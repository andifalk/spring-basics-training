package com.example.service;

import com.example.entity.Address;
import com.example.entity.Person;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PersonService {

  private final PersonRepository personRepository;

  @Autowired
  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public Person findOneByFirstNameIgnoreCaseAndLastNameIgnoreCase(
      String firstName, String lastName) {
    return personRepository.findOneByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);
  }

  public List<Person> findAllByLastNameIgnoreCase(String lastName) {
    return personRepository.findAllByLastNameIgnoreCase(lastName);
  }

  public Person findOneByIdentifier(UUID identifier) {
    return personRepository.findOneWithDetailsByIdentifier(identifier);
  }

  public List<Person> findAll() {
    return personRepository.findAll();
  }

  @Transactional
  public Person save(Person person) {
    return personRepository.save(person);
  }

  @Transactional
  public void deleteByIdentifier(UUID identifier) {
    personRepository.deleteByIdentifier(identifier);
  }

  public String findAllCitiesOfPersons() {
    // Warning: This produces unwanted N+1 sql problems by navigating to addresses with lazy
    // loading!!!
    // If you perform this outside a transaction boundary you even will get an error!
    return personRepository.findAll().stream()
        .map(p -> p.getAddresses().stream().map(Address::getCity).collect(Collectors.joining(",")))
        .collect(Collectors.joining(","));
  }
}
