package com.example;

import com.example.beans.AddressBook;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlFileApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        AddressBook addressBook = ctx.getBean(AddressBook.class);
        System.out.println(addressBook);
    }

}
