package com.example.api;

import com.example.entity.Person;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PersonResourceAssembler extends ResourceAssemblerSupport<Person, PersonResource> {

  private final ModelMapper modelMapper;

  @Autowired
  public PersonResourceAssembler(ModelMapper modelMapper) {
    super(PersonRestController.class, PersonResource.class);
    this.modelMapper = modelMapper;
  }

  @Override
  public PersonResource toResource(Person person) {
    PersonResource personResource = modelMapper.map(person, PersonResource.class);
    personResource.add(
        linkTo(methodOn(PersonRestController.class).getPerson(person.getIdentifier()))
            .withSelfRel(),
        linkTo(methodOn(PersonRestController.class).getPerson(person.getIdentifier()))
            .withRel("update"),
        linkTo(methodOn(PersonRestController.class).getPerson(person.getIdentifier()))
            .withRel("delete"),
        linkTo(methodOn(PersonRestController.class).getPersonAddresses(person.getIdentifier()))
            .withRel("addresses"));
    return personResource;
  }
}
