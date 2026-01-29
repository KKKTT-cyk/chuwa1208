## 2. Tight Coupling vs Loose Coupling and Spring IOC

### Tight Coupling
Tight coupling occurs when classes are highly dependent on each other's concrete implementations. Changes in one class often require changes in dependent classes.

**Example of Tight Coupling:**
```java
public class UserService {
    private MySQLUserRepository repository = new MySQLUserRepository();
    
    public User getUser(int id) {
        return repository.findById(id);
    }
}
```

**Problems:**
- Hard to test (can't easily mock dependencies)
- Difficult to change implementations
- Reduces code reusability
- Creates rigid architecture

### Loose Coupling
Loose coupling minimizes dependencies between classes by programming to interfaces rather than concrete implementations.

**Example of Loose Coupling:**
```java
public class UserService {
    private UserRepository repository;
    
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    
    public User getUser(int id) {
        return repository.findById(id);
    }
}
```

### Spring IOC (Inversion of Control)

**What Spring IOC Does:**

Spring IOC Container manages object creation and dependency injection, promoting loose coupling through:

1. **Dependency Injection (DI)**: The container injects dependencies rather than objects creating their own dependencies
2. **Bean Management**: Creates, configures, and manages the lifecycle of application objects (beans)
3. **Decoupling**: Removes the responsibility of object creation from classes

**Types of Dependency Injection:**
- Constructor Injection (recommended)
- Setter Injection
- Field Injection

**Example:**
```java
@Service
public class UserService {
    private final UserRepository repository;
    
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
}

@Repository
public class MySQLUserRepository implements UserRepository {
    // Implementation
}
```

**Benefits:**
- Easier testing with mock objects
- Flexible configuration
- Better code maintainability
- Promotes interface-based design

---

## 3. MVC Pattern

### What is MVC?

MVC (Model-View-Controller) is a design pattern that separates application concerns into three interconnected components.

### Components:

#### Model
- Represents the application's data and business logic
- Manages data, logic, and rules
- Independent of the user interface
- Notifies views of state changes

**Example:**
```java
public class User {
    private int id;
    private String name;
    private String email;
    // getters, setters, business logic
}
```

#### View
- Presents data to the user
- Displays the model in appropriate format
- Receives user input
- Multiple views can exist for the same model

**Example:**
```jsp
<html>
<body>
    <h1>Welcome, ${user.name}</h1>
    <p>Email: ${user.email}</p>
</body>
</html>
```

#### Controller
- Handles user input and interactions
- Updates the model based on input
- Selects appropriate view for response
- Acts as intermediary between Model and View

**Example:**
```java
@Controller
public class UserController {
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "userView";
    }
}
```

### Benefits:
- Separation of concerns
- Parallel development
- Easier testing
- Code reusability
- Multiple views for same model

---

## 4. Front Controller Pattern

### What is Front Controller?

Front Controller is a design pattern that provides a centralized entry point for handling all requests in a web application.

### Key Characteristics:

1. **Single Entry Point**: All requests pass through one controller
2. **Centralized Control**: Common processing logic in one place
3. **Request Routing**: Delegates requests to appropriate handlers

### Components:

- **Front Controller**: Central handler for all requests
- **Dispatcher**: Routes requests to specific handlers
- **View**: Renders the response

### Workflow:
```
Client Request → Front Controller → Handler Mapping → Specific Controller → View
```

### Benefits:
- Centralized authentication and authorization
- Consistent request handling
- Common pre/post processing
- Easier to manage cross-cutting concerns (logging, security)
- Reduced code duplication

### Example Structure:
```java
public class FrontController {
    private Dispatcher dispatcher;
    
    public void handleRequest(String request) {
        // Common pre-processing (security, logging, etc.)
        dispatcher.dispatch(request);
        // Common post-processing
    }
}
```

**In Spring MVC, the DispatcherServlet implements the Front Controller pattern.**

---

## 5. DispatcherServlet and How It Works

### What is DispatcherServlet?

DispatcherServlet is the front controller in Spring MVC framework. It's the central servlet that receives all HTTP requests and coordinates the request handling process.

### How DispatcherServlet Works:

#### Request Processing Flow:

1. **Request Reception**
   - Client sends HTTP request
   - DispatcherServlet receives the request

2. **Handler Mapping**
   - DispatcherServlet consults HandlerMapping
   - Determines which controller should handle the request
   - Based on URL patterns and annotations

3. **Handler Execution**
   - DispatcherServlet forwards request to appropriate Controller
   - Controller processes the request and returns ModelAndView

4. **View Resolution**
   - DispatcherServlet consults ViewResolver
   - ViewResolver maps logical view name to actual view

5. **View Rendering**
   - View renders the model data
   - Response sent back to client

### Detailed Workflow Diagram:
```
Client
  ↓ (1) HTTP Request
DispatcherServlet
  ↓ (2) Find handler
HandlerMapping
  ↓ (3) Return handler
DispatcherServlet
  ↓ (4) Execute handler
Controller
  ↓ (5) Return ModelAndView
DispatcherServlet
  ↓ (6) Resolve view
ViewResolver
  ↓ (7) Return View
DispatcherServlet
  ↓ (8) Render view
View (JSP/Thymeleaf)
  ↓ (9) HTTP Response
Client
```

### Configuration Example:

**web.xml (Traditional):**
```xml
<servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/spring-servlet.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

**Java Configuration (Modern):**
```java
public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext context = 
            new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        
        ServletRegistration.Dynamic dispatcher = 
            container.addServlet("dispatcher", 
                new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
```

### Key Components:

- **HandlerMapping**: Maps requests to handlers
- **HandlerAdapter**: Executes the handler
- **ViewResolver**: Resolves logical view names
- **HandlerExceptionResolver**: Handles exceptions
- **MultipartResolver**: Handles file uploads
- **LocaleResolver**: Internationalization support

---

## 6. JSP and ModelAndView

### What is JSP?

**JSP (JavaServer Pages)** is a server-side technology for creating dynamic web content using Java.

#### Key Features:

1. **HTML + Java Code**: Embed Java code in HTML
2. **Server-Side Processing**: Compiled to servlets
3. **Tag Libraries**: JSTL, custom tags
4. **Expression Language (EL)**: Simplified data access

#### JSP Example:
```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
    <h1>Users</h1>
    <table>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
```

#### JSP Lifecycle:
1. Translation: JSP → Servlet
2. Compilation: Servlet → Bytecode
3. Initialization: _jspInit()
4. Request Processing: _jspService()
5. Destruction: _jspDestroy()

### What is ModelAndView?

**ModelAndView** is a Spring MVC class that holds both the model data and view information.

#### Components:

1. **Model**: Data to be displayed (Map of objects)
2. **View**: View name or View object

#### Usage Example:

**Method 1: Using ModelAndView Object**
```java
@Controller
public class UserController {
    
    @GetMapping("/users")
    public ModelAndView listUsers() {
        ModelAndView mav = new ModelAndView();
        
        // Set view name
        mav.setViewName("userList");
        
        // Add model data
        List<User> users = userService.getAllUsers();
        mav.addObject("users", users);
        mav.addObject("title", "User Management");
        
        return mav;
    }
}
```

**Method 2: Constructor**
```java
@GetMapping("/user/{id}")
public ModelAndView getUser(@PathVariable int id) {
    User user = userService.getUser(id);
    return new ModelAndView("userDetail", "user", user);
}
```

**Method 3: Using Model Parameter (Alternative)**
```java
@GetMapping("/users")
public String listUsers(Model model) {
    List<User> users = userService.getAllUsers();
    model.addAttribute("users", users);
    return "userList"; // Returns view name only
}
```

#### Key Methods:

- `setViewName(String viewName)`: Set the view
- `addObject(String name, Object value)`: Add model attribute
- `addAllObjects(Map<String, ?>)`: Add multiple attributes
- `getModel()`: Get the model map
- `getViewName()`: Get the view name

#### Benefits:
- Clean separation of model and view
- Type-safe model data passing
- Flexible view resolution
- Easy to test

---

## 7. Servlet and Servlet Container

### What is a Servlet?

A **Servlet** is a Java class that handles HTTP requests and generates dynamic web content. It runs on the server side and follows the Java Servlet API specification.

#### Key Characteristics:

1. **Server-Side Component**: Runs on web server
2. **Platform Independent**: Pure Java
3. **Protocol Independent**: Can handle any protocol (HTTP, FTP, etc.)
4. **Lifecycle Managed**: By servlet container

#### Servlet Lifecycle:

1. **Loading**: Servlet class loaded by container
2. **Initialization**: `init()` method called once
3. **Request Handling**: `service()` method called for each request
4. **Destruction**: `destroy()` method called before removal

#### Servlet Example:
```java
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        // Initialization code
        System.out.println("Servlet initialized");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello World!</h1>");
    }
    
    @Override
    public void destroy() {
        // Cleanup code
        System.out.println("Servlet destroyed");
    }
}
```

### What is a Servlet Container?

A **Servlet Container** (also called Web Container) is the runtime environment that manages servlets' lifecycle and provides services.

#### Responsibilities:

1. **Lifecycle Management**: Create, initialize, and destroy servlets
2. **Request Handling**: Map URLs to servlets
3. **Multi-threading**: Handle concurrent requests
4. **Security**: Implement authentication and authorization
5. **Session Management**: Track user sessions
6. **JSP Support**: Compile and execute JSPs

#### How It Works:
```
Client → HTTP Request → Servlet Container → Servlet → Response → Client
```

### Servlet Implementations

#### 1. **HttpServlet** (Most Common)
```java
public class MyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        // Handle GET requests
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // Handle POST requests
    }
}
```

#### 2. **GenericServlet**
```java
public class MyGenericServlet extends GenericServlet {
    public void service(ServletRequest req, ServletResponse res) {
        // Protocol-independent servlet
    }
}
```

#### 3. **Annotation-based Servlets** (Servlet 3.0+)
```java
@WebServlet(
    name = "UserServlet",
    urlPatterns = {"/users", "/user/*"},
    loadOnStartup = 1
)
public class UserServlet extends HttpServlet {
    // Implementation
}
```

### Servlet Containers (Other than Tomcat)

#### 1. **Jetty**
- Lightweight and embeddable
- Used in development and production
- Easy to integrate with testing frameworks
- Popular for microservices

```xml
<!-- Maven dependency -->
<dependency>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-server</artifactId>
    <version>11.0.15</version>
