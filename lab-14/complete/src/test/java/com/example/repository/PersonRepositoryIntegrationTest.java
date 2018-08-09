package com.example.repository;

import com.example.entity.AddressBuilder;
import com.example.entity.Person;
import com.example.entity.PersonBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Verify person repository")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
class PersonRepositoryIntegrationTest {

  @Autowired private PersonRepository cut;

  @Autowired private TestEntityManager entityManager;

  @DisplayName("can store a person")
  @Test
  void verifyStorePerson() {
    Person person = cut.save(PersonBuilder.person().withLastName("Maier").build());
    assertThat(person).isNotNull();
    assertThat(person.getId()).isGreaterThan(0);
  }

  @DisplayName("can find a person by its identifier")
  @Test
  void verifyFindPersonByIdentifier() {
    UUID identifier = UUID.randomUUID();
    entityManager.persist(
        PersonBuilder.person()
            .withIdentifier(identifier)
            .withLastName("Maier")
            .addAddress(AddressBuilder.address().withCity("Stuttgart").build())
            .addAddress(AddressBuilder.address().withCity("Berlin").build())
            .build());

    Person person = cut.findOneByIdentifier(identifier);
    assertThat(person).isNotNull().extracting(Person::getIdentifier).contains(identifier);
  }

  @DisplayName("can find all persons living in 'Stuttgart'")
  @Test
  void verifyFindAllLivingInCity() {
    entityManager.persist(
        PersonBuilder.person()
            .withLastName("Maier")
            .addAddress(AddressBuilder.address().withCity("Stuttgart").build())
            .addAddress(AddressBuilder.address().withCity("Berlin").build())
            .build());
    entityManager.persist(
        PersonBuilder.person()
            .withLastName("Huber")
            .addAddress(AddressBuilder.address().withCity("Stuttgart").build())
            .addAddress(AddressBuilder.address().withCity("München").build())
            .build());
    entityManager.persist(
        PersonBuilder.person()
            .withLastName("Müller")
            .addAddress(AddressBuilder.address().withCity("Hamburg").build())
            .build());
    entityManager.persist(PersonBuilder.person().withLastName("Bader").build());

    List<Person> personList = cut.findAllLivingInCity("Stuttgart");

    assertThat(personList)
        .isNotNull()
        .hasSize(2)
        .extracting(Person::getLastName)
        .contains("Maier", "Huber");
  }

  @DisplayName("cannot find any person living in 'München'")
  @Test
  void verifyFindNoneLivingInCity() {
    entityManager.persist(
        PersonBuilder.person()
            .withLastName("Maier")
            .addAddress(AddressBuilder.address().withCity("Stuttgart").build())
            .addAddress(AddressBuilder.address().withCity("Berlin").build())
            .build());
    entityManager.persist(PersonBuilder.person().withLastName("Bader").build());

    List<Person> personList = cut.findAllLivingInCity("München");

    assertThat(personList).isNotNull().isEmpty();
  }
}
