package com.example.api;

import org.springframework.hateoas.ResourceSupport;

import java.util.Collection;

public class AddressListResource extends ResourceSupport {

  private final Collection<AddressResource> addresses;

  public AddressListResource(Collection<AddressResource> addresses) {
    this.addresses = addresses;
  }

  public Collection<AddressResource> getAddresses() {
    return addresses;
  }
}
