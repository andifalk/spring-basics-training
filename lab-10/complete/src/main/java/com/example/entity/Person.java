package com.example.entity;

import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
    indexes = {
      @Index(name = "IDX_FIRST_NAME_LAST_NAME", columnList = "firstName, lastName", unique = true)
    })
public class Person extends AbstractPersistable<Long> {

  @NotNull
  @Column(unique = true)
  private UUID identifier;

  @NotNull
  @Size(min = 1, max = 50)
  private String firstName;

  @NotNull
  @Size(min = 1, max = 50)
  private String lastName;

  @OneToMany(cascade = CascadeType.ALL)
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
        .append("id", getId())
        .append("identifier", identifier)
        .append("firstName", firstName)
        .append("lastName", lastName)
        .toString();
  }
}
