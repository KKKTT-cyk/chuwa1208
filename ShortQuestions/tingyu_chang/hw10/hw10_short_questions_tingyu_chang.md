### Q1\. Add newly learned annotations to your previous cheatsheet, add explainations for these annotations.
# Java Spring & JPA Annotation Cheatsheet

### 1. Data Persistence Layer (JPA & Hibernate)
*These annotations bridge the Java objects and the database tables.*

| Annotation | Explanation |
| :--- | :--- |
| **`@Entity`** | Marks a plain Java class as a persistable domain object. This tells the JPA provider (like Hibernate) that instances of this class map to rows in a database table. |
| **`@Id`** | Designates a specific field within an entity to serve as the Primary Key. Every entity must have exactly one ID to uniquely identify records. |
| **`@Column`** | Customizes the mapping between a field and a database column. You use this to specify details like the column name (if different from the field), length, or nullability constraints. |
| **`@OneToMany`** | Defines a relationship where a single record in this entity links to a list or set of records in another entity (e.g., one `User` has many `Orders`). |
| **`@ManyToOne`** | The counterpart to `@OneToMany`. It indicates that many instances of this entity are linked to one instance of another (e.g., many `Orders` belong to one `User`). This is usually the "owning" side of the relationship. |
| **`@JoinColumn`** | Explicitly defines the Foreign Key column in the database schema. It is typically placed on the "owning" side of the relationship (the side that holds the foreign key). |
| **`@Repository`** | A specialization of `@Component` used for Data Access Objects (DAOs). Beyond just registering the bean, it enables Spring to catch platform-specific database exceptions and re-throw them as Spring's unified unchecked data access exceptions. |

### 2. Web & Controller Layer (Spring MVC)
*These annotations handle incoming HTTP requests and define how the application responds.*

| Annotation | Explanation |
| :--- | :--- |
| **`@Controller`** | Marks the class as a web handler in the Spring MVC pattern. Traditionally used for serving UI templates (like Thymeleaf or JSP) where the return value resolves to a view name. |
| **`@RestController`** | A convenience annotation that combines `@Controller` and `@ResponseBody`. It indicates that methods in this class write their return values directly to the HTTP response body (usually as JSON) rather than resolving to a view. |
| **`@RequestMapping`** | The general-purpose annotation for mapping web requests to specific handler classes or methods. It allows you to configure the URL path, HTTP method (GET, POST, etc.), headers, and media types. |
| **`@GetMapping`** | A shortcut for `@RequestMapping(method = RequestMethod.GET)`. It is specifically designed to handle requests that retrieve data without side effects. |
| **`@PostMapping`** | A shortcut for `@RequestMapping(method = RequestMethod.POST)`. It is used for operations that create new resources or submit data for processing. |
| **`@RequestBody`** | Applied to a method parameter to trigger automatic deserialization. It takes the inbound HTTP request body (e.g., JSON) and converts it into a Java object. |
| **`@PathVariable`** | Extracts values directly from the URI path (e.g., `/users/{id}`). It binds the dynamic part of the URL to a method parameter. |
| **`@ResponseEntity`** | A wrapper object that represents the entire HTTP response. Returning this gives you programmatic control to set the status code, custom headers, and the response body. |
| **`@ExceptionHandler`** | Defines a method that triggers automatically when a specific exception is thrown within the controller (or globally if used in a ControllerAdvice). It allows you to return graceful error responses instead of stack traces. |


### 3. Core, Configuration & Validation
*These annotations manage dependency injection and data integrity.*

| Annotation | Explanation |
| :--- | :--- |
| **`@Service`** | A specialization of `@Component` used to mark classes that hold business logic. While functionally similar to `@Component`, using this adds semantic clarity, indicating the layer where transactions and business rules reside. |
| **`@Configuration`** | Indicates that a class declares one or more `@Bean` methods. Spring treats this class as a source of bean definitions and processes it to generate the application context. |
| **`@Autowired`** | Instructs Spring to automatically resolve and inject a collaborating bean into the marked field, constructor, or setter. *Note: Constructor injection is generally preferred over field injection.* |
| **`@NotNull`** | A constraint from the Bean Validation API (JSR 380). It guarantees that a field is strictly not null (though it can be empty). This is often used on DTOs to validate inputs before business logic runs. |

