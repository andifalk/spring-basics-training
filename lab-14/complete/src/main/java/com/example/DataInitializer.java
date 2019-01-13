package com.example;

import com.example.entity.Address;
import com.example.entity.Country;
import com.example.entity.Person;
import com.example.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Autowired
    public DataInitializer(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        Stream.of(
            new Person(UUID.randomUUID(), "Hans", "Mustermann", Arrays.asList(
                    new Address(UUID.randomUUID(), "Hauptstr. 1", "80345", "München",
                            Country.GERMANY, "hans.mustermann@example.com", "089112233444"),
                    new Address(UUID.randomUUID(), "Firmenstr. 1", "70345", "Stuttgart",
                            Country.GERMANY, "hans.mustermann@test.com", "0711112233444"))),
            new Person(UUID.randomUUID(), "Peter", "Bach", Collections.singletonList(
                    new Address(UUID.randomUUID(), "Nebengasse. 11", "60345", "Wien",
                            Country.AUSTRIA, "peter.bach@example.com", "023112233444"))),
            new Person(UUID.randomUUID(), "Maria", "Maier", Collections.singletonList(
                        new Address(UUID.randomUUID(), "Rathausplatz 234", "50345", "Bern",
                                Country.SWITZERLAND, "maria.bach@example.com", "011112233444"))),
            new Person(UUID.randomUUID(), "Stefanie", "Lotter", Collections.singletonList(
                        new Address(UUID.randomUUID(), "Flussgasse. 54", "50344", "Bern",
                                Country.SWITZERLAND, "stefanie.lotter@example.com", "023112233444")))
        ).forEach(personRepository::save);

        logger.info("Number of persons={}", personRepository.count());

        logger.info("All persons={}", personRepository.findAll());

        List<Person> personList = personRepository.findAllLivingInCity("Bern");
        logger.info("Persons living in the city of 'Bern': {}", personList);

        Person personWithAddress = personRepository.findOneByFirstNameIgnoreCaseAndLastNameIgnoreCase("hans", "mustermann");
        logger.info("Person {} with addresses {}", personWithAddress, personWithAddress.getAddresses());
    }
}
