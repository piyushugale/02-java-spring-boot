```
1. REST controllers
2. HTTP methods
3. Request mapping 
4. JSON serialization
```


# 1️⃣ REST Controllers
In Spring Boot, a REST controller is a class that handles HTTP requests and returns data (usually JSON).

It uses @RestController
    ```
    `@RestController` => `@Controller` + `@ResponseBody`

That means:
* It handles web requests
* It automatically converts return values into JSON
    
    ```java
    @RestController
    @RequestMapping("/users")
    public class UserController {
    
        @GetMapping
        public List<String> getUsers() {
            return List.of("John", "Alice");
        }
    }
    ```
    
    Request:
    ```
    GET /users
    ```
    Response:
    ```json
    ["John", "Alice"]
    ```

------------------------------------------------------------------------------------------------------------------------

## 2️⃣ HTTP Methods

REST APIs use standard HTTP methods to perform operations.

| Method | Purpose                | Example           |
|--------|------------------------|-------------------|
| GET    | Read data              | Get all users     |
| POST   | Create new data        | Add new user      |
| PUT    | Update entire resource | Update user       |
| PATCH  | Partial update         | Update email only |
| DELETE | Remove data            | Delete user       |

    ```java
    @PostMapping
    public String createUser(@RequestBody String name) {
        return "User created: " + name;
    }
    ```

------------------------------------------------------------------------------------------------------------------------

## 3️⃣ Request Mapping

Request mapping connects the Endpoints/URLs to controller methods.

Main annotations:
* `@RequestMapping`     → Generic mapping at class level 

* `@GetMapping`         → GET requests
* `@PostMapping`        → POST requests
* `@PutMapping`         → PUT requests
* `@DeleteMapping`      → DELETE requests

Class-level mapping: This means, every endpoint inside this controller will start with /users. Think of it as a base URL.
    
    @RequestMapping("/users")
    ```
Method-level mapping:

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id) {               // @PathVariable is used to extract values from the URL.
        return "User ID: " + id;
    }

Calling:
    GET /users/5

    Returns:
    User ID: 5

Final URL = Class-Level + Method-Level

    @RestController
    @RequestMapping("/users")
    public class UserController {
        @GetMapping("/name")
        public List<String> getUsers() {
            return List.of("John", "Alice");
        }
    }

    - GET /users/name

------------------------------------------------------------------------------------------------------------------------
## 4️⃣ JSON Serialization

Spring Boot automatically converts Java objects into JSON using the Jackson library.
When a controller returns an object:

    public class User 
    {
        private int id;
        private String name;
    
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public User getUser() {
            return new User(1, "John");
        }
    }


Spring converts it into:

    ```json
    {
      "id": 1,
      "name": "John"
    }
    ```

This process is called:
- Serialization   → Java object → JSON
- Deserialization → JSON → Java object

It happens automatically if:

* You use `@RestController`
* You use `@RequestBody`
* Jackson is in the classpath (comes with `spring-boot-starter-web`)

---

## 🔥 How Everything Connects

```
Client → HTTP Request → REST Controller → Service → Response (JSON)
```

---

## 🎯 Interview-Ready Summary

* REST Controller handles HTTP requests and returns JSON.
* HTTP methods define CRUD operations.
* Request mapping binds URLs to controller methods.
* JSON serialization is handled automatically by Jackson.

---

This one will copy cleanly into GitHub or VS Code without issues.
