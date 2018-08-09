package com.example.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class PersonBuilder {

  private UUID identifier = UUID.randomUUID();

  private String firstName = "Hans";

  private String lastName = "Mustermann";

  private List<Address> addresses = new ArrayList<>();

  public static PersonBuilder person() {
    return new PersonBuilder();
  }

  private PersonBuilder() {}

  public PersonBuilder withIdentifier(UUID identifier) {
    this.identifier = identifier;
    return this;
  }

  public PersonBuilder withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public PersonBuilder withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public PersonBuilder addAddress(Address address) {
    this.addresses.add(address);
    return this;
  }

  public Person build() {
    return new Person(identifier, firstName, lastName, addresses);
  }
}
