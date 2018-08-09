package com.example.api;

import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

public class PersonResource {

    private UUID identifier;

    @NotNull
    @Size(min = 1, max = 50)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 50)
    private String lastName;

    public PersonResource() {
    }

    public PersonResource(UUID identifier, String firstName, String lastName) {
        this.identifier = identifier;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("identifier", identifier)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .toString();
    }
}
