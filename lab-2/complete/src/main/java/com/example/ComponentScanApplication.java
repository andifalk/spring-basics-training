package com.example;

import com.example.beans.Address;
import com.example.beans.AddressBook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class ComponentScanApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ComponentScanConfiguration.class);
        AddressBook addressBook = ctx.getBean(AddressBook.class);

        List<Address> addressList = (List<Address>) ctx.getBean("addressList");
        addressBook.setAddresses(addressList);

        System.out.println(addressBook);
    }

}
