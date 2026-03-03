# 🌱 Spring Boot Auto-Configuration

## 📌 What is Auto-Configuration?

    Spring Boot automatically configures your application based on the dependencies present in your project.
    You do not need to manually configure most beans or write XML configuration.

src/main/resources/META-INF/spring.factories
    META-INF/spring.factories is a special configuration file used to register auto-configuration classes and other framework extensions.

    In Spring Boot 2.x, spring.factories (located at src/main/resources/META-INF/spring.factories) was used for auto-configuration registration.
    Starting with Spring Boot 3.x, it is replaced by spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports.

Explanation:
    Spring Boot checks your dependencies (like web, JPA, or messaging starters).
    It automatically creates beans and default configs for them.
    Your application just runs, no extra setup needed.

💡 Tip: You can always override these defaults by defining your own beans.
---

## 🚀 Example: Using spring-boot-starter-web

If you add this dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

Spring Boot automatically configures:

- DispatcherServlet
- Jackson (JSON converter)
- Embedded Tomcat server
- Spring MVC setup

Now you only need to write:

```java
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
```

No:
- XML configuration
- Manual servlet setup
- External Tomcat configuration

---

## 🗄 Example: Using spring-boot-starter-data-jpa

If you add:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

Spring Boot automatically configures:

- DataSource
- EntityManager
- TransactionManager
- Hibernate

You just provide database properties in `application.properties`.
---


## ⚙ How Auto-Configuration Works ???

Spring Boot checks:

- What dependencies are available in the classpath
- What configuration properties are defined
- What environment settings are present

Based on this, it automatically creates and configures required beans.

---

## 🎯 Interview One-Liner

Auto-Configuration in Spring Boot automatically sets up required beans and configurations based on project dependencies, reducing manual setup and boilerplate code.

---

## 📌 Summary

Spring Boot = Convention over configuration  
Auto-Configuration = Less manual setup, faster development, production-ready defaults