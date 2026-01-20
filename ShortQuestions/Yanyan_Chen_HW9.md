**List all of the annotations you learned from class and homework**

@Autowired
Injects required dependencies into a Spring-managed component (constructor injection is preferred).

@Entity
Indicates that the class is a JPA entity mapped to a database table.

@Service
Marks a service-layer component that contains business logic.

@Id
Defines the primary key of the entity.

@GeneratedValue
Specifies how the primary key value is automatically generated.

@Column
Customizes the mapping between a class field and a database column (name, nullable, length, etc.).

@Repository
Marks the data access layer and enables persistence-related exception translation.


@PathVariable
Binds a URI template variable to a method parameter.

@RequestBody
Maps the HTTP request body to a Java object (DTO or entity).

@RestController
Marks the class as a RESTful web controller where methods return data objects (e.g. JSON) instead of views.

@RequestMapping("/path")
Defines the base URL path (and optional HTTP methods) for the controller or handler methods.

**Type out the code for the Comment feature of the class project**

Entity

```
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
    
    // Getters and Setters
}
```

Repository

```
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
```

**What is JPA? What is Hibernate?**

JPA (Java Persistence API) is a Java specification that defines how to manage relational data using Java objects.
Hibernate is an ORM (Object Relational Mapping) framework that implements the JPA specification and provides additional features such as caching and advanced query support.

**What is Hikari? What are the benefits of connection pool?**

HikariCP is a high-performance JDBC connection pool used by Spring Boot by default.
Benefits of connection pooling include:
Faster database access
Reduced connection creation overhead
Better resource management
Improved application performance

**What is @OneToMany, @ManyToOne, @ManyToMany? Give examples**

@OneToMany: One Post has many Comments
@ManyToOne: Many Comments belong to one Post
@ManyToMany: Students and Courses where many students can take many courses
These annotations define relationships between entities.

**What is cascade = CascadeType.ALL, orphanRemoval = true? Other CascadeTypes?**

CascadeType.ALL: Applies all cascade operations (PERSIST, MERGE, REMOVE, REFRESH, DETACH)
orphanRemoval = true: Automatically deletes child entities when they are removed from the parent
Other CascadeTypes:
PERSIST – Save child when parent is saved
MERGE – Merge child updates
REMOVE – Delete child when parent is deleted
REFRESH – Refresh child state
DETACH – Detach child from persistence context

**What is FetchType.LAZY and FetchType.EAGER?**

FetchType.LAZY: Data is loaded only when accessed
FetchType.EAGER: Data is loaded immediately
LAZY is preferred for better performance. EAGER is used when related data is always needed.


**What is the rule of the JPA naming convention?**

JPA allows automatic query generation based on method names.
Examples:
findByTitle
findByUserId
findByEmailAndStatus
deleteByPostId
We do not need to implement these methods manually.

**Use JPA advanced methods in repository layer**

Advanced JPA methods use naming conventions:
findByTitleContaining
findByCreatedAtAfter
findByStatusOrderByCreatedAtDesc
countByUserId
These methods are automatically implemented by Spring Data JPA.
 
**What is JPQL?**

JPQL (Java Persistence Query Language) is an object-oriented query language used to query JPA entities instead of database tables.

**What is @NamedQuery and @NamedQueries?**

@NamedQuery defines a static JPQL query at the entity level.
@NamedQueries is a container for defining multiple named queries

**What is @Query? Where do we write it?**

@Query allows us to write custom SQL or JPQL directly.
It is written in the Repository interface.

**What is HQL and Criteria Queries?**

HQL (Hibernate Query Language) is similar to JPQL but specific to Hibernate
Criteria Queries provide a programmatic and type-safe way to build queries

**What is EntityManager?**

EntityManager is the core JPA interface used to manage entities, execute queries, and control persistence context.

**What is SessionFactory and Session?**

SessionFactory: Creates Hibernate sessions and is thread-safe
Session: Represents a single unit of work with the database

**What is Transaction? How to manage it?**

A transaction ensures data consistency by grouping database operations.
Transactions can be managed using @Transactional or programmatically using EntityManager.

**What is Hibernate Caching?**

Hibernate caching improves performance by storing frequently accessed data.
It includes:
First-level cache (Session level)
Second-level cache (SessionFactory level)
Optional query cache

**Difference between first-level and second-level cache**

First-level cache: Enabled by default, scoped to a session
Second-level cache: Shared across sessions, must be explicitly enabled

**How do you understand @Transactional?**

@Transactional ensures that a method runs within a transaction.
If an exception occurs, all database operations are rolled back automatically, ensuring data consistency.
 

