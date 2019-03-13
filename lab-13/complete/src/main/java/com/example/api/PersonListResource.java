package com.example.api;

import org.springframework.hateoas.ResourceSupport;

import java.util.Collection;

public class PersonListResource extends ResourceSupport {

  private final Collection<PersonResource> persons;

  public PersonListResource(Collection<PersonResource> persons) {
    this.persons = persons;
  }

  public Collection<PersonResource> getPersons() {
    return persons;
  }
}
