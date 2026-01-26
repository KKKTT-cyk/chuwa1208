#  Jan 20th, HW 9

## 2. Type out the code for the Comment feature of the class project.
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

## 3. In postman, call all of the APIs in PostController and CommentController.

## 4. What is JPA? and what is Hibernate?
JPA (Java Persistence API): a specification/standard (interfaces + rules) for ORM in Java. It defines annotations like @Entity, and APIs like EntityManager. JPA itself is not an implementation.

Hibernate: the most common implementation of JPA. It actually performs the ORM work (SQL generation, caching, dirty checking, etc.). In Spring Boot, when you add spring-boot-starter-data-jpa, Hibernate is usually the default provider.


## 5. What is Hiraki? what is the benefits of connection pool?
HikariCP (often misspelled “Hiraki”) is a JDBC connection pool library, and it’s the default pool in Spring Boot for many versions.

Why connection pooling helps
    - Creating DB connections is expensive; pool reuses existing connections.
    - Improves throughput and reduces latency under load.
    - Controls max connections to prevent DB overload.
    - Provides timeouts/leak detection/metrics (depending on config).

## 6. What is the @OneToMany, @ManyToOne, @ManyToMany? write some examples.
Many-to-One (Comment → Post)
Many comments belong to one post:
```
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "post_id")
private Post post;
```

One-to-Many (Post → Comment)
One post has many comments:
```
@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Comment> comments;
```

Many-to-Many (Student ↔ Course example)
Many students take many courses:
```
@ManyToMany
@JoinTable(
  name = "student_course",
  joinColumns = @JoinColumn(name = "student_id"),
  inverseJoinColumns = @JoinColumn(name = "course_id")
)
private Set<Course> courses = new HashSet<>();
```

## 7. What is the cascade = CascadeType.ALL, orphanRemoval = true? and what are the other CascadeType
and their features? In which situation we choose which one?
Cascade controls which operations on parent automatically apply to child.
- CascadeType.ALL = {PERSIST, MERGE, REMOVE, REFRESH, DETACH}
- PERSIST — saving parent also saves new children
- MERGE — updating parent merges children changes
- REMOVE — deleting parent also deletes children
- REFRESH — refreshing parent refreshes children
- DETACH — detaching parent detaches children

orphanRemoval = true means:
- If a child is removed from the parent collection, it will be deleted from DB.
- Example: post.getComments().remove(comment) → comment row deleted (when transaction flushes).

To choose:
- Use cascade = PERSIST when parent “creates” children (common).
- Use cascade = REMOVE only if child truly cannot exist without parent.
- Use ALL + orphanRemoval=true when child lifecycle is fully owned by parent (Post owns Comment).
- Avoid cascading REMOVE in shared relationships (like Many-to-Many), because children might be shared by other parents.

## 8. What is the fetch = FetchType.LAZY, fetch = FetchType.EAGER? what is the difference? In which situation you choose which one?
- LAZY: related data is loaded only when accessed.
    - Pros: faster initial query, avoids loading huge graphs.
    - Cons: can cause LazyInitializationException if you access relation after session closed.
    
- EAGER: related data loaded immediately with parent.
    - Pros: convenient for small always-needed relations.
    - Cons: can cause big joins / N+1 problems / over-fetching.

Rules:
- Default to LAZY for collections (@OneToMany, @ManyToMany).
- Use EAGER only when relationship is small and always required (and you understand the SQL impact).
- In REST APIs, prefer LAZY + explicit fetching (DTOs, join fetch, entity graph) instead of EAGER everywhere.


## 9. What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list some examples?
- In Spring Data JPA, you can create query methods by naming:
    - findByTitle(String title)
    - findByAuthorAndStatus(String author, Status status)
    - findByCreatedAtBetween(LocalDateTime start, LocalDateTime end)
    - findTop10ByOrderByIdDesc()
    - existsByEmail(String email)
    - countByPostId(Long postId)
    - deleteByPostId(Long postId)

- Do we need to implement them ourselves?
    - No, not for derived query methods on JpaRepository.
    - You implement yourself when:
        - query is too complex for naming,
        - you need joins, fetch-joins, projections, native SQL, etc.

## 10. Try to use JPA advanced methods in your class project. In the repository layer, you need to use the naming convention to use the method provided by JPA.

## 11. 

## 12.

## 13. What is JPQL?
JPQL (Java Persistence Query Language) is a query language defined by JPA that operates on entities and their fields, not database tables and columns.

Key points
    - Object-oriented query language
    - Uses entity names and entity attributes
    - Database-independent
    - Converted to SQL by the JPA provider (Hibernate)

## 14. What is @NamedQuery and @NamedQueries?
They define static JPQL queries that are:
    - Parsed at application startup
    - Reusable
    - Centralized in entity classes

@NamedQuery: 
```
@Entity
@NamedQuery(
  name = "Post.findAll",
  query = "SELECT p FROM Post p"
)
public class Post {}
```

@NamedQueries:
```
@NamedQueries({
  @NamedQuery(
    name = "Post.findByTitle",
    query = "SELECT p FROM Post p WHERE p.title = :title"
  ),
  @NamedQuery(
    name = "Post.findRecent",
    query = "SELECT p FROM Post p ORDER BY p.id DESC"
  )
})
```

## 15. What is @Query? In which Interface we write the sql or JPQL?
@Query allows custom JPQL or native SQL in Spring Data JPA repositories.
Written in Repository interface

## 16. What is HQL and Criteria Queries?
HQL (Hibernate Query Language)
    - Hibernate’s version of JPQL
    - Very similar to JPQL
    - Hibernate-specific extensions

## 17. What is EnityManager?
EntityManager is the core JPA interface for interacting with persistence context.

Responsibilities:
    - Persist entities
    - Find entities
    - Remove entities
    - Manage first-level cache
    - Create queries
    - Control transactions (in pure JPA)

## 18. What is SessionFactory and Session?
These are Hibernate-native APIs.
SessionFactory: 
    - Heavyweight
    - Created once per application
    - Thread-safe
    - Creates Session
Session:
    - Lightweight
    - Not thread-safe
    - Represents one unit of work
    - Manages first-level cache

## 19. What is Transaction? how to manage your transaction?
A transaction is a unit of work that must follow ACID principles:
    - Atomicity
    - Consistency
    - Isolation
    - Durability

Manual transaction (Hibernate)
```
Transaction tx = session.beginTransaction();
session.save(entity);
tx.commit();
```
Spring-managed transaction (preferred)
```
@Transactional
public void createPost(Post post) {
    postRepository.save(post);
}
```

Spring automatically:
    - Begins transaction
    - Commits on success
    - Rolls back on runtime exception

## 20. What is hibernate Caching? Explain Hibernate caching mechanism in detail.
Hibernate uses multi-level caching to reduce database access.

Levels of caching:
    - First-level cache (mandatory)
    - Second-level cache (optional)
    - Query cache (optional)

## 21. What is the difference between first-level cache and second-level cache?
First-Level Cache:
    - Enabled by default
    - Scoped to Session / EntityManager
    - Cannot be disabled
    - Exists per transaction

Second-Level Cache:
    - Shared across sessions
    - Requires configuration (Ehcache, Redis, etc.)
    - Stores entity data across transactions
    - Improves performance in read-heavy apps

## 22. How do you understand @Transactional?
@Transactional enables declarative transaction management in Spring.

What it does:
    - Opens transaction before method
    - Commits if successful
    - Rolls back on unchecked exceptions
    - Binds EntityManager to thread
