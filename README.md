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

