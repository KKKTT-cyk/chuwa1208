#  Jan 21st HW 10

## 3. Explain why do we need model mappers in Spring, and in what scanrios we need it.
In Spring applications, different layers use different object models, such as JPA entities for database operations and DTOs for API requests and responses. Returning entities directly can expose sensitive data, cause serialization issues, and tightly couple the API to the database schema. ModelMapper helps convert objects between these layers automatically, reducing repetitive setter code and keeping concerns separated. It is commonly used to map request DTOs to entities before persistence and entities to response DTOs before returning data.
```
UserEntity entity = modelMapper.map(userRequest, UserEntity.class);
UserResponse dto = modelMapper.map(entity, UserResponse.class);
```

## 4. Provide 3 examples in which model mapper will NOT map succesfully, explain why.
- ModelMapper fails when field names do not match between source and target objects because it relies on naming conventions.
```
class UserEntity { String firstName; }
class UserDTO { String givenName; }
```
- It also fails when mapping nested objects to flat fields without configuration, such as mapping post.user.username to postDTO.username, because it cannot infer flattening logic automatically. 
- Another failure case is incompatible data types without a converter, such as mapping BigDecimal to String, which requires explicit conversion rules.

## 5. Explain how model mapper cast different data types between source object and target class.
ModelMapper does not use Java casting; instead, it uses built-in and custom converters. If source and target types are compatible, values are assigned directly. If a built-in converter exists, ModelMapper performs the conversion automatically. When no suitable converter is available, developers must define a custom converter to control how the data is transformed.
```
modelMapper.addConverter(ctx ->
    ctx.getSource() == null ? null : ctx.getSource().toString());
```

## 7. Explain how Controller Advices work, is there any other approach to do same/similar global API exception handling?
- @ControllerAdvice / @RestControllerAdvice is Spring’s global interceptor for controller exceptions. When a controller (or something it calls like service/repository) throws an exception, Spring’s DispatcherServlet looks for an @ExceptionHandler method that matches that exception type. If it finds one (in the same controller or in a global advice), it uses that handler to build the HTTP response (status code + body). With @RestControllerAdvice, the return value is automatically serialized as JSON.

- Other approaches that achieve similar “global” handling:
    - Implement ErrorController (customize the /error endpoint) for centralized error pages / JSON.
    - Customize Spring Boot error properties (server.error.*) to control default error body fields (limited customization).
    - Use HandlerExceptionResolver / ResponseStatusExceptionResolver (lower-level, more control).
    - Throw ResponseStatusException directly (simple, but less structured than a custom error model).
    - Use @ResponseStatus on exception classes (quick mapping, less flexible than advice for custom body).

## 8. What's the difference between throwing a regular exception and a customized API exception that will be eventually thrown to Controller Advice codes?
Throwing a “regular” exception (like RuntimeException) usually produces a generic 500 response unless you explicitly handle it. A customized API exception is mainly useful because it carries business meaning (error code, status, details), and your Controller Advice can translate it into a consistent API error response (same JSON structure across endpoints).
/Users/chenbig/Documents/Chuwa/Homework/chuwa1208/ShortQuestions/default_vs_custom_error.png

## 10. Explain Spring framework fundamental principles. And how can they help build business applications?
Spring’s fundamentals are mainly IoC (Inversion of Control) and DI (Dependency Injection): instead of your code creating dependencies, Spring creates and wires them. This makes business applications easier to grow because code becomes loosely coupled, more testable, and easier to swap implementations (e.g., changing from in-memory repo to JPA repo). Spring also supports AOP (Aspect-Oriented Programming), which allows cross-cutting concerns like transactions, logging, and security to be applied consistently without repeating code everywhere (e.g., @Transactional around service methods).

## 11. Explain different types of dependency injection, explain their suitable use cases, and why fielde injection is not recommended in general.
There are three common DI types: constructor injection, setter injection, and field injection. Constructor injection is best for required dependencies because it guarantees the object is fully initialized and allows final fields, making the class easier to test. Setter injection is good for optional dependencies or when you truly need to change a dependency after creation (less common). Field injection is not recommended because dependencies are “hidden” (not visible in constructor), it’s harder to test without Spring (often requires reflection), and it prevents immutability.

Constructor injection:
```
@Service
public class PostService {
    private final PostRepository repo;

    public PostService(PostRepository repo) {
        this.repo = repo;
    }
}
```

Setter injection:
```
@Service
public class ReportService {
    private EmailSender emailSender; // optional

    @Autowired
    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }
}
```

Field injection:
```
@Service
public class BadExampleService {
    @Autowired
    private PostRepository repo;
}
```

## 12. Explain different types of application context in Spring framework
ApplicationContext is the Spring “container” that holds beans. Common types:
- AnnotationConfigApplicationContext: standalone Java app using @Configuration classes.
- ClassPathXmlApplicationContext: legacy XML-based configuration.
- WebApplicationContext: web environment (Servlet-based), integrated with Spring MVC.
- In Spring Boot, the context is created automatically (commonly a servlet web context for MVC apps).

## 13. Compare @Component and @Bean and in which scenario they should be used.
- @Component is used on a class so Spring can auto-detect it via component scanning (@Service, @Repository, @Controller are specializations). Use it when you own the class and want Spring to manage it directly.

- @Bean is used on a method inside a @Configuration class to register a bean manually. Use it when you need to create a bean from a third-party class, or you need custom construction logic.
```
@Component
public class JwtUtil { }
```
```
@Configuration
public class AppConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
```

## 14. Explain Spring bean scopes and how to pick the correct bean scope.
- singleton (default): one instance per Spring container. Best for stateless services, repositories, utilities.
- prototype: new instance each time requested. Use when bean is stateful per usage (rare in typical REST services).
- request (web): one per HTTP request. Use for request-scoped data.
- session (web): one per user session. Use for session state (less common in REST).
- application (web): one per ServletContext.

Rule of thumb: REST services are usually singleton + stateless. Only use request/session when you truly need scoped state.


## 15. Explain the difference between bean id and bean class.
- Bean class/type: the Java type Spring uses for autowiring (e.g., PaymentService interface).

- Bean id/name: the identifier in the container (e.g., "paypalPaymentService"). Spring can inject by type first; when there are multiple candidates, name/id can help disambiguate (often via @Qualifier).

## 16. Explain that when a bean has multiple alternative implementations, how will Spring decide which bean  implementation to inject/autowire?
When more than one bean matches a type, Spring resolves in this order (common behavior):
- Use the bean marked @Primary
- Use @Qualifier at the injection point
- Try matching by parameter/field name to bean name
- Otherwise throw NoUniqueBeanDefinitionException
```
public interface PaymentService { void pay(); }

@Service
@Primary
public class StripePaymentService implements PaymentService { public void pay() {} }

@Service("paypal")
public class PaypalPaymentService implements PaymentService { public void pay() {} }
```

```
@Service
public class CheckoutService {
    private final PaymentService paymentService;

    public CheckoutService(@Qualifier("paypal") PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```
