package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        /*
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
        */
    }
}
