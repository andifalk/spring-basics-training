package com.example.api;

import com.example.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PersonListResourceAssembler extends ResourceAssemblerSupport<Collection<Person>, PersonListResource> {

  private final PersonResourceAssembler personResourceAssembler;

  @Autowired
  public PersonListResourceAssembler(PersonResourceAssembler personResourceAssembler) {
    super(PersonRestController.class, PersonListResource.class);
    this.personResourceAssembler = personResourceAssembler;
  }

  @Override
  public PersonListResource toResource(Collection<Person> personList) {
    PersonListResource personListResource = new PersonListResource(
            personList.stream()
                    .map(personResourceAssembler::toResource)
                    .collect(Collectors.toList()));
    personListResource.add(
            linkTo(methodOn(PersonRestController.class).getAllPersons()).withSelfRel());
    personListResource.add(
            linkTo(methodOn(PersonRestController.class).createPerson(new PersonResource()))
                    .withRel("create"));
    return personListResource;
  }
}
