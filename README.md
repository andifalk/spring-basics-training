[![License](https://img.shields.io/badge/License-Apache%20License%202.0-brightgreen.svg)][1]
[![Build Status](https://travis-ci.org/andifalk/spring-basics-training.svg?branch=master)](https://travis-ci.org/andifalk/spring-basics-training)


# Spring Basics Training
Spring basics training is a basic Spring Framework and Spring Boot training. 
In this course, students build a Spring Java application including the Spring Core Framework and related modules like Spring Boot, Spring MVC, Spring Data, Spring Security and Spring Testing with lots of hands-on labs.

All training modules are based on current __Spring version 5.1.x__ and __Spring Boot 2.1.x.__

Depending on the intensity in diving into hands-on labs of the spring world this training is targeted
as a 1-day or 2-days training workshop.

## Presentation

["Spring Basics Training" Presentation (html)](https://andifalk.github.io/spring-basics-training/presentation/index.html)

## Labs (Hands-On)

### System Requirements

* A Java SDK (at least version 8, tested with JDK versions 8, 9 and 11)
* Any Java IDE ([Eclipse](https://www.eclipse.org/), [Spring Toolsuite](https://spring.io/tools), [IntelliJ](https://www.jetbrains.com/idea/), [VS Code](https://code.visualstudio.com/), [NetBeans](https://netbeans.org/), ...)

### Setup

All labs contain _initial_ and _complete_ directories.
In _init_ directory you find all required source code to start and complete the lab. In _complete_ directory 
you find the complete solution of the lab.

As we are building the samples using [Gradle](https://gradle.org) your Java IDE should be capable use this.

* As [IntelliJ](https://www.jetbrains.com/idea/) user support for Gradle is included by default.
* As a [Visual Studio Code](https://code.visualstudio.com/) user you have to install the following extensions: _java extension pack_, _spring boot extension pack_ and _gradle language support_.
* As an [Eclipse](https://www.eclipse.org/) or [Spring ToolSuite](https://spring.io/tools) user you have to install a plugin via the marketplace

![eclipse](eclipse_gradle.png "eclipse")

To get the workshop project you either can just clone the repository using

```
https://github.com/andifalk/spring-basics-training.git
```

or

```
 git@github.com:andifalk/spring-basics-training.git
```

or simply download it as a [zip archive](https://github.com/andifalk/spring-basics-training/archive/master.zip).

After that you can import the workshop project into your IDE

* [IntelliJ](https://www.jetbrains.com/idea): "New project from existing sources..."
* [Eclipse](https://www.eclipse.org/) or [Spring ToolSuite](https://spring.io/tools): "Import/Gradle/Existing gradle project"
* [Visual Studio Code](https://code.visualstudio.com/): Just open the root directory

### Table of Contents

1. Core Spring Labs
    1. [Beans and Application Context (XML)](https://github.com/andifalk/spring-basics-training/tree/master/lab-1)
    2. [Beans and Application Context (Java Annotations, Component Scanning)](https://github.com/andifalk/spring-basics-training/tree/master/lab-2) 
    3. [Lifecycle of Spring Beans](https://github.com/andifalk/spring-basics-training/tree/master/lab-3)	
    4. [Aspect Oriented Programming (Just some basics)](https://github.com/andifalk/spring-basics-training/tree/master/lab-4)
    5. [Advanced Bean Wiring (Qualifier, Profile)](https://github.com/andifalk/spring-basics-training/tree/master/lab-5)
    6. [Spring Expression Language (SpEL)](https://github.com/andifalk/spring-basics-training/tree/master/lab-6)
2. Spring Boot Labs
    1. [Create a new App using 'start.spring.io'](https://github.com/andifalk/spring-basics-training/tree/master/lab-7)
    2. [Externalized Configuration (Property and YAML)](https://github.com/andifalk/spring-basics-training/tree/master/lab-8)
    3. [DevOps: Logging and Monitoring (Actuator)](https://github.com/andifalk/spring-basics-training/tree/master/lab-9)
3. Spring Data Labs
    1. [Spring Data JPA](https://github.com/andifalk/spring-basics-training/tree/master/lab-10)
    2. [Flyway Database Migrations](https://github.com/andifalk/spring-basics-training/tree/master/lab-11)
4. Spring MVC Labs
    1. [RESTful Services](https://github.com/andifalk/spring-basics-training/tree/master/lab-12)
    2. [RESTful Services with HATEOAS](https://github.com/andifalk/spring-basics-training/tree/master/lab-13)
5. Spring Security Labs
    1. [Authentication and Authorization](https://github.com/andifalk/spring-basics-training/tree/master/lab-14)
    2. Actuator Security (*Upcoming*)
6. Testing Labs
    1. [Unit and Integration Tests (Test slices)](https://github.com/andifalk/spring-basics-training/tree/master/lab-15)
    2. Security Tests (*Upcoming*)
7. Deploy Spring Boot Applications
    1. CloudFoundry (*Upcoming*)
    2. Kubernetes (*Upcoming*)

## License

Apache 2.0 licensed

Copyright (c) by 2019 Andreas Falk

[1]:http://www.apache.org/licenses/LICENSE-2.0.txt
