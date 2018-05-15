# Lab 2: Beans and Application Context (Java)
In this lab we learn how to build a basic spring application using the java application context configuration.

After completing this lab you should know how to create a basic spring application
using the java application context for beans configuration. This includes also component scanning of beans.

Additionally you learn the difference between the two main bean scopes _singleton_ and _prototype_.

## Initial code

In the _initial_ project you find your first spring bean classes (PoJo's) to build a simple address book:

* AddressBook: Container for _Person_ objects
* Person: The person holding address details
* Country: Just an enum defining some countries

In addition you also find the following:

* ComponentScanApplication: This is the class to start the application (contains a main() method)
* ComponentScanConfiguration: This is an empty java application context configuration class
 
## Steps to complete

1. Change beans for _AddressBook_ and _Person_ so that these are detected by component scanning
(Please note that multiple instances of a _Person_ are required, you can use the _@Scope_ annotation here).

2. Configure the _ComponentScanConfiguration_ to enable component scannning (use _@ComponentScan_ annotation).
and create a java bean for the address list using the @Bean annotation.

3. Create an annotation based classpath spring context in main method of existing class _ComponentScanApplication_. Then
get an instance of _AddressBook_ from the instantiated context and print it to system out. 


***Tip:***
If you need any help then consult the [presentation](https://andifalk.github.io/spring-basics-training/presentation/index.html) 
or the [Spring Reference Docs](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-classpath-scanning). 
If you really have no clue you can always look into the finished reference code in _complete_ sub project

