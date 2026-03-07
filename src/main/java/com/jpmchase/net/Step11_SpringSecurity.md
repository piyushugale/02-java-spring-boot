Spring Security     – Framework to secure Spring Boot apps.
Authentication      – Verify identity of a user.
Authorization       – Control access based on roles/permissions.
Securing Endpoints  – Restrict access to URLs or methods based on roles.

Good to know !
    Web Endpoints   : These are designed for humans;        they return HTML to be rendered as a webpage in a browser.
    REST Endpoints  : These are designed for machines/code; they return raw JSON or XML data for a frontend (React) or another service to use.
    URL vs. Resource: A Web endpoint is a location (e.g: ../home.html), while a REST endpoint represents a resource (e.g: ../api/users).
    Method Usage    : Web endpoints mostly use GET and POST, while REST strictly uses GET, POST, PUT, and DELETE to perform specific actions.
    State           : Web endpoints often use Sessions/Cookies to remember you; REST is Stateless, meaning every request must contain its own credentials (like a JWT token)



## 1. Spring Security 
    - Spring Security is a framework that provides authentication + authorization to secure Spring Boot applications.  
    - It can protect Web endpoints (return html view), REST APIs (returns json/xml), and method-level access.


## 2. Authentication 
    - Authentication is the process of verifying the identity of a user.

Example: Using in-memory authentication in Spring Boot:

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {          // extends spring-security
    
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("USER").and()
                .withUser("admin").password("{noop}admin").roles("ADMIN");
        }
    }
    ```
       -  {noop} means plaintext password (demo use only)
       -  Users user and admin are authenticated with roles.


## 3. Authorization
    - Authorization controls what resources a user can access based on roles or permissions.

How to restrict a specific URL (like /admin/) to only the "ADMIN" role ?
Example: Restricting access to endpoints based on roles:

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                .antMatchers("/admin/").hasRole("ADMIN")                // Only ADMIN access allowed 
                .antMatchers("/user/").hasAnyRole("USER", "ADMIN")      // Both USER, ADMIN access allowed 
                .antMatchers("/").permitAll()                           // Everyone can see the home page
                .and().formLogin();                                     // Enable the default login page
          }



# 4. Securing Endpoints
    - Endpoints in Spring Boot can be secured using: > both can be used simultaneously
        1. HTTP security rules                     (restrcit access at endpoint or API level)  
        2. Method-level security using annotations (fine grained security control even if public endpoints access is allowed)

---
## Example: Method-Level Security

        @RestController
        public class SampleController {
        
            @GetMapping("/admin/data")
            @PreAuthorize("hasRole('ADMIN')")                   // ensures only authorized role can call the method.
            public String adminData() {
                return "Sensitive admin data";
            }
        
            @GetMapping("/user/data")
            @PreAuthorize("hasAnyRole('USER','ADMIN')")         // ensures only authorized role can call the method.
            public String userData() {
                return "User accessible data";
            }
        }

@PreAuthorize ensures only authorized roles can call the method.
/admin/data → accessible only by ADMIN role.
/user/data → accessible by USER or ADMIN roles.