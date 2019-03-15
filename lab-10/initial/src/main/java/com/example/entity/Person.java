package com.example.entity;

import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.UUID;

public class Person {

  private UUID identifier;

  private String firstName;

  private String lastName;

  private List<Address> addresses;

  public Person() {}

  public Person(UUID identifier, String firstName, String lastName, List<Address> addresses) {
    this.identifier = identifier;
    this.firstName = firstName;
    this.lastName = lastName;
    this.addresses = addresses;
  }

  public UUID getIdentifier() {
    return identifier;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  @Override
  public String toString() {
    return new org.apache.commons.lang3.builder.ToStringBuilder(
            this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("identifier", identifier)
        .append("firstName", firstName)
        .append("lastName", lastName)
        .toString();
  }
}
