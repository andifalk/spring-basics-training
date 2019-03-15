package com.example.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.UUID;

public class Address {

  private UUID identifier;

  private String street;

  private String zipCode;

  private String city;

  private Country country;

  private String email;

  private String phone;

  public Address() {}

  public Address(
      UUID identifier,
      String street,
      String zipCode,
      String city,
      Country country,
      String email,
      String phone) {
    this.identifier = identifier;
    this.street = street;
    this.zipCode = zipCode;
    this.city = city;
    this.country = country;
    this.email = email;
    this.phone = phone;
  }

  public UUID getIdentifier() {
    return identifier;
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

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("identifier", identifier)
        .append("street", street)
        .append("zipCode", zipCode)
        .append("city", city)
        .append("country", country)
        .append("email", email)
        .append("phone", phone)
        .toString();
  }
}