---

### Q2\. Walkthrough sample codes under https://github.com/CTYue/springboot-redbook/commits/06_mapper-exception, you are supposed to bring up the application on your local.
* 


---
### Q3\. Explain why do we need model mappers in Spring, and in what scanrios we need it
* Why do we need Model Mappers?
* We need model mappers to separate our Database Layer (Entities) from our API Layer (DTOs).
1. Security: Entities often contain sensitive data (passwords, internal IDs) that shouldn't be exposed to the user. Mappers filter this out.
2. Decoupling: If you change your database schema, you shouldn't have to break the frontend API. Mappers act as a buffer.
3. Preventing Errors: Entities with relationships (like a User having a List of Orders) can cause "Infinite Recursion" errors when converted to JSON. Mappers allow us to create flat, safe objects.

* In what scenarios do we need it?
* Authentication/User Profile: When returning a user object, you must map the `User` entity to a `UserDTO` to ensure the password field is excluded.
* Complex Data Views: When a frontend page needs data from three different tables, you use a mapper to combine those three entities into one single `DashboardDTO`.
* Input Validation: When a user submits a registration form, the data might not match the database columns exactly (e.g., "confirm password" field). You map that input to a temporary object first to validate it before saving.

---

### Q4\. Provide 3 examples in which model mapper will NOT map succesfully, explain why.
1. Field Name Mismatch
* Why: Mappers rely on matching property names. If the Entity has `firstName` but the DTO has `name`, the mapper cannot automatically link them, causing the field to remain null.

2. Incompatible Data Types
* Why: If the source field is a `String` (e.g., "active") and the destination is a `Boolean`, the mapper will fail (or skip the field) because it doesn't know how to convert the values without a custom converter.

3. Missing Getters/Setters
* Why: Mappers generally use reflection to call public methods. If a private field does not have a public `get` or `set` method, the mapper acts as if the property doesn't exist and cannot copy the data.

---

### Q5\. Explain how model mapper cast different data types between source object and target class.
* Model Mapper does not use standard Java casting (e.g., `(int) object`). Instead, it acts as a translation engine that uses Reflection to inspect the data types of both fields and applies the following strategies:
1. Built-in Converters (Implicit):For compatible or standard types, the library uses pre-defined logic to convert the data automatically.
* Primitives & Wrappers: Automatically handles boxing/unboxing (e.g., `int` $\leftrightarrow$ `Integer`).
* String Parsing: Tries to parse Strings into numbers or Booleans (e.g., String `"123"` $\rightarrow$ `int` `123`).
* ToString: Converts objects to strings by calling their `.toString()` method.
2. Custom Converters (Explicit): When the source and target types are completely incompatible (e.g., converting a `List<String>` into a single comma-separated `String`), the mapper relies on a Custom Converter provided by the developer. 
* The mapper detects the type mismatch and looks for a registered function to handle the transformation logic.

---

### Q6\. Add your own API exceptions so that when something wrong happens in service layer, your rest API will return your customized response and status code.


---

### Q7\. Explain how Controller Advices work, is there any other approach to do same/similar global API exception handling?
* How Controller Advice Works: `@ControllerAdvice` acts as a global interceptor (using AOP) that wraps around all controllers. When an exception is thrown anywhere in the application, the `DispatcherServlet` catches it and delegates it to this class. 
* Specific methods annotated with `@ExceptionHandler` inside the advice class then process the error and return a custom HTTP response, like a JSON object to the client.
* Other Approaches:
1. Local `@ExceptionHandler`: You can define exception handlers directly inside a specific Controller class, but this only applies to endpoints within that one controller (not global).

2. `ResponseStatusException`: You can throw this specific exception directly in your business logic (e.g., `throw new ResponseStatusException(HttpStatus.NOT_FOUND)`). It is simple but provides limited control over the JSON response body.

