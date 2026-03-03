Dependency Injection	
- @Autowired
- beans
- inversion of control
- component scanning


# 🔹 Dependency Injection (DI)

Dependency Injection is a design pattern where SPRING provides the required dependencies to a class instead of the class creating them itself.
> “Don’t create your dependencies, let the Spring framework inject them.”
- This promotes loose coupling and testable code.



## 1️⃣ Inversion of Control (IoC)

- IoC is the principle behind DI.
- It means: the control of (object creation + dependencies) is inverted from your code to the Spring container.

Without IoC:

    public class UserService {
        private UserRepository repo = new UserRepository();     // ❌ tightly coupled
    }
    
With IoC (Spring creates & injects):
    
    ```
    @Component
    public class UserService {
        private final UserRepository repo;
    
        @Autowired
        public UserService(UserRepository repo) {               // ✅ dependency injected - This is a constructor for the UserService class. 
                                                                      (A constructor is a special method that is called when a new object of the class is created.)
            this.repo = repo;
        }
    }
    ```



## 2️⃣ Beans

> A bean is an object that is managed by the Spring container.
 Any class annotated with `@Component`, `@Service`, `@Repository`, `@Controller` becomes a Spring bean.

 @Component  → Generic bean, any class
 @Service    → marks a class containing business logic.
 @Repository → marks a class that accesses the database; handles exceptions.
 @Controller → handles web requests and returns views.
 @RestController → handles web requests and returns JSON directly.

AOP (Aspect Oriented Programming) 
- It allows you to separate cross-cutting concerns from your main business logic.
- Instead of writing logging code in every method, you define it once in an aspect, and Spring automatically applies it where needed.

Example of bean -
> Spring can create object and inject it into other classes automatically using @Autowired.

   ```java
   @Component
   public class UserRepository {
       // Spring manages this bean
   }
   ```



## 3️⃣ @Autowired

 `@Autowired` tells Spring:

> “Inject the required bean here automatically”

It can be used on:

 Constructor (recommended)
 Field (less recommended)
 Setter method

Example (constructor injection):

    ```java
    @Service
    public class UserService {
    
        private final UserRepository repo;                            // private: keep data safe + final: immutable 
    
        @Autowired
        public UserService(UserRepository repo) {
            this.repo = repo;
        }
    }
    ```

Spring will automatically inject the `UserRepository` bean here.

---

## 4️⃣ Component Scanning

> Spring automatically scans packages for the classes annotated with `@Component`, `@Service`, `@Repository`, `@Controller`, etc.
 This is called component scanning.

By default, Spring scans the package of the main() application class and its sub-packages.

com.example.myapp
             ├─ MyApp.java       ← package scanned automatically
             ├─ controller/
             ├─ service/
             └─ repository/

> All beans in controller, service, repository will be detected automatically.

---

### 🔹 How it All Connects

```
Spring Container
  |-- Scans packages → detects beans (@Component, @Service, etc.)
  |-- Injects dependencies → @Autowired
  |-- Class now ready to use → IoC & DI
```

### Summary:

 Dependency Injection (DI)  : Spring provides dependencies instead of class creating them.
 Inversion of Control (IoC) : Spring controls object creation and wiring.
 Beans                      : Objects managed by Spring container.
 @Autowired                 : Automatically injects dependencies.
 Component Scanning         : Spring automatically detects beans in packages.

