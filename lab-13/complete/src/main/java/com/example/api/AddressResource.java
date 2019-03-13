package com.example.api;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class AddressResource extends ResourceSupport {

  @NotNull private UUID identifier;

  @NotNull
  @Size(max = 50)
  private String street;

  @NotNull
  @Size(max = 10)
  private String zipCode;

  @NotNull
  @Size(max = 50)
  private String city;

  @NotNull private Country country;

  @Email private String email;

  @Size(max = 50)
  private String phone;

  public AddressResource() {}

  public AddressResource(
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

  public void setIdentifier(UUID identifier) {
    this.identifier = identifier;
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

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhone(String phone) {
    this.phone = phone;
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
