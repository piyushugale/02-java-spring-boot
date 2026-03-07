# Spring Boot Testing Cheat Sheet

## 1. Types of Testing
- Unit Testing          – Test one class/method independently, without external systems.
- Integration Testing   – Test interaction between multiple components (service, repository, database).

## 2. Testing Frameworks
- JUnit                 – Framework to write and run Java test cases.
- Mockito               – Library to mock dependencies in unit tests.

## 3. Spring Boot Test Annotations
- `@WebMvcTest`         – Loads only CONTROLLER layer for web-layer testing.
- `@DataJpaTest`        – Loads only JPA REPOSITORIES with an in-memory database.
- `@SpringBootTest`     – Loads COMPLETE Spring Boot APPLICATION context for integration testing.
- `@MockBean`           – Adds a mock bean in Spring context to replace a real dependency.

## 4. Test Setup & Cleanup
- `@BeforeEach`         – Runs before every test to set up data.
- `@AfterEach`          – Runs after every test for cleanup.
- Test Configuration    – Special configuration used only in testing, e.g., test DB or profile.
  Test Configuration ensures your tests are fast, safe, and don't accidentally delete real data

  1. The "H2" In-Memory Database (Most Common)
     - Production: Uses a heavy cloud database like AWS RDS (PostgreSQL).
     - Test Config: Uses H2, a tiny database that lives in your computer's RAM.
     - Why? It starts in 1 second, is wiped clean after every test, and doesn't require a cloud connection.

  2. The @ActiveProfiles("test") Annotation
     - You create a file called application-test.properties. Inside, you put "fake" credentials:
     - When you run tests, Spring ignores your real dev or prod settings and uses these instead.
      
     # application-test.properties
        spring.datasource.url=jdbc:h2:mem:testdb
        spring.mail.host=localhost
        cloud.aws.s3.bucket=mock-bucket

  3. Mocking External Services
     - If your app calls a JPMorgan Payment API, you don't want to send real money during a test.
     - Test Config: You define a "Mock Bean."
     - Result: When the code calls the Payment API, the test config intercepts it and simply returns a fake "Success" message.

What happens behind the back?
     - When you use a Mock, Spring swaps the real PaymentService with Fake/Mock Object
     - Object doesn't open a network connection nor call the actual API
     - Your code thinks it talked to the real API and got a success message

Example:
Real Service Perspective: "I am a real WeatherService. When the App calls me, I'm going to spend 5 seconds connecting to a satellite and return 25°C."
Mock Service Perspective: "I am a fake WeatherService. I look exactly like the real one, but I have no 'brain'. 
                           When the App calls me, I don't touch a satellite; I just shout 5°C immediately because the Test told me to."



## 5. Tools & Utilities
- Mocking – Create fake objects to simulate dependencies.
- Assertions – Methods to verify expected results in tests.
- TestContainers – Run real databases/services in Docker for testing.
- H2 Database – In-memory database used in tests instead of a real one.  

