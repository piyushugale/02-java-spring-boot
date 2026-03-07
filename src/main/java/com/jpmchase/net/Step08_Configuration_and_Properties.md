````
# Summary:
- `application.properties`  → default configuration file
- `application.yml`         → hierarchical configuration format
- `profiles`                → separate configs for dev/test/prod environments
- `external configuration`  → override configuration without changing code
````


# Spring Boot Configuration
- Spring Boot allows configuring application behavior using configuration files, profiles, and external configuration.

---

# 1. application.properties (default) 
- `application.properties` is the default configuration file in Spring Boot.

Location: 
- src/main/resources/application.properties

It stores key-value configuration settings such as:
  - server port
  - database connection
  - logging levels
  - custom application properties

Example:
```properties
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
app.name=MySpringApp
```
Accessing property in code:
```java
@Value("${app.name}")
private String appName;
```



# 2. YAML Configuration
- Spring Boot also supports YAML configuration files.

File name:
- application.yml

YAML is more readable and hierarchical compared to `.properties`.

Example:
```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: root
    password: password

app:
  name: MySpringApp
```

Equivalent `.properties` format:
```
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
```

---


# 3. Environment Profiles

Profiles allow different configurations for different environments.

Common environments: dev, test, prod

Example profile files:
```
application-dev.properties
application-test.properties
application-prod.properties
```

```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost/devdb
```

`application-prod.properties`
```properties
server.port=80
spring.datasource.url=jdbc:mysql://prod-server/proddb
```

Activate profile:
```
spring.profiles.active=dev
```

Spring loads:
```
application.properties
+ application-dev.properties
```

---

# 4. External Configuration
- Spring Boot allows configuration outside the application, so the application does not need to be rebuilt.

External configuration sources include:

### Environment variables
SERVER_PORT=9090

### Command-line arguments
java -jar app.jar --server.port=9090

### External configuration file
java -jar app.jar --spring.config.location=/config/application.properties

### Container environments (Docker / Kubernetes)
Configuration can be injected through environment variables.

---

# Configuration Priority (Highest → Lowest)

```
Command-line arguments
Environment variables
External configuration files
application-{profile}.properties
application.properties
```

Spring always applies the highest priority configuration.

---

# Configuration Loading Flow

```
Spring Boot Application Starts
        |
Loads application.properties
        |
Loads profile configuration (application-dev.properties)
        |
Checks external configs / environment variables
        |
Final configuration applied
```

---

# Summary:
- `application.properties`  → default configuration file
- `application.yml`         → hierarchical configuration format
- `profiles`                → separate configs for dev/test/prod environments
- `external configuration`  → override configuration without changing code
