package com.example.api;

import com.example.entity.Address;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class AddressResourceAssembler extends ResourceAssemblerSupport<Address, AddressResource> {

  private final ModelMapper modelMapper;

  @Autowired
  public AddressResourceAssembler(ModelMapper modelMapper) {
    super(PersonRestController.class, AddressResource.class);
    this.modelMapper = modelMapper;
  }

  @Override
  public AddressResource toResource(Address address) {
    return modelMapper.map(address, AddressResource.class);
  }
}
