package com.example;

import com.example.beans.Person;
import com.example.beans.AddressBook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class ComponentScanApplication {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ComponentScanConfiguration.class);
        AddressBook addressBook = ctx.getBean(AddressBook.class);

        List<Person> personList = (List<Person>) ctx.getBean("addressList");
        addressBook.setAddresses(personList);

        System.out.println(addressBook);
    }

}
