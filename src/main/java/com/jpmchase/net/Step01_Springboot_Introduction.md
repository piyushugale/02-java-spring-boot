What is Spring Boot?
https://www.youtube.com/watch?v=zJgwVppKXJ0&list=PLUcsbZa0qzu0gVRFlVfscqjD84TqMssOt

Spring Boot = Spring Framework + Auto Configuration + Conventions + Tools

SpringBoot starters - Dependency Descriptors
    Spring Boot Starters combine all the necessary libraries(JAR files) for particular feature or technology into Single dependency.    
    for example:

    spring-boot-starter-web         → for REST APIs (includes MVC, Tomcat, JSON, etc.)
    spring-boot-starter-data-jpa    → for database + JPA
    spring-boot-starter-security    → for authentication
    
    spring-boot-starter-web dependency --
    web app requires:
        - web.jar
        - web-mvc.jar
        - validation.jar
        - tomcat.jar
        - logging.jar

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>4.0.2</version>
        <scope>compile</scope>
    </dependency>

    
    spring-boot-starter-web             RestAPI
    spring-boot-starter-data-jpa        postgresql (driver)
    spring-cloud-starter-aws-messaging (sqs)
    spring-boot-starter-security        (if authentication required)
    spring-boot-starter-actuator        (for monitoring)
    spring-boot-starter-validation      (for request validation)
    spring-boot-starter-test            (for testing)





class OrderService{
    private PaymentService paymentService = new PaymentService();  // tightly coupled    
}

1. Tight Coupling - 
   - If object created with 'new' keyword (e.g PaymentService) tomorrow if we want to switch to another service StripePaymentService 
     we will have to EDIT the code, recompile OrderService 
2. Hard to Test -
   - Cannot easily replace PaymentService with a mock for testing 
3. Scattered Object Creation - 
   - Every class creates its own object using 'new', spreading object lifecycle and config logic throughout the application 

These problems are solved using -
- Dependency Injection 
- IOC (Inversion of Control) Container (Spring)

Springboot is taking away the headache of managing the code to create the object lifecycle


# 🌱 Spring Framework vs Spring Boot (With Examples)

## 📌 1️⃣ What is Spring Framework?

Spring Framework is a powerful Java framework used to build enterprise-level applications.

It mainly provides:

- Dependency Injection (DI)
- Inversion of Control (IoC)
- Aspect-Oriented Programming (AOP)
- Transaction Management
- MVC Architecture
- Integration with Hibernate, JDBC, etc.

---

## 🔹 Example: Dependency Injection (Without Spring)

```java
class Service {
    void serve() {
        System.out.println("Service logic");
    }
}

class Controller {
    Service service = new Service(); // tightly coupled

    void execute() {
        service.serve();
    }
}
```

Problem:
- Controller directly creates Service.
- Hard to test.
- Tightly coupled.

---

## 🔹 Example: Dependency Injection (With Spring)

```java
@Component
class Service {
    void serve() {
        System.out.println("Service logic");
    }
}

@Component
class Controller {

    private final Service service;

    @Autowired
    Controller(Service service) {
        this.service = service;
    }

    void execute() {
        service.serve();
    }
}
```

Now:
- Spring creates objects.
- Spring injects dependencies.
- Loose coupling.
- Easy testing.

---

# 🚀 2️⃣ Why Spring Boot Came?

Spring Framework was powerful but required heavy configuration:

- XML files
- web.xml setup
- Manual Tomcat configuration
- Manual dependency management

Setup time was high.

Spring Boot was created to simplify everything.

---

## 🔹 Before Spring Boot (Traditional Spring Setup)

You needed:
- web.xml
- DispatcherServlet config
- DataSource config
- External Tomcat server
- Multiple XML files

Example:

```xml
<bean id="viewResolver"
      class="org.springframework.web.servlet.view.InternalResourceViewResolver">
</bean>
```

Too much configuration.

---

## 🔹 After Spring Boot (Minimal Setup)

```java
@SpringBootApplication
@RestController
public class Application {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

Run → Application starts.

No:
- XML
- Manual server
- Manual configuration

Embedded Tomcat runs automatically.

---

# 🆚 Spring vs Spring Boot

| Feature | Spring Framework | Spring Boot |
|----------|------------------|-------------|
| Configuration | Manual | Auto-configured |
| XML Usage | Common | Rare |
| Server Setup | External server | Embedded server |
| Setup Time | High | Very Low |
| Microservices | Complex | Very Easy |
| Production Ready | Manual setup | Built-in support |

---

# 🏦 Real-World Architecture Example

Modern backend system:

Controller → Service → Repository → Database

Example:

```java
@RestController
class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
```

Used in:
- Banking systems
- Payment gateways
- E-commerce backends
- Microservices architecture

---

# 🎯 Interview One-Liner

Spring Framework provides core infrastructure like Dependency Injection and MVC.

Spring Boot makes Spring development fast, simple, and production-ready using auto-configuration and embedded servers.

---

# 📌 Final Summary

Spring = Powerful foundation framework  
Spring Boot = Simplified, fast, modern Spring development

Most modern Java backend jobs use Spring Boot.