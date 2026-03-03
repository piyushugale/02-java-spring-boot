
Client → Controller → Service → DB → Service → Controller → Client


User / Client
    |
    | 1️⃣ Sends HTTP Request (GET/POST/PUT/DELETE)   → could be browser, Postman, mobile app, frontend JS.
    v
    Spring DispatcherServlet                        → Spring’s front controller intercepts all HTTP requests.
    |
    | 2️⃣ Intercepts the request
    v
    REST Controller (@RestController)               → Spring calls the appropriate controller method based on URL mapping (@RequestMapping, @GetMapping, etc.)
    |
    | 3️⃣ Controller method called
    |    e.g., getUser(@PathVariable id)
    v
    Service Layer (optional but recommended)        → Controller delegates to service layer to handle business logic
    |
    | 4️⃣ Business logic executed
    |    e.g., fetch user from database
    v
    Repository / Database Layer                     → Service fetches or updates data from Database
    |
    | 5️⃣ Data fetched (User object)
    v
    Service Layer
    |
    | 6️⃣ Returns object to Controller
    v
    Controller (@RestController)                    → tells Spring to serialize to JSON using Jackson
    |
    | 7️⃣ Spring uses HttpMessageConverter (Jackson)
    |    → Serializes User object to JSON
    v
    HTTP Response (JSON)                            → Client receives JSON response and can render it.
    |
    | 8️⃣ Sent back to Client
    v
User / Client

