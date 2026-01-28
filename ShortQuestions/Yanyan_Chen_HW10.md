# Q1. Add Newly Learned Annotations to Cheat Sheet (With Explanations)

### @Configuration
Marks a class as a source of bean definitions. Spring will scan it and register beans defined inside it.

### @Bean
Declares a bean managed by the Spring IoC container. Commonly used to register third-party objects (e.g., `ModelMapper`) that cannot be annotated with `@Component`.

### @ControllerAdvice
A specialization for global exception handling across all controllers. It can intercept exceptions thrown by controller methods and return consistent API responses.

### @ExceptionHandler
Used inside `@ControllerAdvice` (or a controller) to handle specific exception types and customize the HTTP response.

### @Valid
Triggers Bean Validation on method parameters (typically DTOs in the controller layer). If validation fails, Spring throws `MethodArgumentNotValidException`.

### @NotBlank / @Size / @Pattern
Bean Validation annotations used to enforce constraints on string fields, such as non-empty values, length limits, and regex-based allowed patterns.

### @Autowired
Injects dependencies from the Spring container. Constructor injection is preferred over field injection for better testability and immutability.

### @Service / @Repository / @Component
Stereotype annotations used for component scanning.
- `@Service` is used for the service layer.
- `@Repository` is used for the data access layer and enables exception translation.
- `@Component` is used for generic Spring-managed components.

---

# Q2. Walk Through Sample Code Under Commit `06_mapper-exception`

1. Update `application.properties` to point to my local MySQL database (URL, username, and password).

<img width="2874" height="1538" alt="image" src="https://github.com/user-attachments/assets/d384e8f7-29ae-4929-bf64-649156b15575" />

2. Local Database includes redbook.

<img width="1128" height="738" alt="image" src="https://github.com/user-attachments/assets/18a1aeb0-1ec9-47eb-b1b2-392af56b0220" />


4. Run the application via IntelliJ.

<img width="2874" height="1538" alt="image" src="https://github.com/user-attachments/assets/4880ed9f-268e-47bb-a02f-02c385b4cebd" />

<img width="2874" height="1538" alt="image" src="https://github.com/user-attachments/assets/b861d2f5-35cb-4c61-9f50-113e0da57971" />


5. Verify APIs using Postman (e.g., create post).

<img width="1268" height="768" alt="image" src="https://github.com/user-attachments/assets/99ef1207-30d5-4b04-b5b9-86684e407dc4" />

---

# Q3. Why Do We Need ModelMapper in Spring, and When Do We Need It?

ModelMapper is used to map between different object models, most commonly between **Entity** and **DTO**. In Spring applications, the persistence model (JPA entity) should not be directly exposed to the API layer because entities may contain internal fields, lazy-loaded relationships, or persistence-related concerns.

We typically need ModelMapper when:
- We want a clean separation between API contracts (DTOs) and database models (Entities).
- The application has many endpoints and repetitive manual mapping code becomes noisy and error-prone.
- We want consistent mapping logic with centralized configuration.

---

# Q4. Give Three Examples Where ModelMapper Will NOT Map Successfully, and Why

### Example 1: Different Field Names  
If the source object has a field named `postTitle` but the target object has `title`, the default ModelMapper configuration cannot match them automatically. A custom property mapping is required.

### Example 2: Incompatible Data Structure or Nested Objects  
If the source object contains a nested structure like `author.name`, but the target object expects a flat field such as `authorName`, default mapping may fail or produce `null` unless explicitly configured.

### Example 3: Collection Element Type Mismatch  
If the source object has `List<CommentEntity>` and the target object has `List<CommentDto>`, automatic mapping of generic collection elements may not work correctly without using type tokens or explicit configuration.

---

# Q5. How Does ModelMapper Cast Different Data Types Between Source and Target?

ModelMapper performs type conversion using its internal **Converter** mechanism. For simple and compatible conversions, ModelMapper may convert values automatically (for example, `Integer` to `Long`, or `String` to `Enum` if configured).

Typical scenarios include:
- Numeric widening (e.g., `Integer → Long`) can often work automatically.
- `String` to `Date` or `Date` to `String` usually requires a custom converter.
- Enum conversions may require explicit mapping when enum names differ.

