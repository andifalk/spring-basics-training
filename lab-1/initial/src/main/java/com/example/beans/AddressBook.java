package com.example.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class AddressBook {

  private List<? extends Person> addresses;

  private Person address1, address2;

  public AddressBook() {}

  public AddressBook(List<? extends Person> addresses) {
    this.addresses = addresses;
  }

  public List<? extends Person> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<? extends Person> addresses) {
    this.addresses = addresses;
  }

  public void setAddress1(Person address1) {
    this.address1 = address1;
  }

  public void setAddress2(Person address2) {
    this.address2 = address2;
  }

  @Override
  public String toString() {
    ToStringBuilder toStringBuilder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
    toStringBuilder.append("addresses", "\n");
    if (addresses != null) {
      addresses.forEach(
          a -> {
            toStringBuilder.append(a + "\n");
          });
    }
    toStringBuilder.append("address1", address1).append("\n").append("address2", address2);
    return toStringBuilder.toString();
  }
}
