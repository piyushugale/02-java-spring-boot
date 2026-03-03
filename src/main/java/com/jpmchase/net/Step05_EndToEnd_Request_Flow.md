
Client → Controller → Service → DB → Service → Controller → Client

```markdown
# REST Request-Response Flow in Spring Boot

```

User / Client
|
| 1️⃣ Sends HTTP Request (GET/POST/PUT/DELETE) → e.g., browser, Postman, mobile app, frontend JS
v
Spring DispatcherServlet → Spring’s front controller intercepts all HTTP requests
|
| 2️⃣ Intercepts the request
v
REST Controller (@RestController) → Calls appropriate controller method based on URL mapping (@RequestMapping, @GetMapping, etc.)
|
| 3️⃣ Controller method called, e.g., getUser(@PathVariable id)
v
Service Layer (optional but recommended) → Handles business logic delegated by controller
|
| 4️⃣ Business logic executed, e.g., fetch user from database
v
Repository / Database Layer → Service fetches or updates data from database
|
| 5️⃣ Data fetched (User object)
v
Service Layer → Returns object to controller
|
| 6️⃣ Controller (@RestController) tells Spring to serialize object to JSON
v
Spring uses HttpMessageConverter (Jackson) → Serializes User object to JSON
|
| 7️⃣ HTTP Response (JSON)
v
User / Client → Receives JSON response and renders it

```