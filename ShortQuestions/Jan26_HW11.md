#  Jan26 HW11

## 2. Explain tight coupling vs loose coupling and what does Spring IOC do?
Tight coupling means one class directly depends on the concrete implementation of another class. This makes code hard to change, test, and maintain because any change in one class may force changes in others.

Loose coupling means classes depend on abstractions (interfaces), not concrete implementations. This improves flexibility, testability, and maintainability.

Spring IoC (Inversion of Control) solves tight coupling by creating and managing objects for you and injecting dependencies at runtime instead of letting classes create them directly.

Example:
```
class OrderService {
    private PaymentService paymentService = new PaymentService();
}
```

Loose coupling with Spring IoC:
```
class OrderService {
    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

## 3. What is MVC pattern?
MVC (Model–View–Controller) is a design pattern that separates an application into three layers:
    - Model: Business logic and data (entities, services)
    - View: UI layer (JSP, Thymeleaf, HTML)
    - Controller: Handles requests, coordinates Model and View

Benefits:
    - Separation of concerns
    - Easier maintenance and testing
    - Clear responsibility for each layer

## 4. What is Front-Controller?
A Front Controller is a single entry point that handles all incoming HTTP requests and then dispatches them to the appropriate controller.

Why it’s useful:
    - Centralized request handling
    - Common logic (logging, security, validation) in one place
    - Cleaner and more consistent architecture

## 5. Explain DispatcherServlet and how it works
DispatcherServlet is the core of Spring MVC and acts as the Front Controller.

Request flow:
    - Client sends HTTP request
    - DispatcherServlet receives the request
    - Finds the correct controller via HandlerMapping
    - Calls the controller method
    - Controller returns a ModelAndView
    - DispatcherServlet resolves the view via ViewResolver
    - View is rendered and response is returned to client

## 6. What is JSP and What is Model And View？
JSP (JavaServer Pages) is a server-side technology used to generate dynamic HTML using Java.

Model:
    - Holds application data and business logic
    - Examples: Entity classes, DTOs, service results

View:
    - Displays data to the user
    - Examples: JSP, Thymeleaf templates

In Spring MVC:
    - Controller prepares the Model
    - View (JSP) reads data from the Model and renders HTML

## 7. Explain servlet and servlet container, name some servlet implementations and servlet containers other than tomcat
A Servlet is a Java class that handles HTTP requests and responses. It runs on the server and follows the Servlet API.

A Servlet Container:
    - Manages servlet lifecycle (init, service, destroy)
    - Handles threading, security, and request routing
    - Provides runtime environment for servlets

Common servlet containers (besides Apache Tomcat):
    - Jetty – Lightweight and embeddable
    - Undertow – High-performance, non-blocking
    - JBoss EAP – Full Java EE server
    - GlassFish – Reference implementation of Jakarta EE