---
# Q6 Custom exception handling (data validation): check if the title is empty or too long, error code: 422 Unprocessable Entity

### Test Case 1: the post title is empty

<img width="1268" height="784" alt="image" src="https://github.com/user-attachments/assets/c15b6d18-9fc3-4841-8667-b92d3ca62a22" />

### Test Case 2: the post title is too long (cannot exceed 100 characters)

<img width="1268" height="784" alt="image" src="https://github.com/user-attachments/assets/92181fda-b82b-434b-83db-4f08b470fd0e" />

---

# Q7. How Does Controller Advice Work? Are There Other Approaches for Global API Exception Handling?

`@ControllerAdvice` acts as a global interceptor for controller exceptions. When an exception is thrown in the controller or service layer and is not caught, Spring searches for a matching `@ExceptionHandler` method inside controller advice classes. The handler builds a `ResponseEntity` with a customized response body and HTTP status code.

Other approaches include:
- `@RestControllerAdvice`, which is similar but designed specifically for REST APIs.
- Implementing `HandlerExceptionResolver` for lower-level control.
- Using `ResponseStatusException` or `@ResponseStatus` on exception classes for simpler use cases (less flexible than centralized handling).

---

# Q8. Difference Between Throwing a Regular Exception and a Customized API Exception (With Screenshots)

A **regular exception** (such as `NullPointerException`) is usually not meaningful to API consumers. Without a specific handler, it is typically caught by the global exception mechanism and returned as a generic `500 Internal Server Error`, often with limited and uncontrolled messaging.

<img width="1268" height="896" alt="image" src="https://github.com/user-attachments/assets/77a818fe-b94a-404f-a842-ece0068dfa47" />


A **customized API exception** (such as `BlogAPIException` or a user-defined `InvalidContentException`) represents a known business scenario. It allows:
- A clear and meaningful error message for clients.
- A specific HTTP status code (e.g., 400, 404, 409, 422).
- A consistent error response body (timestamp, message, and details).

<img width="1268" height="784" alt="image" src="https://github.com/user-attachments/assets/d81e55bc-9acb-4f77-bdb7-411ea089c975" />

---

# Q9. Write some regular expression to restrict the value of attributes that your PostorComment can have. You may use https://regex101.com/ to construct and test/validate your regular expression.

### Test Case 1: The post title contains special characters

<img width="1268" height="896" alt="image" src="https://github.com/user-attachments/assets/96cdd61c-0955-4cdb-ad98-f23c0f089092" />


### Test Case 2: the comment invalid email format

<img width="1268" height="896" alt="image" src="https://github.com/user-attachments/assets/6fc9cd72-0ff9-4d2c-b269-47359c267110" />


---

# Q10. Explain Spring Framework Fundamental Principles and How They Help Build Business Applications

**Inversion of Control (IoC)**: Spring manages object lifecycles and dependencies, reducing manual wiring and enabling modular design.

**Dependency Injection (DI)**: Dependencies are injected rather than created inside classes, improving testability, flexibility, and maintainability.

**Aspect-Oriented Programming (AOP)**: Cross-cutting concerns such as logging, transactions, and security can be separated from business logic, keeping core code clean.

**Convention over Configuration and Auto-Configuration (Spring Boot)**: Reduces boilerplate configuration, allowing developers to focus on business logic more efficiently.

These principles make business applications easier to scale, test, extend, and maintain consistently across teams.

---

# Q11. Types of Dependency Injection, Use Cases, and Why Field Injection Is Not Recommended

### Constructor Injection  
The recommended approach for required dependencies. It ensures objects are created in a valid state, supports immutability through `final` fields, and is the easiest to unit test.

### Setter Injection  
Useful for optional dependencies or in rare cases where circular dependencies must be resolved (although redesign is usually preferred).

### Field Injection (`@Autowired` on fields)  
Not recommended because:
- It makes unit testing harder and often requires reflection or a Spring context.
- Dependencies are hidden and not visible in the constructor signature.
- Fields cannot be declared `final`, weakening immutability.
- It encourages uncontrolled dependency growth.

---

# Q12. Explain different types of application context in Spring framework, with screenshots. You may take https:// github.com/CTYue/springIOC for reference.


