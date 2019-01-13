package com.example.repository;

import com.example.entity.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @EntityGraph(attributePaths = "addresses")
    Person findOneByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

    List<Person> findAllByLastNameIgnoreCase(String lastName);

    Person findOneByIdentifier(UUID identifier);

    @Query("SELECT DISTINCT p FROM Person p LEFT JOIN p.addresses adr JOIN FETCH p.addresses WHERE adr.city = :city")
    List<Person> findAllLivingInCity(@Param("city") String city);

    void deleteByIdentifier(UUID identifier);

}
