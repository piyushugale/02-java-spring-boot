| Step   | Flow                                     |
|--------|------------------------------------------|
| 1      | Client sends request                     |
| 2      | Controller processes request             |
| 3      | Exception occurs                         |
| 4      | `@ControllerAdvice` catches exception    |
| 5      | Custom error response created            |
| 6      | JSON error response returned to client   |
| ------ | ---------------------------------------- |


# Error Handling in Spring Boot
Error handling ensures that when something goes wrong in the application, the system returns clear and meaningful responses instead of crashing.
Spring provides mechanisms like exception handling, custom error responses, `@ControllerAdvice`, and global error handling.

---

# 1. Exception Handling
- Exceptions occur when something unexpected happens during program execution.

Example:
```java
public int divide(int a, int b) {
    return a / b;                           // may throw ArithmeticException if b = 0
}
```

Handling exceptions using `try-catch`:

```java
try {
    int result = divide(10, 0);
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
}
```

In Spring applications, exceptions are usually handled centrally rather than inside every method.

---

# 2. Custom Error Responses
- Instead of returning raw stack traces, APIs should return structured error responses.

Example error response JSON:
```json
{
  "timestamp": "2026-03-06T12:00:00",
  "status": 404,
  "error": "User Not Found",
  "message": "User with id 10 does not exist"
}
```

Create a custom exception:

```java
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
```
Throw the exception:
```java
if(user == null){
    throw new UserNotFoundException("User not found");
}
```

---

# 3. @ControllerAdvice
- `@ControllerAdvice` is used to handle exceptions globally across multiple controllers.
- It allows you to separate error-handling logic from controller code.

Example:

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
```

How it works:

1. Controller throws an exception
2. Spring detects the exception
3. `@ControllerAdvice` handles it
4. Proper HTTP response is returned

---

# 4. Global Error Handling
- Global error handling ensures all exceptions are handled in one place.

Benefits:
- Cleaner controllers
- Consistent API responses
- Easier maintenance

Example:

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Specific Catch: Handles 404s when a user is missing
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // 404
                .body(ex.getMessage());
    }

    // 2. Specific Catch: Handles 400s for bad input (Validation)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400
                .body("Invalid request: " + ex.getMessage());
    }

    // 3. The Global "Safety Net": Handles everything else (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                .body("An unexpected server error occurred.");
    }
}

```

`@RestControllerAdvice` = `@ControllerAdvice` + `@ResponseBody`

It automatically returns JSON responses.

---

# Error Handling Flow

```
Client Request
      |
Controller
      |
Exception Occurs
      |
@ControllerAdvice / Global Handler
      |
Custom Error Response Created
      |
HTTP Response Sent to Client
```

---

# Summary

- Exception Handling → Handles runtime errors in the application
- Custom Error Responses → Structured error messages for APIs
- @ControllerAdvice → Centralized exception handling for controllers
- Global Error Handling → Handles all application errors in one place