3. `HandlerExceptionResolver`: You can implement this interface for low-level global error handling, though it is more complex and less common than using Controller Advice.

----

### Q8\. What's the difference between throwing a regular exception and a customized API exception that will be eventually thrown to Controller Advice codes? Please provide screenshots to explain your findings


---

### Q9\. Write some regular expression to restrict the value of attributes that your Post or Comment can have. You may use https://regex101.com/ to construct and test/validate your regular expression.





---

### Q10\. Explain Spring framework fundamental principles. And how can they help build business applications?
### Spring Framework Fundamental Principles

### 1. Inversion of Control (IoC) & Dependency Injection (DI)
**Concept:** Basically, instead of your code creating objects itself (like doing `new Database()`), Spring does the heavy lifting for you. It creates the objects and "injects" them into your class when the app starts.
**Business Benefit:**
1. **Loose Coupling:** Your code isn't stuck to one specific implementation, so it's easier to change things later.
2. **Testing:** It makes unit testing way easier because you can swap in "mock" database connections without rewriting your actual code.

### 2. Aspect-Oriented Programming (AOP)
**Concept:** AOP handles the repetitive stuff that isn't part of your main business logic—like logging, security checks, or transactions. You write this logic once, and Spring applies it automatically across your application.
**Business Benefit:**
1. **Clean Code:** Your business methods stay clean and focused, without being cluttered by repetitive logging or error handling code.
2. **Consistency:** It ensures important rules, like security, are applied everywhere automatically, so you don't accidentally forget to secure a new page.

---

### Q11\. Explain different types of dependency injection, explain their suitable use cases, and why fielde injection is not recommended in general. Please provide necessary code snippets and screenshots if possible.
1. Constructor Injection (Recommended)
* This is the most common and generally preferred method. You explicitly define a constructor that accepts the required dependencies as arguments.
* Suitable Use Cases:
* Mandatory Dependencies: Use this when your class absolutely needs the dependency to function. It guarantees the object is never in an invalid state.
* Unit Testing: It makes testing super easy because you can just pass in a "mock" object when you call `new PostServiceImpl(...)`.
* Immutability: It allows you to mark your fields as `final`, ensuring they don't get overwritten later.

```java
@Service
public class PostServiceImpl implements PostService {
    // Allows the field to be final (Immutable)
    private final PostRepository postRepository;

    // Spring automatically finds the bean and passes it here
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}

```
2. Setter Injection
* In this approach, you provide a public setter method annotated with `@Autowired`. Spring creates the object first, and then calls this method to "set" the dependency.
* Suitable Use Cases:
* Optional Dependencies: If a dependency isn't strictly required (e.g., a logger or cache), you can use this. If Spring doesn't find the bean, the class can still work, if you handle the null check.
* Reconfiguration: Technically allows you to swap the dependency after the object is created, though this is rare in practice.

```java
@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    // The dependency is injected after the object is created
    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}

```
3. Field Injection
* This involves placing the `@Autowired` annotation directly on the field itself. It looks the cleanest because it requires the least amount of code.
```java
@Service
public class PostServiceImpl implements PostService {
    // Dependencies are injected via Reflection
    @Autowired
    private PostRepository postRepository;
}

```
* Disadvantages:
1. Difficult Testing: You cannot easily pass "mock" objects for unit tests because there is no constructor or setter to access the field.
2. No Immutability: You cannot make the fields `final`, which prevents you from creating immutable, thread-safe components.
3. Hidden Dependencies: It hides the class's requirements. Unlike a constructor that clearly lists what dependencies are needed, field injection makes it easy to add too many dependencies without realizing it, which creates bloated code.

---


### Q12\.Explain different types of application context in Spring framework, with screenshots. You may take https://github.com/CTYue/springIOC for reference.


---


### Q13\. Compare @Component and @Bean and in which scenario they should be used.



---


### Q14\. Explain Spring bean scopes and how to pick the correct bean scope.



---


### Q15\. Explain the difference between bean id and bean class.


---


### Q16\. Explain that when a bean has multiple alternative implementations, how will Spring decide which bean implementation to inject/autowire?



---






---


