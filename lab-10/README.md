# Lab 10: Spring Data JPA
In this lab we start with a multi-layered Spring Boot application beginning with the data access layer.

After completing this lab you should know how implement the data access layer using spring data jpa and hibernate
on an auto-configured in-memory H2 database.

## Initial code

There _initial_ project mostly contains the base spring boot application containing base classes
to reduce typing effort for you the training participants. 

In the _complete_ project you find the spring boot project containing the data access layer with following classes/files:

* CompleteApplication: This is the generated starter class to start the spring boot application (contains a main() method).
* DataInitializer: A spring boot command line helper class to store some test data in the database at application start.
* PersonService: A spring service defining the transactional boundaries for data access layer
* PersonRepository: The data access repository for *Person* and *Address* entity data
* Entities:
    * Person: Persistent *Person* entity
    * Address: Persistent *Address* entity
    * Country: An enum for countries (used in *Address*)
* application.yml: Externalized (default) configuration for the application in YAML format
 
## Steps to complete

1. Start with completing the classes *Person* and *Address* to full JPA entities .

    **Person** entity:
    
    Attribute  | Type          | Constraints
    -----------|---------------|--------------------
    identifier | UUID          | Not null, unique
    firstName  | String        | Length=50, Not null
    lastName   | String        | Length=50, Not null
    addresses  | List<Address> | Cascade=all

    **Address** entity:
    
    Attribute  | Type          | Constraints
    -----------|---------------|--------------------
    identifier | UUID          | Not null, unique
    street     | String        | Length=50, Not null
    zipCode    | String        | Length=50, Not null
    city       | String        | Length=50, Not null
    country    | String        | Enum=String, Not null
    email      | String        | Length=50
    phone      | String        | Length=50
           
    Use typical JPA annotations like `@Entity`, `@Column` and bean validations like `@NotNull` or `@Size` to define
    the entities. 
    
2. Now it is time to implement the corresponding repository for data access. Create a new interface *PersonRepository*
that extends *JpaRepository* in new package *com.example.repository*. This way you automatically already get all the
CRUD (Create/Read/Update/Delete) functionality.
    
3. In this step we want to extend this *PersonRepository* with some custom operations:

    1. Find a person for given first and last name (with ignoring case). Use 
    the automatic generation of queries based on naming patterns as described 
    in [Query Creation](https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.query-methods.query-creation).
    Make sure all addresses are also fetched in this finder (Hint: Look for [Entity Graphs](https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.entity-graph)).

    2. Find all persons having given last name (ignoring case). Use same approach as well as for previous one.

    3. Find unique person by its identifier (UUID). Use same approach again as previous one.
    
    4. Find all persons living at an address with a given city. Use `@Query` approach here (with join and fetch join to load
    addresses as well). See [Using @Query](https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.query-methods.at-query)
    for reference.

    5. Delete a person by its identifier (UUID). See [Derived Delete Queries](https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.modifying-queries.derived-delete).

    Tip: To test all these operations use the existing class *DataInitializer*.
    
4. Complete the service class *PersonService* in package *com.example.service* (this acts as transaction boundary) for managing persons.
Delegate to methods of *PersonRepository* and do not forget to put `@Transactional` annotations to modifying operations (there is a 
class-level 'readonly' transaction already defined). Please be aware of implementing lazy loading operations leading either to 
typical N+1 sql problems or even to lazy loading errors (when executed outside the transaction boundary). 
     
5. When running the application please have a closer look into the console log output. Here you see all the sql 
statements being executed with corresponding transaction boundaries.

6. You may also look into the in-memory database using the h2 console. To perform this just browse to 
[localhost:8080/h2-console](http://localhost:8080/h2-console). Make sure that the following settings are in the login dialog:

    * Driver Class: org.h2.Driver
    * JDBC-URL: jdbc:h2:mem:testdb
    * User Name: sa
    * Password: 
         
***Tip:***
If you need any help then consult the [presentation](https://andifalk.github.io/spring-basics-training/presentation/index.html) 
or the [Spring Data JPA Reference Docs](https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/). 
If you really have no clue you can always look into the finished reference code in _complete_ sub project
