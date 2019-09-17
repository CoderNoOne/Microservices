# Microservices

### 1. About
Imagine the frontend applications orchestrate the loan request, the flow of backend processing is as follows:

- calling auth microservice to rgister new user. An oauth token is get in /register api response
- calling customer microservice and loan-applciation microservice through protected gateway microservice using the acces_token obtained in previous step
- saving a loan application and customer details in our system for processing




![microservice architecture](https://i.imgur.com/ad6Qh8Z.jpg)




1) Auth microservice which acts as the authorization service, User data such as username and password is stored in auth microservices's database
2) eureka for service discovery
3) gateway microservice acting as the API gateway to customer-microservice and loan-application microservice

### 2. Screenshots

User registration:

![user registration](https://i.imgur.com/iIUY2a3.jpg)

Getting JWT token:

![JWT token](https://i.imgur.com/FCpPYPg.jpg)

Registering microservices: customer-microservice and loan-application-microservice in Eureka

![microservice registration](https://i.imgur.com/FzjnxEL.jpg)

Saving customer via configured Gateway:

![saving customer](https://i.imgur.com/pGsrbHr.jpg)

Saving loan via configured Gateway:

![saving loan](https://i.imgur.com/s8lKweJ.jpg)

Getting all loans for specified customer:

![getting all loans for customer](https://i.imgur.com/ojDuG6C.jpg)

### 3. Build with
* jdk 1.8 - if you decide to use jdk 9+, you 'll need to add avax.xml.bind dependency  
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring boot]() - starting point for building Spring-based applications with minimized upfront configuration of Spring
***
### 4. Main dependencies, libraries, frameworks and technologies:

* [spring boot](https://spring.io/projects/spring-boot) - it's used to build stand-alone and production ready spring applications.
* [spring boot cloud](https://spring.io/projects/spring-cloud) -  provides tools to quickly build some of the common patterns in distributed systems (service discovery, circuit breakers and so on)
* [spring boot actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html) - mainly used to expose operational information about the running application – health, metrics, info etc.
* [spring data jpa](https://spring.io/projects/spring-data-jpa) - part of the larger Spring Data family, deals with enhanced support for JPA based data access layers
* [JSR-303/JSR-349 Bean Validation]() - specification of the Java API for JavaBean validation in Java EE and Java SE, provides an easy   way of ensuring that the properties of JavaBean have the right values in them. Used to validate forms.
* [lombok](https://projectlombok.org/) - minimized boilerplate code, used also to generate a logger field
* [mapStruct](http://mapstruct.org/) - used to map between different object models (entities and DTOs)
* [Junit 5](https://junit.org/junit5/docs/current/user-guide/) - one of the most popular unit-testing frameworks in the Java ecosystem
* [Mockito 3](https://site.mockito.org/) -  JAVA-based library that is used to mock interfaces so that a dummy functionality can be added to a mock interface that can be used in unit testing
* [Hamcrest](http://hamcrest.org/JavaHamcrest/) - a framework for writing matcher objects allowing 'match' rules to be defined declaratively


