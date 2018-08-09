package com.example.service;

import com.example.entity.AddressBuilder;
import com.example.entity.PersonBuilder;
import com.example.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Verify person service")
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

  @Mock private PersonRepository personRepository;

  @DisplayName("finds expected cities for persons")
  @Test
  void verifyFindAllCitiesOfPersons() {
    given(personRepository.findAll())
        .willReturn(
            Arrays.asList(
                PersonBuilder.person()
                    .addAddress(AddressBuilder.address().withCity("Stuttgart").build())
                    .build(),
                PersonBuilder.person()
                    .addAddress(AddressBuilder.address().withCity("Berlin").build())
                    .build(),
                PersonBuilder.person()
                    .addAddress(AddressBuilder.address().withCity("München").build())
                    .build()));

    PersonService cut = new PersonService(personRepository);
    assertThat(cut.findAllCitiesOfPersons()).isNotNull().isEqualTo("Stuttgart,Berlin,München");
  }

  @DisplayName("finds no cities when no persons are found")
  @Test
  void verifyFindNoCitiesOfPersons() {
    given(personRepository.findAll()).willReturn(Collections.emptyList());

    PersonService cut = new PersonService(personRepository);
    assertThat(cut.findAllCitiesOfPersons()).isNotNull().isEmpty();
  }
}
