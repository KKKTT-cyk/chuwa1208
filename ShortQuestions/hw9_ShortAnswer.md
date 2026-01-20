# Chuwa 0116 HW9 - Short Answer
Delin Liang <br>
Due: Jan 20, 2026 <br>

_Note: for Q1, please check the `annotaitons.md` file_

---
### Q2 - Type out the code for the Comment feature of the class project.
Entity

```java
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

```java
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
```

---
### Q4 - What is JPA? and what is Hibernate?

**JPA (Java Persistence API)** is Java standard specification/standard for **object-relational mapping (ORM)**. It defines a set of interfaces and annotations that describe how objects map to tables and how to perform persistence operations in a consistent way. <br>
_Note: JPS is a and API, not an implementation_

**Hibernate** is a popular **implementation** of the JPA specification. It allows atucal practise of the JPA interfaces and annotations and translates them into SQL, executes queries via JDBC, and maps results back to Java objects.

---
### Q5 - What is Hiraki? what is the benefits of connection pool?

**Hikari (HikariCP)** is a commonly used **JDBC connection pool implementation** (mostly used in Spring). It ensures a high performance and lightweight nature by managing a **reusable** pool of database connections so the application does not need to create a brand-new DB connection for every request.

#### Benefits of Connection Pool
1. **Performance**: Creating a DB connection is expensive; pooling reuses existing connections, reducing latency
2. **Increased Stability and Reliability**: Limits max concurrent connections to prevent exhausting DB resources
3. **Efficiency**: Keeps a set of ready connections to handle traffic spikes better
4. **Tunable Behavior**: Allows configurations of timeouts, max pool size, idle connections, etc., to fit your workload

---
### Q6 - What is the `@OneToMany`, `@ManyToOne`, `@ManyToMany` ? write some examples.
The `@OneToMany`, `@ManyToOne`, `@ManyToMany` annotations are the three most common JPA relationship mappings. They describe **cardinality** (how many records relate to how many) between database tables, expressed in Java entities.

#### `@OneToMany` | 1 : M
* **Meaning:** One parent entity has many child entities
* **Typical DB shape:** `child` table contains a foreign key to `parent` (e.g., `child.parent_id`).
* **Exmaple:**<br>
    a. **Bidirectional Mapping** - One post with many comments <br>
    ```java
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    ```
    _And since this is a bidirectional mapping scenario, the `Comment` class would also have a mapping showing a `@ManyToOne` relationship to the `Posts` class. Deatiled codes will be listed in the next scetion._
    
    b. **Unidirectional**: we usually want to avoid this kind of design because it only defines the association on one side (e.g. only `Posts` knows `Comments` but     `Comments` has no access to `Posts`), while a bidirectional one allows navigation from both sides

#### `@ManyToOne` | M : 1
* **Meaning:** Many child entities belong to one parent
* **Typical DB shape:** `child` table contains a foreign key column to `parent`
* **Exmaple:**<br>
Many Comments to one Post
    ```java
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
    ```

#### `@ManyToMany` | M : N
* **Meaning:** Many entities on each side can relate to many on the other side
* **Typical DB shape:** A **join table** which holds the relationships
* **Exmaple:**<br>
Students and Courses: There can be many students enroll in a course, and one student can enroll in many courses
```java
// =============Students Class====================
@ManyToMany
@JoinTable(
    name = "student_courses",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id")
)
private Set<Course> courses = new HashSet<>();

