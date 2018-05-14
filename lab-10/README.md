# Lab 10: Spring Data JPA
In this lab we start with a multi-layered Spring Boot application beginning with the data access layer.

After completing this lab you should know how implement the data access layer using spring data jpa and hibernate
on an autoconfigured in-memory H2 database.

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

1. Start with implementing the JPA entity classes *Person* and *Address*.

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
           
    This information is also limited by default and just shows *up* or *down* status without further details.
    We will change this behaviour as well in the next step. 
    
2. Now it is time to enable more actuator endpoints and also show more details in health endpoint. 
To do this just add the following entries to the *application.yml*:   

    ```
        management:
          endpoints:
            web:
              exposure:
                include: '*'
          endpoint:
            health:
              show-details: always
    ```

    Now restart the application and this time browse to [localhost:8080/actuator](http://localhost:8080/actuator)
    
    Here you should see now a much longer list of actuator endpoints as before. If you want to know what information 
    is exposed by each actuator endpoint just navigate to each entry in your browser.
    If you navigate to the health endpoint at [localhost:8080/actuator/health](http://localhost:8080/actuator/health)
    then you should see more details this time:
         
    ```
      {
       "status": "UP",
       "details": {
         "diskSpace": {
           "status": "UP",
           "details": {
             "total": 950636347392,
             "free": 439610875904,
             "threshold": 10485760
           }
         }
      }
    ```
    
    ***Note***: In production you must secure most of the actuator endpoints as these provide sensitive information
    that can be used by hackers in trying to attack your application! We will add security in the spring security lab section.
    
3. Now we will add our own custom health endpoint indicator. 
Create a new class called *MyHealthEndpoint* in package *com.example*. Put the following code into this class:

    ```
        @Component
        public class MyHealthEndpoint implements HealthIndicator {
            
            private static final Logger LOGGER 
                = LoggerFactory.getLogger(MyHealthEndpoint.class);
        
            @Override
            public Health health() {
                LOGGER.trace("Custom health endpoint called");
                return Health.up().withDetail("mykey", "value").build();
            }
        }
    ```
    
    This custom health endpoint always returns the status *UP* with some sample details.
    A logger is added here as well. Spring boot provides auto configuration for the *SLF4J* logger.
    
    If you now restart the application and browse to [localhost:8080/actuator/health](http://localhost:8080/actuator/health) 
    again then you should see additional details:
    
    ```
    {
      "status": "UP",
      "details": {
        "myHealthEndpoint": {
          "status": "UP",
          "details": {
            "mykey": "value"
          }
        },
        "diskSpace": {
          "status": "UP",
          "details": {
            "total": 950636347392,
            "free": 439610875904,
            "threshold": 10485760
          }
        }
      }
    }
    ```
    
    You cannot see any corresponding *TRACE* log output for the logger we put in in this step as
    the default logging level is *INFO*. We will change this in the last step of this lab.
    
4. In this step we will add a custom actuator endpoint exposed to the web. To achieve this 
create a new class *MyCustomEndpoint* in package *com.example* with the following contents:

    ```    
        @Component
        @WebEndpoint(id = "custom")
        public class MyCustomEndpoint {
        
            @ReadOperation
            public MyStatus myValue() {
                return new MyStatus(200, "ok");
            }
        
            class MyStatus {
                private int status;
                private String detail;
        
                public MyStatus() {
                }
        
                public MyStatus(int status, String detail) {
                    this.status = status;
                    this.detail = detail;
                }
        
                public int getStatus() {
                    return status;
                }
        
                public String getDetail() {
                    return detail;
                }
            }
        }
    ```

    If you restart the application again and browse to the actuator list at 
    [localhost:8080/actuator/actuator](http://localhost:8080/actuator/actuator) then you should 
    see a new endpoint at [localhost:8080/actuator/custom](http://localhost:8080/actuator/custom).
    If you browse to this location then you should see the following contents:
    
    ```
        {
          "status": 200,
          "detail": "ok"
        }
    ```
     
5. In this last step we change the default logging configuration in *application.yml* 
to following settings:

    * Change log level to *DEBUG* for package *org.springframework*
    * Change log level to *TRACE* for package *com.example*
    
    ```
        logging:
          level:
            com:
              example: trace
            org:
              springframework: debug
    ```         

    After making these changes and restarting the application the logging output we put in during this lab
    is appearing in the console. 
         
***Tip:***
If you need any help then consult the [presentation](https://andifalk.github.io/spring-basics-training/presentation/index.html) 
or the [Spring Data JPA Reference Docs](https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/). 
If you are really have no clue you can always look into the finished reference code in _complete_ sub project
