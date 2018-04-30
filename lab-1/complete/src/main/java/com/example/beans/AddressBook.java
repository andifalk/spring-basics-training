package com.example.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class AddressBook {

    private List<? extends Address> addresses;

    public AddressBook() {
    }

    public AddressBook(List<? extends Address> addresses) {
        this.addresses = addresses;
    }

    public List<? extends Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<? extends Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
        toStringBuilder.append("addresses", "\n");
        if (addresses != null) {
            addresses.forEach(
                    a -> {
                        toStringBuilder.append(a + "\n");
                    }

            );
        }
        return toStringBuilder.toString();
    }
}


