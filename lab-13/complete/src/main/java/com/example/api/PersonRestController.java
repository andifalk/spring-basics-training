package com.example.api;

import com.example.entity.Address;
import com.example.entity.Person;
import com.example.service.PersonService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/persons", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PersonRestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PersonRestController.class);

  private final PersonService personService;

  private final ModelMapper modelMapper;

  @Autowired
  public PersonRestController(PersonService personService, ModelMapper modelMapper) {
    this.personService = personService;
    this.modelMapper = modelMapper;
  }

  @GetMapping
  public PersonListResource getAllPersons() {
    PersonListResource personListResource =
        new PersonListResource(
            personService.findAll().stream()
                .map(p -> modelMapper.map(p, PersonResource.class))
                .peek(p -> p.add(linkTo(methodOn(PersonRestController.class).getPerson(p.getIdentifier()))
                                .withSelfRel()))
                .collect(Collectors.toList()));
    personListResource.add(
        linkTo(methodOn(PersonRestController.class).getAllPersons()).withSelfRel());
    personListResource.add(
        linkTo(methodOn(PersonRestController.class).createPerson(new PersonResource()))
            .withRel("create"));
    return personListResource;
  }

  @GetMapping("/{personId}")
  public ResponseEntity<PersonResource> getPerson(@PathVariable("personId") UUID personIdentifier) {
    Person person = personService.findOneByIdentifier(personIdentifier);
    if (person == null) {
      return ResponseEntity.notFound().build();
    }

    PersonResource personResource = modelMapper.map(person, PersonResource.class);
    personResource.add(
            linkTo(methodOn(PersonRestController.class).getPerson(personIdentifier)).withSelfRel(),
            linkTo(methodOn(PersonRestController.class).getPerson(personIdentifier)).withRel("update"),
            linkTo(methodOn(PersonRestController.class).getPerson(personIdentifier)).withRel("delete"),
            linkTo(methodOn(PersonRestController.class).getPersonAddresses(personIdentifier)).withRel("addresses"));
    return ResponseEntity.ok(personResource);
  }

  @GetMapping("/{personId}/addresses")
  public ResponseEntity<List<AddressResource>> getPersonAddresses(
      @PathVariable("personId") UUID personIdentifier) {
    Person person = personService.findOneByIdentifier(personIdentifier);
    if (person == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(
        person
            .getAddresses()
            .stream()
            .map(a -> modelMapper.map(a, AddressResource.class))
            .collect(Collectors.toList()));
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<PersonResource> createPerson(@Valid @RequestBody PersonResource personResource) {

    Person person = modelMapper.map(personResource, Person.class);
    if (person.getIdentifier() == null) {
      person.setIdentifier(UUID.randomUUID());
    }
    URI location =
            ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/persons")
                    .path("/{personId}")
                    .buildAndExpand(person.getIdentifier())
                    .toUri();
    return ResponseEntity.created(location).body(modelMapper.map(personService.save(person), PersonResource.class));
  }

  @PostMapping(path = "/{personId}/addresses", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<AddressListResource> addAddress(
      @PathVariable("personId") UUID personIdentifier,
      @RequestBody AddressResource addressResource) {

    Person person = personService.findOneByIdentifier(personIdentifier);
    if (person == null) {
      return ResponseEntity.notFound().build();
    }

    Address address = modelMapper.map(addressResource, Address.class);
    if (address.getIdentifier() == null) {
      address.setIdentifier(UUID.randomUUID());
    }
    person.getAddresses().add(address);

    URI location =
            ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/persons")
                    .path("/{personId}")
                    .path("/addresses")
                    .buildAndExpand(person.getIdentifier())
                    .toUri();

    return ResponseEntity.created(location).body(
      new AddressListResource(
        personService
            .save(person)
            .getAddresses()
            .stream()
            .map(a -> modelMapper.map(a, AddressResource.class))
            .collect(Collectors.toList())));
  }

  @DeleteMapping("/{personId}")
  public ResponseEntity<Void> deletePerson(@PathVariable("personId") UUID personIdentifier) {
    personService.deleteByIdentifier(personIdentifier);
    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<String> handleIntegrityViolations(DataIntegrityViolationException ex) {
    LOGGER.error("Integrity violation: {}", ex.getMessage());
    return ResponseEntity.badRequest().body("Submitted data is not valid");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleInternalErrors(Exception ex) {
    LOGGER.error("General error: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("A general error occurred");
  }
}
