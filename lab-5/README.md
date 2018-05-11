# Lab 5: Advanced Bean Wiring (Primary, Qualifier, Profile)
In this lab we get to learn the advanced techniques for configuring spring beans.

After completing this lab you should know how to solve dependency injection issues because of multiple 
spring bean candidates.

## Initial code

In the _initial_ project you find the following spring bean classes (PoJo's) to build this application:

* BackupManager: A bean implementing backup using a _Storage_ medium
* Storage: Interface defining a storage medium
* CompactDisc: Implementation of _Storage_ for a compact disc storage medium
* Harddisk: Implementation of _Storage_ for a harddrive disk storage medium
* SolidStateDisc: Implementation of _Storage_ for a solid state disc storage medium

In addition you also find the following:

* MultipleBeansApplication: This is the class to start the application (contains a main() method)
* MultipleBeansConfiguration: This is a java application context configuration class for enabling component scan
 
## Steps to complete

1. Try to start the _MultipleBeansApplication_. You will get an error like 
`expected single matching bean but found 3: compactDisc,harddisk,solidStateDisc`   

2. Now try to solve it by using _@Qualifier_ annotations. In this step the _SSD_ should be the active one for backup 

3. Try another alternative to solve it by using _@Primary_ annotation. This time the _CD_ should used for backup  

4. The last alternative we'll try to solve the problem is by using _@Profile_ annotation. This time the _HD_ should used for backup
Remember: You have to activate the profile at application start by specifying VM system property 
`-Dspring.profiles.active=...`.  

***Tip:***
If you need any help then consult the [presentation](https://andifalk.github.io/spring-basics-training/presentation/index.html) 
or the [Spring Reference Docs](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-scanning-qualifiers). 
If you are really have no clue you can always look into the finished reference code in _complete_ sub project

