# Lab 1: Beans and Application Context (XML)
In this lab we learn how to build a basic spring application using the xml application context configuration.

After completing this lab you should know how to create a basic spring application
using the xml application context for beans configuration.

Additionally you learn the difference between the two main bean scopes _singleton_ and _prototype_.

## Initial code

In the _initial_ project you find your first spring bean classes (PoJo's) to build a simple address book:

* AddressBook: Container for _Person_ objects
* Person: The person holding address details
* Country: Just an enum defining some countries

In addition you also find the following:

* XmlFileApplication: This is the class to start the application (contains a main() method)
* application.xml: This is an empty xml application context file
 
## Steps to complete

1. Configure beans for _AddressBook_ and _Person_ in already existing _application.xml_ file 
(Please note that multiple instances of a _Person_ are required).

2. Create a xml based classpath spring context in main method of existing class _XmlFileApplication_. Then
get an instance of _AddressBook_ from the instanciated context and print it to system out. 


***Tip:***
If you need any help then consult the [presentation](https://andifalk.github.io/spring-basics-training/presentation/index.html) 
or the [Spring Reference Docs](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-instantiation). 
If you really have no clue you can always look into the finished reference code in _complete_ sub project

