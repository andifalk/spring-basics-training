package com.example;

import com.example.beans.Person;
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
    public List<Person> addressList(Person person1, Person person2, Person person3) {
        List<Person> personList = new ArrayList<>();
        person1.setFirstName("Hans");
        person1.setLastName("Muster");
        person1.setStreet("Hauptstr. 12");
        person1.setZipCode("70159");
        person1.setCity("Stuttgart");
        person1.setCountry(Country.GERMANY);
        personList.add(person1);

        person2.setFirstName("Peter");
        person2.setLastName("Maier");
        person2.setStreet("Nebenstr.2");
        person2.setZipCode("11111");
        person2.setCity("Wien");
        person2.setCountry(Country.AUSTRIA);
        personList.add(person2);

        person3.setFirstName("Susanne");
        person3.setLastName("Bauer");
        person3.setStreet("Parkallee 233");
        person3.setZipCode("22222");
        person3.setCity("Bern");
        person3.setCountry(Country.SWITZERLAND);
        personList.add(person3);

        return personList;
    }
}