This project demonstrates different types of Spring `ApplicationContext` using XML-based and Java-based configurations.

---

## 1. ClassPathXmlApplicationContext (XML-based)

`ClassPathXmlApplicationContext` loads bean definitions from an XML file located in the classpath (e.g. `resources` folder).  
Beans can be defined using `<bean>` or discovered using `<context:component-scan>`.

**Code Usage**  
- `SpringIocContainerApplication.java`
- Line: `new ClassPathXmlApplicationContext("bean.xml")`

<img width="2872" height="1746" alt="image" src="https://github.com/user-attachments/assets/796a478f-4356-4d16-970a-e8552f375c0d" />


**XML Configuration (Screenshot Required)**  
- `bean.xml`
- `<context:component-scan>` and `<bean>` definitions

<img width="2872" height="1746" alt="image" src="https://github.com/user-attachments/assets/ae512b1e-c382-4970-8875-e3fdc59bac65" />

## 2. AnnotationConfigApplicationContext (Java-based)

`AnnotationConfigApplicationContext` loads beans from Java configuration classes annotated with `@Configuration`.  
Beans are defined using `@Bean` methods or component scanning.

**Code Usage**  
- `SpringIocContainerApplication.java`
- Line: `new AnnotationConfigApplicationContext(BeanConfig.class)`

<img width="2872" height="1746" alt="image" src="https://github.com/user-attachments/assets/93c3e47c-59ef-4191-9df9-f513171d9b94" />

**Java Configuration Class**  
- `BeanConfig.java`
- `@Configuration`, `@ComponentScan`, and `@Bean`

<img width="2872" height="1746" alt="image" src="https://github.com/user-attachments/assets/906af183-2863-43f3-b0db-73fe1756d134" />

## 3. Multiple ApplicationContext Instances

The project creates multiple `ApplicationContext` instances.  
Each context is independent and manages its own beans.

**Code Usage**  
- `SpringIocContainerApplication.java`
- creation of `context`, `context2`, and `context3`

<img width="2872" height="1746" alt="image" src="https://github.com/user-attachments/assets/82f3c6f5-573d-4abb-a7c7-643dc9738329" />

This project demonstrates:
- XML-based configuration using `ClassPathXmlApplicationContext`
- Java-based configuration using `AnnotationConfigApplicationContext`
- Usage of multiple independent ApplicationContext containers

---

# Q13. Compare `@Component` and `@Bean`, and When to Use Each

### @Component  
Used on a class to allow Spring to discover it via component scanning. Best suited for application classes you own, such as services, controllers, repositories, and utilities.

### @Bean  
Used on a method inside a `@Configuration` class to register an object as a Spring bean. Best used when:
- The class comes from a third-party library and cannot be annotated.
- Customized initialization or configuration is required (e.g., configuring `ModelMapper`).

---

# Q14. Explain Spring Bean Scopes and How to Pick the Correct Scope

Common bean scopes include:
- **singleton (default)**: One instance per Spring container. Best for stateless services and repositories.
- **prototype**: A new instance is created each time the bean is requested. Useful for stateful objects but harder to manage.
- **request (web)**: One instance per HTTP request.
- **session (web)**: One instance per HTTP session.

The correct scope should be chosen based on whether the bean maintains state and whether that state should be shared.

---

# Q15. Difference Between Bean ID and Bean Class

The **bean class** is the Java type (for example, `ModelMapper.class`) used to create the bean instance.

The **bean ID (or bean name)** is the identifier used to reference the bean within the Spring container.
- For `@Component`, the bean ID defaults to the class name with lower camel case.
- For `@Bean`, the bean ID defaults to the method name unless explicitly specified.

---

# Q16. If Multiple Implementations Exist, How Does Spring Decide Which Bean to Inject?

If multiple beans match the same type, Spring resolves dependency injection using the following mechanisms:
- `@Primary`: The primary bean is selected by default.
- `@Qualifier("beanName")`: Explicitly specifies which bean to inject.
- Bean name matching: In some cases, the parameter name in constructor injection can help resolve ambiguity.

If ambiguity remains, Spring throws a `NoUniqueBeanDefinitionException`.
