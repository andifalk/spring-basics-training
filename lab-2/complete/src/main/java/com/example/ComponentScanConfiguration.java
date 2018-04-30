package com.example;

import com.example.beans.Address;
import com.example.beans.Country;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.example.beans")
public class ComponentScanConfiguration {

    @Bean
    public List<Address> addressList(Address address1, Address address2, Address address3) {
        List<Address> addressList = new ArrayList<>();
        address1.setFirstName("Hans");
        address1.setLastName("Muster");
        address1.setStreet("Hauptstr. 12");
        address1.setZipCode("70159");
        address1.setCity("Stuttgart");
        address1.setCountry(Country.GERMANY);
        addressList.add(address1);

        address2.setFirstName("Peter");
        address2.setLastName("Maier");
        address2.setStreet("Nebenstr.2");
        address2.setZipCode("70159");
        address2.setCity("Stuttgart");
        address2.setCountry(Country.GERMANY);
        addressList.add(address2);

        address3.setFirstName("Susanne");
        address3.setLastName("Bauer");
        address3.setStreet("Parkallee 233");
        address3.setZipCode("80034");
        address3.setCity("München");
        address3.setCountry(Country.GERMANY);
        addressList.add(address3);

        return addressList;
    }
}
