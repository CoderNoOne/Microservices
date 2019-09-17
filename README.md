# Microservices

### 1. About
Imagine the frontend applications orchestrate the loan request, the flow of backend processing is as follows:

- calling auth microservice to rgister new user. An oauth token is get in /register api response
- calling customer microservice and loan-applciation microservice through protected gateway microservice using the acces_token obtained in previous step
- saving a loan application and customer details in our system for processing




![user_page](https://i.imgur.com/ad6Qh8Z.jpg)






1) Auth microservice which acts as the authorization service, User data such as username and password is stored in auth microservices's database
2) eureka for service discovery
3) gateway microservice acting as the API gateway to customer-microservice and loan-application microservice
