package com.example.entity;

import java.util.UUID;

public final class AddressBuilder {

  private UUID identifier = UUID.randomUUID();

  private String street = "Hauptstr. 10";

  private String zipCode = "77777";

  private String city = "Stuttgart";

  private Country country = Country.GERMANY;

  private String email = "test@example.com";

  private String phone = "0711123456789";

  public static AddressBuilder address() {
    return new AddressBuilder();
  }

  private AddressBuilder() {}

  public AddressBuilder withIdentifier(UUID identifier) {
    this.identifier = identifier;
    return this;
  }

  public AddressBuilder withStreet(String street) {
    this.street = street;
    return this;
  }

  public AddressBuilder withCity(String city) {
    this.city = city;
    return this;
  }

  public AddressBuilder withZipCode(String zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  public AddressBuilder withCountry(Country country) {
    this.country = country;
    return this;
  }

  public AddressBuilder withEmail(String email) {
    this.email = email;
    return this;
  }

  public AddressBuilder withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public Address build() {
    return new Address(identifier, street, zipCode, city, country, email, phone);
  }
}