</dependency>
```

#### 2. **Undertow**
- Lightweight and high-performance
- Non-blocking architecture
- Default in WildFly application server
- Low memory footprint
- Used in Spring Boot

#### 3. **GlassFish**
- Reference implementation of Java EE
- Full Java EE support
- Open-source
- Developed by Oracle/Eclipse Foundation

#### 4. **WebLogic**
- Commercial servlet container by Oracle
- Enterprise-grade features
- Full Java EE support
- Used in large enterprise applications

#### 5. **WebSphere**
- IBM's commercial application server
- Enterprise features
- High scalability
- Mission-critical applications

#### 6. **JBoss/WildFly**
- Open-source application server (JBoss → WildFly)
- Full Java EE support
- Modular architecture
- Used in enterprise applications

#### 7. **Resin**
- High-performance servlet container
- Good for high-traffic sites
- Commercial and open-source versions

#### Comparison Table:

| Container | Type | Performance | Use Case | License |
|-----------|------|-------------|----------|---------|
| Tomcat | Servlet Container | Good | General purpose | Apache 2.0 |
| Jetty | Servlet Container | Excellent | Embedded/Microservices | Apache 2.0 |
| Undertow | Servlet Container | Excellent | Microservices | Apache 2.0 |
| GlassFish | Application Server | Good | Java EE apps | CDDL/GPL |
| WebLogic | Application Server | Good | Enterprise | Commercial |
| WebSphere | Application Server | Good | Enterprise | Commercial |
| WildFly | Application Server | Good | Java EE apps | LGPL |

#### Choosing a Servlet Container:

- **Development/Testing**: Jetty, Tomcat
- **Microservices**: Undertow, Jetty
- **Enterprise Java EE**: WildFly, GlassFish, WebLogic
- **Production Web Apps**: Tomcat, Undertow
- **Embedded Applications**: Jetty, Undertow

---

## 8. Screenshot

<img width="1692" height="1306" alt="image" src="https://github.com/user-attachments/assets/e893a8d3-dffb-49e7-97e7-78a664486f61" />

