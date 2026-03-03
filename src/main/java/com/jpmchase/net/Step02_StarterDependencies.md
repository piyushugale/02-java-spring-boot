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

