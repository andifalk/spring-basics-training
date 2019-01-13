# Lab 6: Spring Expression Language (SpEL)
In this lab we get to learn to play around with the Spring Expression Language (SpEL).

After completing this lab you should know the basic parts of the SpEL.

## Initial code

In the _initial_ project you find the following spring bean classes (PoJo's) to build this application:

* MyCalculatorService: A simple calculator (can do additions and subtractions)
* MySystemInfo: A simple service holding some system information like *user name* or 'java version*'

In addition you also find the following:

* SpELApplication: This is the class to start the application (contains a main() method)
* SpELConfiguration: This is a java application context configuration class for enabling component scan and importing the
additional xml application context
* application.xml: XML application context file containing definition of *MySystemInfo* bean
 
## Steps to complete

1. Look at the predefined sample SpEL expressions in *SpELApplication* class. 

2. Put a SpEL expression in *value* attribute of *application.xml* in the line `<property name="javaVersion" value=""/>`

2. Play around and add more SpEL to learn more language elements. Remember you can add SpEL definitions 
to the class *SpELApplication* and in the *application.xml* 

***Tip:***
If you need any help then consult the [presentation](https://andifalk.github.io/spring-basics-training/presentation/index.html) 
or the [Spring Reference Docs](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#expressions). 
If you really have no clue you can always look into the finished reference code in _complete_ sub project

