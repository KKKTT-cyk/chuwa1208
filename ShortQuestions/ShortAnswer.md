## 1. Annotations learned in this class session

During this session, the following **Spring and Web-related annotations** were covered or referenced:

### Core Spring / IoC
- `@Component` – Marks a class as a Spring-managed bean
- `@Service` – Specialization of `@Component` for service-layer classes
- `@Repository` – Specialization of `@Component` for data-access classes
- `@Configuration` – Indicates a configuration class that defines beans
- `@Bean` – Declares a bean via a method in a configuration class
- `@Autowired` – Enables dependency injection
- `@Qualifier` – Specifies which bean to inject when multiple candidates exist
- `@Primary` – Marks a default bean among multiple implementations
- `@Scope` – Defines the lifecycle scope of a bean

### Web / MVC
- `@Controller` – Marks a class as a Spring MVC controller
- `@RestController` – Combines `@Controller` and `@ResponseBody`
- `@RequestMapping` – Maps HTTP requests to handler methods
- `@GetMapping`, `@PostMapping` – HTTP method–specific mappings
- `@PathVariable` – Extracts values from URL path
- `@RequestParam` – Extracts query parameters
- `@RequestBody` – Binds HTTP request body to an object
- `@ResponseBody` – Writes return value directly to HTTP response

### Exception Handling
- `@ControllerAdvice` – Global controller-related logic (e.g. exception handling)
- `@ExceptionHandler` – Handles specific exceptions

---

## 2. Tight coupling vs Loose coupling and what Spring IoC does

### Tight coupling
- Classes create and manage their own dependencies.
- A class is directly dependent on concrete implementations.
- Changes in one class often affect many others.
- Difficult to test and maintain.

**Example (conceptual):**
- A service directly creates a specific database implementation.

### Loose coupling
- Classes depend on abstractions (interfaces), not concrete implementations.
- Dependencies are provided externally.
- Easier to replace, extend, and test components.

### What Spring IoC does
Spring’s **Inversion of Control (IoC)**:
- Moves object creation and dependency management from application code to the Spring container.
- Spring creates objects (beans), wires dependencies, and manages lifecycles.
- Enables loose coupling by injecting dependencies instead of hard-coding them.

**Result:**
- Better modularity
- Easier testing
- Cleaner architecture
- Improved maintainability

---

## 3. What is the MVC pattern?

**MVC (Model–View–Controller)** is a design pattern that separates concerns in an application.

### Components
- **Model**
    - Represents business data and logic
    - Holds application state
- **View**
    - Responsible for presentation (UI)
    - Displays data from the model
- **Controller**
    - Handles user requests
    - Coordinates between Model and View

### Why MVC is useful
- Clear separation of responsibilities
- Easier maintenance and scalability
- UI and business logic evolve independently

Spring MVC is an implementation of the MVC pattern for web applications.

---

## 4. What is the Front Controller?

A **Front Controller** is a single entry point that handles **all incoming requests** to a web application.

### Responsibilities
- Receives every HTTP request
- Performs common preprocessing (logging, authentication, validation)
- Dispatches the request to the appropriate handler

### Benefits
- Centralized request handling
- Consistent behavior across the application
- Reduces duplicated code

In Spring MVC, the **DispatcherServlet** acts as the Front Controller.

---

## 5. DispatcherServlet and how it works

**DispatcherServlet** is the core component of Spring MVC and the **Front Controller** implementation.

### How it works (high level)
1. Client sends an HTTP request.
2. DispatcherServlet receives the request.
3. It consults handler mappings to find the correct controller.
4. The controller processes the request and returns a result (Model + View).
5. DispatcherServlet resolves the view.
6. The view renders the response back to the client.

### Key role
- Orchestrates the entire request–response lifecycle
- Decouples request handling from view rendering
- Enables extensibility via interceptors and resolvers

---

## 6. What is JSP? What are Model and View?

### JSP (JavaServer Pages)
- A server-side technology for generating dynamic HTML.
- Allows embedding Java logic into HTML (historically).
- Commonly used as a **View** technology in MVC.

### Model
- Contains application data and business logic.
- Independent of how data is displayed.

### View
- Responsible for rendering the user interface.
- Receives data from the Model and presents it.
- JSP, Thymeleaf, and FreeMarker are common view technologies.

---

## 7. Servlet and Servlet Container

### What is a Servlet?
- A Java class that handles HTTP requests and responses.
- Runs on a web server and processes client requests.
- Forms the foundation of Java web applications.

### What is a Servlet Container?
- A runtime environment that manages servlets.
- Responsibilities include:
    - Servlet lifecycle management
    - Request routing
    - Thread management
    - Security and session handling

### Servlet implementations
- `HttpServlet`
- `GenericServlet`

### Servlet containers (other than Tomcat)
- **Jetty**
- **Undertow**
- **GlassFish**
- **WildFly**
- **WebLogic**
- **WebSphere**

Each container implements the Java Servlet specification and provides similar core functionality.

