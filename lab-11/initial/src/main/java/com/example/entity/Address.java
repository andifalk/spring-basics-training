package com.example.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
public class Address extends AbstractPersistable<Long> {

    @NotNull
    @Column(unique = true)
    private UUID identifier;

    @NotNull
    @Size(max = 50)
    private String street;

    @NotNull
    @Size(max = 10)
    private String zipCode;

    @NotNull
    @Size(max = 50)
    private String city;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Country country;

    @Email
    private String email;

    @Size(max = 50)
    private String phone;

    public Address() {
    }

    public Address(UUID identifier, String street, String zipCode, String city,
                   Country country, String email, String phone) {
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
                .append("id", getId())
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
