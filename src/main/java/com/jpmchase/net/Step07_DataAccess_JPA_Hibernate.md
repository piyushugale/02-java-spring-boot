Data Access with JPA/Hibernate:

> Spring applications often need to store and retrieve data from a database.
> This is handled by JPA (Java Persistence API) and Hibernate, with Spring Data JPA simplifying the process.
-----------------
Spring Data JPA:
1.Hibernate
2.CRUD operations
3.Entity mapping
4.Repositories
-----------------

1️⃣ JPA (Java Persistence API) - specification
> JPA is a specification for mapping Java objects (entities) <--> to relational database tables. 

It defines standard annotations and APIs for:
 - Entity mapping (@Entity, @Table, @Id)
 - CRUD operations (persist, merge, remove, find)

JPA itself is just a standard — it does not implement anything. Hibernate is one implementation.
    
    persist()   : Creates a new record; throws an error if the ID already exists.
    merge()     : Updates existing records or re-syncs data that was disconnected.
    save()      : The smart option; it automatically chooses between creating or updating.
    

2️⃣ Hibernate - implementation
> Hibernate is a popular JPA implementation (ORM framework).

It provides:
- Automatic mapping between Java objects and database tables
- SQL generation
- Caching
- Transaction management

    ```
    @Entity                 : Marks the class as a database table model.
    @Table(name = "users")  : Names the specific database table "users".
    public class User       : The Java template representing a single user record.

    @Id                     : for Primary Key to uniquely identify each row.
    @Column(unique=true)    : for other unique keys 
    @JoinColumn             : for foreign keys

    @GeneratedValue(...)    : Sets the ID to auto-increment automatically.
    private Long id         : The unique ID number for the user record.
    private String name     : A table column that stores the user's name.
    @ManyToOne              : Many records in this table link to one record in another (e.g., Many Comments -> One Post).
    @OneToMany              : One record here links to a list of many others (e.g., One User -> Many Orders).
    @OneToOne               : A strict 1-to-1 relationship (e.g., One User -> One Profile).
    @ManyToMany             : Many records here relate to many there, usually requiring a join table (e.g., Students <-> Courses).
    @JoinColumn             : Defines the Foreign Key column name used to connect the two tables.
    mappedBy                : Tells JPA that the relationship is already defined on the other side to avoid duplicate columns.

    Primary Key	    : @Id
    Unique Key	    : @Column(unique = true) or @UniqueConstraint
    Foreign Key	    : @JoinColumn > to define a column that references a primary key in another table.
    Composite Key   : @IdClass or @EmbeddedId
    ```

Example:

    @Entity
    @Table(name = "employees", uniqueConstraints = {
    @UniqueConstraint(name = "uk_email", columnNames = "work_email")                // 1. Unique Key (Multiple/Named)
    })
    public class Employee {
    
        @Id                                                                         // 2. Primary Key
        @GeneratedValue(strategy = GenerationType.IDENTITY)      OR (uuid)          // tells the app to let the database handle the automatic generation of unique primary key values
        @GeneratedValue(strategy = GenerationType.UUID)
        private Long id;
    
        @Column(unique = true, nullable = false)                                    // 3. Unique Key (Single field)
        private String governmentId;
        private String workEmail;
    
        @ManyToOne
        @JoinColumn(name = "dept_id")                                              // 4. Foreign Key
        private Department department;
    }


```

3️⃣ Spring Data JPA
> Spring Data JPA builds on JPA/Hibernate to simplify database operations.

- You don’t need to write boilerplate CRUD code.
- You define repository interfaces, and Spring automatically provides implementation at runtime.

Example:

    public interface UserRepository extends JpaRepository<User, Long> {
        List<User> findByName(String name);                                             // Custom query method
      }

> JpaRepository<User, Long> ---→ Provides standard CRUD methods out of the box.
    save()          → insert/update
    findById()      → read by ID
    findAll()       → get all records
    deleteById()    → delete by ID


4️⃣ CRUD Operations

    Operation	Method Example	    Description
    ---------------------------------------------------------
    Create	    save(entity)	    Insert new entity
    Read	    findById(id)	    Get entity by primary key
    Update	    save(entity)	    Update existing entity
    Delete	    deleteById(id)	    Delete entity by ID


5️⃣ Entity Mapping
> Entities represent database tables in Java.

Use annotations to map fields to columns:

    @Entity
    public class Employee {
    
        @Id                                             // Auto-mapped to: id - primary key 
        private Long id;                                
        @Column(name = "emp_sys_code")                  // 1. Different Name: Maps 'code' to 'emp_sys_code'
        private String code;                            
        @Column(nullable = false, unique = true)        // 2. NOT NULL Constraint + Unique
        private String email;                           
        private String firstName;                       // 3. Auto-mapped (CamelCase to snake_case): first_name
        private String lastName;                        // 4. Auto-mapped (CamelCase to snake_case): last_name
        private Double salary;                          // 5. Auto-mapped: salary
    }
        
        @Id → Primary key
        @GeneratedValue → Auto-generate IDs
        @Column → Optional column mapping


6️⃣ Repositories- Interfaces for automatic data access
    - The component that handles the code for saving and finding data in the database.
    - You don't write SQL queries or the implementation code to talk to the database. You only define the interface, and Spring generates the logic for you at runtime.
    - Spring Data JPA generates the implementation at runtime.

Example:

    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {
        List<User> findByName(String name);
    }

@Repository → Marks the interface as a Spring bean for data access
Methods like findByName → Spring parses method names into queries automatically

🔹 How it Works Together

Controller calls the Service layer.
Service calls the Repository.
Repository uses JPA/Hibernate to query the database.
Database returns data → Hibernate maps it to Java entity objects.
Service returns objects → Controller → JSON → Client.

✅ Summary:

JPA             → Standard API for ORM
Hibernate       → JPA implementation
Spring Data JPA → Simplifies CRUD operations
Entity Mapping  → Maps Java class to DB table
Repositories    → Interfaces for automatic data access


Controller: The "Receptionist" that handles incoming web requests and sends back responses.
Service: The "Brain" where all the business logic, calculations, and rules live.
Repository: The "Librarian" that handles the code for saving and finding data in the database.
Database: The "Warehouse" where your data is permanently stored and organized.
