# Lab 12: RESTful Services
In this lab we implement a RESTful service on top of the service and data access layer we have
build in the previous lab.

After completing this lab you should know how to implement the different REST operations like
GET, POST and DELETE.

## Initial code

There _initial_ project contains the spring boot application from previous lab with the service 
and data access layer. 

In the _complete_ project you find the spring boot project containing a RESTful service on top of 
the data access layer with following classes/files:

* CompleteApplication: This is the generated starter class to start the spring boot application (contains a main() method).
* DataInitializer: A spring boot command line helper class to store some test data in the database at application start.
* PersonService: A spring service defining the transactional boundaries for data access layer
* PersonRepository: The data access repository for *Person* and *Address* entity data
* PersonRestController: The spring rest controller defining the RESTful service
* Resources:
    * PersonResource: The REST resource for a person
    * AddressResource: The REST resource for an address
* Entities:
    * Person: Persistent *Person* entity
    * Address: Persistent *Address* entity
    * Country: An enum for countries (used in *Address*)
* V01_InitialSchema.sql: The flyway db migration script (executed automatically at application start)
* application.yml: Externalized (default) configuration for the application in YAML format
* http/...: Sample requests for RESTful service (to use in IntelliJ)
 
## Steps to complete

1. In this step we complete the already existing *PersonRestController* in package *com.example.api*.
There you also find already the corresponding resource classes for person and addresses.
             
***Tip:***
If you need any help then consult the [presentation](https://andifalk.github.io/spring-basics-training/presentation/index.html) 
or the [Spring Boot Reference Docs](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-execute-flyway-database-migrations-on-startup)
or the [Flyway DB Docs](https://flywaydb.org/documentation/). 
If you really have no clue you can always look into the finished reference code in _complete_ sub project
