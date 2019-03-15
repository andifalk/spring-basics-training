package com.example.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Person {

  private String firstName;
  private String lastName;
  private String street;
  private String zipCode;
  private String city;
  private Country country;

  public Person() {
    super();
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getStreet() {
    return street;
  }

  public String getZipCode() {
    return zipCode;
  }

  public String getCity() {
    return city;
  }

  public Country getCountry() {
    return country;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
        .append("firstName", firstName)
        .append("lastName", lastName)
        .append("street", street)
        .append("zipCode", zipCode)
        .append("city", city)
        .append("country", country)
        .toString();
  }
}
