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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/persons", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PersonRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRestController.class);

    private final PersonService personService;

    @Autowired
    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonResource> getAllPersons() {
        ModelMapper modelMapper = new ModelMapper();
        return personService.findAll()
                .stream()
                .map(p -> modelMapper.map(p, PersonResource.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonResource> getPerson(@PathVariable("personId") UUID personIdentifier) {
        Person person = personService.findOneByIdentifier(personIdentifier);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ModelMapper().map(person, PersonResource.class));
    }

    @GetMapping("/{personId}/addresses")
    public ResponseEntity<List<AddressResource>> getPersonAddresses(@PathVariable("personId") UUID personIdentifier) {
        Person person = personService.findOneByIdentifier(personIdentifier);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(person.getAddresses()
                .stream()
                .map(a -> new ModelMapper().map(a, AddressResource.class))
                .collect(Collectors.toList()));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PersonResource createPerson(@Valid @RequestBody PersonResource personResource) {

        Person person = new ModelMapper().map(personResource, Person.class);
        if (person.getIdentifier() == null) {
            person.setIdentifier(UUID.randomUUID());
        }
        return new ModelMapper().map(personService.save(person), PersonResource.class);
    }

    @PostMapping(path = "/{personId}/addresses", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AddressResource>> addAddress(@PathVariable("personId") UUID personIdentifier, @RequestBody AddressResource addressResource) {

        Person person = personService.findOneByIdentifier(personIdentifier);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }

        Address address = new ModelMapper().map(addressResource, Address.class);
        if (address.getIdentifier() == null) {
            address.setIdentifier(UUID.randomUUID());
        }
        person.getAddresses().add(address);
        return ResponseEntity.ok(personService.save(person).getAddresses()
                .stream()
                .map(a -> new ModelMapper().map(a, AddressResource.class))
                .collect(Collectors.toList()));

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
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Submitted data is not valid");
    }

}
