# Lab 8: Externalized Configuration (Property and YAML)
In this lab we extend the Spring Boot application with some configuration.

Hardcoded configuration settings inside the java code is not according to what we call *cloud native*.
After completing this lab you should know how to externalize the configuration from a spring boot application.
This includes both possible configuration formats:

*  Properties
*  YAML 

## Initial code

There _initial_ project contains the generated spring boot application from the previous step. 

In the _complete_ project you find the generated spring boot project with following classes/files:

* CompleteApplication: This is the generated starter class to start the spring boot application (contains a main() method).
* DemoRestController: A sample spring controller defining a simple REST interface.
* MyConfigurationProperties: A typed configuration class with validation
* application.yml: Externalized (default) configuration for the application in YAML format
* application-dev.yml: Externalized configuration for the application in YAML format if spring profile *dev* is active
 
## Steps to complete

1. Open the *application.properties* file (this has also been generated in previous step) and 
add the following to this file:

    `server.port=9090`
    
    This changes the port of the running application from the default port of *8080* to *9090*. 

2. Now rename the *application.properties* file to *application.yml* and also change the entry from previous step to:

    ```
    server:
        port: 9090
    ```
    
    If you start the application now you have to browse to [localhost:9090](http://localhost:9090).
    
3. In this step we want to add some predefined actuator property. To do this just add the following to the 
*application.yml*

    ```
    info:
      application:
        name: MyPropertiesApp
    ```

    Now restart the application and this time browse to [localhost:9090/actuator/info](http://localhost:9090/actuator/info)
    
    Here you should see now just this new entry with value *MyPropertiesApp*
    
    ```
    "application": {
        "name": "MyPropertiesApp"
      }
    }
    ```
    
    This way you can add important information about your application like version, git commit hash etc. 

4. Now we will add a custom property with validation. 
Create a new class called *MyConfigurationProperties* in package *com.example*. Put the following code into this class:

    ```
    @Validated
    @ConfigurationProperties(prefix = "myprops")
    public class MyConfigurationProperties {
    
        @NotNull
        private String message;
    
        public String getMessage() {
            return message;
        }
    
        public void setMessage(String message) {
            this.message = message;
        }
    }
    ```
    
    The annotation *@ConfigurationProperties* marks this class to be automatically mapped to corresponding properties
    named with pattern `myprops.*`. The *@Validated* annotation tells Spring to validate all properties with correspondig validation
    annotations like the *@NotNull* on the `message` attribute. 

    If you restart the application the it will now fail with following message:
    
    ```
    Binding to target org.springframework.boot.context.properties.bind.BindException: 
    Failed to bind properties under 'myprops' to com.example.MyConfigurationProperties failed:
    
        Property: myprops.message
        Value: null
        Reason: must not be null
    ```

    So the validation failed because this new property is missing in *application.yml*.
    Just add the following to *application.yml*:

    ```
    myprops:
      message: 'hello from configuration'
    ```
    
    Now the application should start again without any error.
    
5. The annotation *@ConfigurationProperties* does NOT add the corresponding class to the application context. To make 
this class and the contained property accessible to the application you have to add the following to the main class
*InitialApplication*:

    ```
    @EnableConfigurationProperties(MyConfigurationProperties.class)
    public class CompleteApplication {
    ...
    }
    ```
     
6. Adding a configuration does not make much sense if this is not used anywhere, so we change the *DemoRestController* class:

    ``` 
    @RestController
    public class DemoRestController {
    
        private final MyConfigurationProperties properties;
    
        @Value("${info.application.name}")
        private String appName;
    
        @Autowired
        public DemoRestController(MyConfigurationProperties properties) {
            this.properties = properties;
        }
    
        @GetMapping("/")
        public String hello() {
            return properties.getMessage() + " from " + appName;
        }
    }
    ```
    
    We inject an instance of the new configuration class *MyConfigurationProperties* to access the `message` property
    and we inject the other property `info.application.name` by using the *@Value* annotation. These are the two usual ways to
    access configuration properties in a spring boot application.

7. Start the application using *InitialApplication* class. 
After some seconds you have your first running application. This application does not do pretty much for now.
Browse to [localhost:9090](http://localhost:9090) then you should see `hello from configurationfrom MyPropertiesApp` on the screen. 

8. Finally we want to use a spring profile to map a different `server.port` property when another profile is active.
Therefore create a new file called *application-dev.yml under the folder *src/main/resources just next to the default *application.yml*.
with following contents:

    ```
    server:
        port: 10001
    ```

Now restart the application with specifying the VM property *-Dspring.profiles.active=dev*. As the server port should now be
configured to `10001` you should be able to access the application using [localhost:10001](http://localhost:10001) and the log output should
include `The following profiles are active: dev`.
If you restart again without specifying this vm property then it should again be accessible at [localhost:9090](http://localhost:9090). 

***Tip:***
If you need any help then consult the [presentation](https://andifalk.github.io/spring-basics-training/presentation/index.html) 
or the [Spring Boot Reference Docs](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config). 
If you really have no clue you can always look into the finished reference code in _complete_ sub project

