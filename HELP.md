# IV1201
Project in architecture and design of global applications.

## Description
The website is hosted @ [website](https://safe-fjord-62405.herokuapp.com/).
It is a website for recruiters and recruits. A recruit can log in, upload an application with competences and timestamp for availability.
A recruiter can log in, and fetch available people for a specified date.

### Backend
Java with spring boot

### Frontend
React with redux. See [project](https://github.com/projekttwelve/fronttheend)

### Storage
Postgresql
To connect to the database on Heroku you need to implement some variables in application.property, namely
```java
spring.datasource.username=${HEROKU_USERNAME}
spring.datasource.url=${HEROKU_URL}
spring.datasource.password=${HEROKU_PASSWORD}
```
You can get the variable information from heroku, and then you add them locally.

All calls to database from DAO layer. No logic here.
### Services
Databaseservices handles all communication with DAO layer. Here you can put all logic and errorhandling from the DAO layer. Endpoints from controller layer go through here.
### Controller
This is the accesspoint for users of the application. Specify the address and request method through
```java
@RequestMapping(value = "/api/xxx", method = RequestMethod.xxx)
```
followed by the method. 
### Config
Here you can configure the security of the server. If you wish to have some endpoints being open to the public you can specify those in 
```java
protected void configure
```
All endpoint NOT listed here will require a valid JWT token in the Authorization header. 
You get a JWT token from the /auth endpoint with a username/password combo that exists in database.
### Exception
Create custom exceptions in this folder to help understand what exception was thrown. Remember to add them into the "MyControllerAdvice" in advice folder. They will automatically be caught in the controller and returned to the user accordingly.
### Model
For any custom models you may create them here. They should only represent an object, not do any calls to databases or similar things.
### Cloud
Heroku

### Authentication
The application uses JWT tokens to keep track of logged in users. The token contains username. This is used to authorize endpoints correctly. 

### Testing
Tests are found in the test folder. For frontend tests, see the frontend repo found [here](https://github.com/projekttwelve/fronttheend). Add tests whenever new functionality is created.




# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-security)
* [Flyway Migration](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#howto-execute-flyway-database-migrations-on-startup)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)

