# Lab 3: Spring Bean Lifecycle
In this lab we get to learn the lifecycle of spring beans.

After completing this lab you should know what steps are performed and which callbacks are called for beans
during startup and shutdown of spring context.

## Initial code

In the _initial_ project you find the following spring bean classes (PoJo's) to build the lifecycle application:

* MyLifecycleBean: A bean already implementing the _SmartLifecycle_ interface

In addition you also find the following:

* SpringLifecycleApplication: This is the class to start the application (contains a main() method)
* SpringLifecycleConfiguration: This is a java application context configuration class for enabling component scan 
 
## Steps to complete

1. Implement the callback interfaces _InitializingBean_ and _DisposableBean_ for _MyLifecycleBean   
(Put a logger info output into each implemented operation).

2. Implement operations annotated with _@PostConstruct_ and _@PreDestroy_ for _MyLifecycleBean   
(Put a logger info output into each implemented operation).

3. Start the application using _SpringLifecycleApplication_ to see the sequence of the different lifecycle callbacks. 

***Tip:***
If you need any help then consult the [presentation](https://andifalk.github.io/spring-basics-training/presentation/index.html) 
or the [Spring Reference Docs](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-lifecycle). 
If you are really have no clue you can always look into the finished reference code in _complete_ sub project

