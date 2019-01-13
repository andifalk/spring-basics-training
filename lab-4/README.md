# Lab 4: Aspect Oriented Programming (AOP)
In this lab we get to learn the basics of aspect oriented programming in spring.

After completing this lab you should know the basics of implementing an aspect using the AspectJ annotations in spring.

## Initial code

In the _initial_ project you find the following spring bean classes (PoJo's) to build this application:

* MyBeanOne: A bean implementing an advice for
* MyBeanTwo: Another bean implementing an advice for

In addition you also find the following:

* AopApplication: This is the class to start the application (contains a main() method)
* AopConfiguration: This is a java application context configuration class for enabling AspectJ annotation based AOP
* LoggingAspect: This class is the target for implementing aspects and already contains some sample aspects
 
## Steps to complete

1. Implement a tracing logger for all beans (_MyBeanOne_ and _MyBeanTwo_)
(Use @Around annotation with _ProceedingJoinPoint_).

2. Look at and learn from the other already existing advices (e.g. @Before or @AfterThrowing) 

3. Start the application using _AopApplication_ to see the sequence of the different aspect log outputs. 

***Tip:***
If you need any help then consult the [presentation](https://andifalk.github.io/spring-basics-training/presentation/index.html) 
or the [Spring Reference Docs](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop-api). 
If you really have no clue you can always look into the finished reference code in _complete_ sub project

