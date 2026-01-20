# Assignment9 Yichao Chen

### Q1 List all of the annotations you learned from class and homework to annotaitons.md
### Q2 Type out the code for the Comment feature of the class project.
Class comment
```java
@Entity
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String content;
    // Many to one
    @ManyToOne(fetch = FechType.LAZY)
    @JoinColumn(name = "post.id", nullable = false)
    private Post post;
    
    //Getter and Setter
}
```

Repository
```java
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
```
### Q3 In postman, call all of the APIs in PostController and CommentController.
### Q4 What is JPA? and what is Hibernate?
JPA (Java Persistence API) is a Java specification that define how Java Objects are mapped to relational database tables. It provided a standard sef of interface and annotations for ORM(Object Relational Mapping)
Hibernate is a popular ORM framework and a concrete implementation of the JPA specification.
### Q5 What is Hiraki? what is the benefits of connection pool?
Hiraki is a high performance JDBC connecting pool used in Java applications. It manages a pool of reusable database connections so that the applications does not need to create a new database connection for every request
### Q6 What is the `@OneToMany`, `@ManyToOne`, `@ManyToMany?` write some examples.
One to many: defines one entity is associated with multiple instances of another entity\
One Post has many Comments
```java
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
```
Many to One: defines multiple entities are associated with one single entity\
Many Comments belongs to one Post
```java
@Entity
public class Comment{
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
```

Many to many: defines a relationship where multiple entities can be associated with multiple entities\
Students and Courses

```java
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}

@Eneity
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
}
```
### Q7 What is the `cascade = CascadeType.ALL, orphanRemoval = true`? and what are the other CascadeType and their features? In which situation we choose which one?
`cascade = CascadeType.ALL` means that all persistence operations on a parent entity will automatically be applied to its related child entities. These operations include persisting, merging, removing, refreshing, and detaching.\
`orphanRemoval = true` means that if a child entities is removed from the parent's collection, it will also be deleted from the database automatically.\
`CascadeType.PERSIST`: parent entities are saved, the related child will be saved automatically. It is useful when creating new parent and child objects together.\
`CascadeType.MERGE`: parent entities are updated, the related child will be updated as well. It is useful detached entities.\
`CascadeType.REMOVE`: parent entities are deleted, the child will be deleted as well.\
`CascadeType.REFRESH`: parent entity is refreshed from the database, the child entities are also refreshed.\
`CascadeType.DETACH`: parent entity is detached from the persistence context, the child entities are also detached.
### Q8 What is the `fetch = FetchType.LAZY, fetch = FetchType.EAGER`? what is the difference? In which situation you choose which one?
These two are defines when related entities are loaded from the database in JPA relationship. LAZY is means not loaded immediately, they are fetched only when they are explicitly accessed in the code.\
EAGER means that the related entities are loaded immediately together with the parent entity in a single query.\
The main difference between LAZY and EAGER is loading timing and performance impact. LAZY improves the performance by avoiding unnecessary database queries.

LAZY: when the related data is large or not always needed.\
EAGER: when the related data is small and always needed together with parent entity.
### Q9 What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list some examples?
The method name should be composed of a query keyword, entity field names and optionally conditions or sorting keywords.\
In most cases, no implementation is needed. Spring Data JPA automatically generates the query based on the method name as long as it follows the naming convention and matches the entity field names.\
```java
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByAuthor(String author);

    List<Post> findByTitleContaining(String keyword);

    List<Post> findByAuthorAndTitle(String author, String title);

    List<Post> findByCreatedAtAfter(LocalDateTime time);

    List<Post> findByAuthorOrderByCreatedAtDesc(String author);
}
```
### Q10 Try to use JPA advanced methods in your class project. In the repository layer, you need to use the naming convention to use the method provided by JPA.
```java
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByAuthor(String author);

    List<Post> findByTitleContaining(String keyword);

    List<Post> findByAuthorAndTitle(String author, String title);

    List<Post> findByCreatedAtAfter(LocalDateTime time);

    List<Post> findByAuthorOrderByCreatedAtDesc(String author);
}
```
In PostController
```java
@GetMapping
public Lis<Post> searchPosts(@RequestParam String keyword) {
    return postRepository.findByTitleContaining(keyword);
}
```
### Q11 (Optional) Check out a new branch....
### Q12 type the code, you need to check out new branch from ...
### Q13 What is JPQL?
JPQL (Java Persistence Query Language) is an object-oriented query language defined by JPA. It is used to query entities and their field. JPQL queries are written using entity name and property names and are translated by the JPA Provider into SQL at runtime.
```java
import java.util.List;

@Query("SELECT p FROM Post p WHERE p.author = :author")
List<Post> findPostByAuthor(@Param("author") String author);
```
### Q14 What is @NamedQuery and @NamedQueries?
`@NamedQuery `is a JPA annotation used to define a static JPQL query with a name. `@NamedQueries` is a container annotation that allows multiple `@NamedQuery` definitions to be declared on the same entity class.
### Q15 What is @Query? In which Interface we write the sql or JPQL?
@Query is a Spring Data annotation used to define custom queries directly on repository methods. It allows developers to write JPQL or native SQL when method naming conventions are not sufficient to express complex query logic.\
@Query is written in the Repository interface.
### Q16 What is HQL and Criteria Queries?
It is similar to JPQL and is used to query Hibernate entities and their properties, rather than database tables.Criteria Queries are a type-safe, programmatic way to build database queries using Java code instead of writing query strings.
### Q17 What is EnityManager?
`EntityManager` is a core interface defined by JPA that is responsible for managing the lifecycle of entity objects. `EntityManager` tracks entity states such as new, managed, detached, and removed, and synchronizes changes between Java objects and the database.
### Q18 What is SessionFactory and Session?
`SessionFactory `is a Hibernate core interface responsible for creating Session instances. `SessionFactory `holds configuration information, database connection settings, and second-level cache data.\
`Session` is a Hibernate interface that represents a single unit of work with the database. A `Session` is lightweight and not thread-safe, and it should be opened and closed per request or transaction.
### Q19 What is Transaction? how to manage your transaction?
A transaction is a unit of work that consists of one or more database operations. It ensures that all operations either successfully or are rolled back.
### Q20 What is Hibernate Caching? Explain Hibernate caching mechanism in detail.
Hibernate caching is used to improve application performance by reducing the number of database queries. Instead of repeatedly querying the database, Hibernate stores frequently accessed data in memory and reuse it when possible.
### Q21 What is the difference between first-level cache and second-level cache?
First level cache is the default cache associated with a Hibernate Session. It stores entities within a single session and cannot be disabled.
Second-level is an optional cache associated with Hibernate SessionFactory.
### Q22 How do you understand @Transactional? (https://github.com/TAIsRich/tutorial-transaction)
`@Transactional` is a Spring annotation used to define transaction boundaries declaratively. `@Transactional` allows developers to manage transactions without writing explicit transaction control code, making the application cleaner and easier to maintain.

