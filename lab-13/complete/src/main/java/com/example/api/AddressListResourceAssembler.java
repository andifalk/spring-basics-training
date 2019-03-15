package com.example.api;

import com.example.entity.Address;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class AddressListResourceAssembler extends ResourceAssemblerSupport<Collection<Address>, AddressListResource> {

  private final AddressResourceAssembler addressResourceAssembler;

  public AddressListResourceAssembler(AddressResourceAssembler addressResourceAssembler) {
    super(PersonRestController.class, AddressListResource.class);
    this.addressResourceAssembler = addressResourceAssembler;
  }

  @Override
  public AddressListResource toResource(Collection<Address> addressList) {
    return new AddressListResource(addressList.stream()
            .map(addressResourceAssembler::toResource)
            .collect(Collectors.toList()));
  }
}