//================Courses Class=====================
@ManyToMany(mappedBy = "courses")
private Set<Student> students = new HashSet<>();
```

---
### Q7 - What is the `cascade = CascadeType.ALL, orphanRemoval = true` ? and what are the other CascadeType and their features? In which situation we choose which one?

**Cascade**:
Cascading means **propagating** an operation from the owning entity to the related entity. When a record in the owning entity (parent table) is **saved**, **updated** or **deleted**, the change should be reflected in the related entity (child table) as well

`cascade = CascadeType.ALL`:
All cascading operations (`PERSIST`, `MERGE`, `REMOVE`, `REFRESH`, `DETACH`) on owning entity appiled to related entity

`orphanRemoval = true`: <br>
`orphanRemoval` controls what happens when a child entity becomes an “orphan” in a parent’s collectionensures <br> 
`orphanRemoval = true` ensures that if the child entity is removed from the parent collection and it will be the deleted from the database

#### Other CascadeType
1) `CascadeType.PERSIST`<br>
**Feature**: When you save the owning entity (parent), JPA also saves the related entity (child) <br>
**Choose it when**: children are created together with the parent and should be saved in the same operation
    * Example: Creating a `Post` with new `Comments` and saving the `Post` once

2) `CascadeType.MERGE`<br>
**Feature**: Merging the owning entity (parent) would also merge the related entity (child) <br>
**Choose it when**: you update parent and children as a **single object graph**, typical in DTO → entity update flows
    * Example: Update a `Post` and its `Comment`s in one request payload and persist changes together

3) `CascadeType.REFRESH`<br>
**Feature**: Reloading the owning entity (parent) would also refresh the related entity (child) <br>
**Choose it when**: you explicitly use `EntityManager.refresh()` and need the whole graph consistent with DB

4) `CascadeType.REMOVE`<br>
**Feature**: Removing the owning entity would also remove the related entity (deletes child rows when parent is deleted) <br>
**Choose it when**: children **must not exist without parent**, which is also called the "true composition relationship"
    * Example: Deleting a `Post` would also remove all corresponding `Comment`s

5) `CascadeType.DETACH`<br>
**Feature**: detaching the owning entity (parent) from persistence context also detaches the related entity (child) <br>
**Choose it when**: you would like to manually detach entities

---
### Q8 - What is the `fetch = FetchType.LAZY`, `fetch = FetchType.EAGER`? what is the difference? In which situation you choose which one?

#### `FetchType`s
* `LAZY`: load the related entity/collection **only when it is accessed** (on-demand)
* `EAGER`: load the related entity/collection **immediately** when the parent entity is loaded

#### Difference
* `EAGER` is simpler but may load unnecessary data and hurt performance
    * e.g. extra queries / big joins / N+1 risk
* `LAZY` is more efficient by default but can cause `LazyInitializationException` if you access the relation outside the session/transaction 
    * common when returning entities directly

#### When to Use
* Use `LAZY` for relationships that can be large or not always needed (especially `@OneToMany`, `@ManyToMany`).

* Use `EAGER` only when the related data is **small and always required** for the use case

---
### Q9 - What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list some examples?

#### JPA Naming Convention
* A **fixed naming patter** which repository method names follow <br>
    * `find / read / get / query`
    * `By`
    * `FieldName(s)`
    * Optional keywords: `And`, `Or`, `Between`, `Like`, `OrderBy`, etc.
* Field names must **match entity attribute names**, not column names

#### Do we need to implement the method ourselves?
**No** <br>
Spring Data JPA **auto-generates SQL queries** based on the method name at runtime <br>
_You only declare the method in the Repository interface_

#### Examples
```java
findByLastnameAndFirstname(a,b)
findByLastnameOrFirstname(a,b)
findByAgelsNull()
findByAgelsNotNull()
...
```
---
### Q13 - What is JPQL?
**JPQL (Java Persistence Query Language)** is a **platform-independent**, **object-oriented** query language defined by JPA. <br>

These queries are written against **entity classes and fields** (not database tables and columns). They looks similar to SQL in syntax, but works at the **object level**, and is **database-independent**

---
### Q14 - What is `@NamedQuery` and `@NamedQueries`?
#### `@NamedQuery`
* Defines a **static JPQL query** with a name
* Declared at the **entity class level**
* Compiled and validated at application startup<br>

_We use `@NamedQuery` for **reusable**, **stable** queries, and `@Query` for **simple** or **one-off** queries_

#### `@NamedQueries`
A **container** for defining **multiple** `@NamedQuery` annotations on one entity

---
### Q15 - What is `@Query`? In which Interface we write the sql or JPQL?
`@Query` is a Spring Data JPA annotation used to define a custom query (JPQL or native SQL) **directly** on a repository method, instead of relying on method-name conventions

**Where to write?**
In the **repository interfaces** (such as `JpaRepository`) using the `@Query` annotation<br>
* Example:
    ```java
    public interface PostRepository extends JpaRepository<Post, Long> {

        @Query("SELECT p FROM Post p WHERE p.title = :title")
        List<Post> findByTitle(@Param("title") String title);
    }
    ```

---
### Q16 - What is HQL and Criteria Queries?
* **HQL (Hibernate Query Language)** is an object-oriented query language provided by Hibernate
    * It queries **entities and their fields**, not database tables
    * Syntax is similar to SQL, but operates on **Java classes**
    * It is **Hibernate-specific** (not part of standard JPA)


* **Criteria Query** is a **programmatic**, **type-safe** way to build queries using Java code
    * Queries are constructed via Java APIs instead of strings.
    * Checked at compile time, reducing syntax errors.
    * Part of standard JPA (CriteriaBuilder, CriteriaQuery)

* **Key Difference**
    * HQL is to perform **both select and non-select operations** on the data, but Criteria is **only for selecting the data**
    * HQL is suitable for executing **Static Queries**, where as Criteria is suitable for executing **Dynamic Queries**

---
### Q17 - What is EnityManager?
**EntityManager** is the **core JPA interface** used to interact with the persistence context and perform database operations on entities
_Or to say: EntityManager is JPA’s main API for CRUD and querying entities_
* It manages the **lifecycle of entity objects** (persist, find, merge, remove)
* It represents a **persistence context** (first-level cache)
* It **executes JPQL, Criteria queries, and native SQL**

---
### Q18 - What is SessionFactory and Session?
These are both **Hibernate-specific** concepts (not JPA)

#### SessionFactory
* A **thread-safe**, **heavyweight** object
* Created **once** per application
* Responsible for creating `Session` objects
* Holds configuration, mappings, and second-level cache

#### Session
* A **lightweight**, **non-thread-safe** object
* Represents **one unit of work** with the database
* Used for CRUD and queries

_In short: SessionFactory creates Sessions; Session handles database operations for a single transaction_

---
### Q19 - What is Transaction? how to manage your transaction?

A **Transaction** is a sequence of database operations executed as **one logical unit of work**, folloing the ACID properties.<br>
_ACID: Atomicity, Consistency, Isolation, Durability_

#### How to manage?
1. **Declarative** (Recommended – Spring): Use `@Transactional`
    * Spring automatically begin / commit / rollback
    * Rollback happens on `RuntimeException`
2. **Programmatic** (Manual Control): Using `EntityManager` or Hibernate `Session`

---
### Q20 - What is hibernate Caching? Explain Hibernate caching mechanism in detail.
**Hibernate Caching** is a performance optimization mechanism that reduces database access by storing frequently used data in memory<br>
The goal is to **minimize SQL queries**, **reduce latency**, and **improve throughput**

#### Details
1. **First-Level Cache (L1 Cache)**
    * Scope: `Session`
    * Cannot be disabled
    * Each Hibernate Session has its own cache
    * Same entity queried multiple times in the same session hits memory, not DB
    * Automatically managed by Hibernate
    * Cleared when session closes
    * Guarantees entity identity inside a session

2. **Second-Level Cache (L2 Cache)**
    * Scope: SessionFactory (shared across sessions)
    * Must be configured
    * Stores entity data across multiple sessions
    * Requires a cache provider (e.g. Ehcache, Caffeine, Redis)

3. **Query Cache**
    * Purpose: Cache query result IDs (not entity data)
    * Must be used with second-level cache
    * Stores the list of primary keys
    * Entities are still fetched from L2 cache

---
### Q21 - What is the difference between first-level cache and second-level cache?

| Aspect                 | First-Level Cache (L1)                    | Second-Level Cache (L2)                  |
| ---------------------- | ----------------------------------------- | ---------------------------------------- |
| Scope                  | `Session`                                 | `SessionFactory`                         |
| Enabled by default     | Yes                                       | No (must be configured)                  |
| Shared across sessions | No                                        | Yes                                      |
| Managed by             | Hibernate                                 | Cache provider (Ehcache, Caffeine, etc.) |
| Lifecycle              | Exists during one session                 | Lives as long as SessionFactory          |
| Purpose                | Avoid repeated DB hits within one session | Avoid DB hits across sessions            |

In short:
* L1 cache is automatic and guarantees entity identity inside a single session.
* L2 cache is optional, shared, and mainly used to improve performance in read-heavy applications across multiple sessions.

---
### Q22 - How do you understand @Transactional?
`@Transactional` is a Spring annotation used to **manage database transactions declaratively** <br>
It ensures a group of database operations are executed as **one atomic unit**

#### Core Idea
**Either all operations succeed, or all are rolled back**
* If both succeed → COMMIT
* If any fails → ROLLBACK

#### What `@Transactional` Does
Spring automatically:
1. Opens a transaction before the method starts
2. Commits the transaction if the method finishes normally
3. Rolls back the transaction if a runtime exception occurs

#### Rollback Rules
* Rollback on `RuntimeException` / `Error`
* No rollback on checked Exception by default

#### Where to Use
* Service layer (best practice)
* Methods involving multiple DB operations
* Business logic that must stay consistent

#### Key Attributes (Common)
`rollbackFor`, `propagation`, `isolation`, `readOnly`
