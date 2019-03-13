# Lab 15: Unit and Integration Tests
In this lab we add several tests to the implementation we have done so far in the previous labs.

After completing this lab you should know how to test typical spring web applications on different layers
using unit and integration tests.
To support this spring defines several test slices (e.g. *@DataJpaTest* or *@WebMvcTest*).

## Initial code

There _initial_ project contains the spring boot application from previous lab with the RESTful service 
and data access layer secured by spring security. 

In the _complete_ project you find the spring boot project containing a now secured RESTful service on top of 
the data access layer with following classes/files:

* CompleteApplication: This is the generated starter class to start the spring boot application (contains a main() method).
* DataInitializer: A spring boot command line helper class to store some test data in the database at application start.
* PersonService: A spring service defining the transactional boundaries for data access layer
* PersonRepository: The data access repository for *Person* and *Address* entity data
* PersonRestController: The spring rest controller defining the RESTful service
* WebSecurityConfiguration
* Resources:
    * PersonResource: The REST resource for a person
    * AddressResource: The REST resource for an address
* Entities:
    * Person: Persistent *Person* entity
    * Address: Persistent *Address* entity
    * Country: An enum for countries (used in *Address*)
    * PersonBuilder: Helper class for easier creation of person test objects in tests
    * AddressBuilder: Helper class for easier creation of address test objects in tests
* V01_InitialSchema.sql: The flyway db migration script (executed automatically at application start)
* application.yml: Externalized (default) configuration for the application in YAML format
* http/...: Sample requests for RESTful service (to use in IntelliJ)
* src/docs/asciidoc/api-guide.adoc: Asciidoc document required for Spring RestDocs api documentation
 
## Steps to complete

1. Just start the initial application. If you want to access [localhost:8080/persons](http://localhost:8080/persons)
you will notice that this endpoint is now secured and you have to provide user login data to access it.
The same is true for all the actuator endpoints for monitoring like [localhost:8080/actuator/health](http://localhost:8080/actuator/health).

    If you just add spring security as dependency then the current user details are as follows:
    
    * Username=user
    * Password=Look into the console log !!!!!  
    
    Look for something like this:
    `Using generated security password: 9bdc1674-6126-4462-bb61-12d7e03d67bc`
    
2. Now we will add our custom security configuration to our application. This includes

    * Our own user credentials
    * Fine-grained authentication configuration
    * Method layer authorization     

***Tip:***
If you need any help then consult the [presentation](https://andifalk.github.io/spring-basics-training/presentation/index.html) 
or the [Spring Security Reference Docs](https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle)
and the [Spring Boot Reference Docs](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-security). 
If you really have no clue you can always look into the finished reference code in _complete_ sub project
