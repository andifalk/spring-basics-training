# Lab 13: RESTful Services with HATEOAS
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
All operations of the rest interface should call only the *PersonService* operations.
Please add the following requests:

    * GET request for list of all persons (use a *@GetMapping*): `/persons`
    * GET request for one person (use a *@GetMapping*): `/persons/{personId}`
    * GET request for the addresses of a person (use a *GetMapping*): `/persons/{personId}/addresses`
    * POST request to create a person (use a *PostMapping*): `/persons`
    * POST request to add an address to a person (use a *PostMapping*): `/persons/{personId}`
    * DELETE request to delete a person with its addresses (use a *DeleteMapping*): `/persons/{personId}`

You also have to specify the correct consumer and/or producer (the media types, in our case JSON/UTF-8) for each request.

***Tip:***
If you need any help then consult the [presentation](https://andifalk.github.io/spring-basics-training/presentation/index.html) 
or the [Spring MVC Reference Docs](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-controller)
and the [Spring Boot Reference Docs](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-spring-mvc). 
If you really have no clue you can always look into the finished reference code in _complete_ sub project
