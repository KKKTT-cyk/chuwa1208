**Q1. What are the three major categories of exceptions in Java's exception hierarchy? For each category, explain: (1) Whether it must be handled at compile-time, (2) Common examples, (3) Best practices for handling.**

In Java, all exception types are subclasses of the Throwable class. To understand how they function, it is best to look at them through the lens of the Java Exception Hierarchy.

1. Checked Exceptions
Checked exceptions are conditions that a well-written application should anticipate and recover from. They represent "external" failures that are out of the programmer's immediate control.

Compile-time requirement: Yes. The Java compiler strictly enforces the "Catch or Specify" requirement. You must either wrap the code in a try-catch block or declare the exception in the method signature using the throws keyword.

Common examples: IOException, SQLException, FileNotFoundException, and ClassNotFoundException.

Best practices: * Only use checked exceptions for conditions that the caller can reasonably be expected to recover from. Avoid over-using them; if a caller can't do anything to fix the situation, a checked exception creates unnecessary "boilerplate" code.


**Q2. Explain the execution order of try-catch-finally blocks. If both the catch block and finally block contain return statements, which value will be returned? Why is it strongly discouraged to use return statements in finally blocks?**

*try* executes. If exeption occurs, it's caught by a machting *catch*. *finally* will always execute (almost). 

return statement in finally will override everything. So if both catch and finally block return, the return value from *finally* will be returned.

return statement from finally block is discouraged because it suppresses exceptions, overrides return values in try block, and makes debugging difficult. finally block should only be usef for clean-up.


**Q3. What is the "catch scope should be from small to large" rule? Why must specific exception types (like `OrderNotFoundException`) be caught before general ones (like `Exception`)? What happens if you violate this rule?**

The rule means that we should catch more specific exceptions first, then more general ones.

Specific exceptions should be caught before general ones because Java matches catch blocks top-down. A general catch would swallow specific exceptions. If violated, there will be a Compile-time error: “Exception already caught”.


**Q4. Compare `throw` and `throws`: (1) Where is each used in code? (2) What follows each keyword? (3) Provide one practical example demonstrating both keywords working together in a DAO-Service-Controller architecture.**

`throw` is used inside method. An instance of an exception class would follow. 
`throws` is used in method signature. One or more exception class names would follow, separated by commas.

```
// DAO
public Order find(String id) throws SQLException {
    throw new SQLException("DB error"); // Throws an exception but doens't handle. The caller has to handle.
}

// Service
public Order getOrder(String id) {
    try {
        return dao.find(id);
    } catch (SQLException e) {
        throw new OrderException(OrderErrorCode.ORDER_NOT_FOUND);
    }
}

// Controller
public void handleRequest() {
    service.getOrder("123");
}
```

**Q5. What is try-with-resources syntax (introduced in Java 7)? What interface must a class implement to be used with try-with-resources? Explain the execution order when multiple resources are declared.**

1. The try-with-resources statement is a specialized try block that ensures each resource—such as a file stream or database connection—is closed automatically at the end of the statement. Before Java 7, developers had to manually close resources in a finally block, which was prone to errors and "resource leaks" if an exception occurred during the closing process itself.

To be used within a try-with-resources statement, a class must implement the java.lang.AutoCloseable interface (or its child, java.io.Closeable). This interface has a single method:  

```public void close() throws Exception;```

When the try block finishes (either normally or via an exception), the JVM automatically calls this close() method for you.


2. Execution Order with Multiple Resources
You can declare multiple resources within a single try statement by separating them with semicolons. The order of execution follows two strict rules:

    - Initialization: Resources are initialized in the order they are declared (top-to-bottom/left-to-right).

    - Closing: Resources are closed in the reverse order of their declaration (bottom-to-top/right-to-left).

This "Last-In, First-Out" (LIFO) order ensures that if one resource depends on another (e.g., a BufferedWriter wrapping a FileWriter), the outer wrapper is closed before the inner resource it depends on.


**Q6. When creating custom exceptions, how do you decide between extending `Exception` vs extending `RuntimeException`? Provide criteria for each choice and one example scenario for each.**

Extend Exception when caller must handle it, or external failure (IO, network). Example: InsufficientBalanceException.

Extend RuntimeException when programming or business rule violation, or wecCannot recover meaningfully. Example: OrderNotFoundException.


**Q7. Explain the two important features of Enum: "Every element is in values" and "Every element is a constructor". How would you implement an Enum with a private constructor that accepts parameters?**

Every Element is in values()
When an enum is defined, the language automatically generates a static method called values(). This method returns an array containing all the constants of that enum type in the order they are declared. This ensures type safety and easy iteration. You don't have to manually track which constants belong to the group; the enum handles the "collection" aspect itself. It is commonly used in for-each loops to iterate through all possible states (e.g., iterating through all DaysOfWeek to initialize a calendar).

Every Element is a "Constructor" (Instance)
This is a conceptual way of saying that each enum constant is an instance of the enum class. When you define RED, GREEN, BLUE, you are essentially calling a constructor for each of those three objects the moment the class is loaded. Because each element is a full-fledged object/instance, enums can have their own fields, methods, and specific behaviors. They aren't just labels; they are "singleton-like" objects. If your enum has a constructor, every constant declared at the top must satisfy that constructor’s signature.

To implement an enum with a private constructor that accepts parameters, you define the fields you want to store and then pass the values during the declaration of the constants.

```
public enum Planet {
    // Every element "calls" the constructor below
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.3781e6);

    // Private fields to store the parameters
    private final double mass;   // in kilograms
    private final double radius; // in meters

    // Private Constructor: It cannot be invoked from outside the Enum
    private Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }

    // Getter methods to access the data
    public double getMass() { return mass; }
    public double getRadius() { return radius; }
}
```

**Q8. Describe the popular Enum template pattern (Interface + Enum + Exception). What are its four components? How does using an interface type (`IErrorCode`) allow the exception class to accept multiple different enum types?**

Four Components:

    - IErrorCode interface

    - ErrorCode enum(s)

    - Custom Exception

    - Business logic using interface

Using an interface like IErrorCode as a type in your exception class is the "secret sauce" of this pattern. It leverages Polymorphism to achieve Extensibility.


**Q9. Compare the three major Collection interfaces: List, Set, and Queue. For each, explain: (1) Ordering characteristics, (2) Duplicate element handling, (3) Most commonly used implementation class, (4) One typical use case.**

| Interface | Order         | Duplicates | Common Impl  | Use Case           |
| --------- | ------------- | ---------- | ------------ | ------------------ |
| List      | Ordered       | Allowed    | `ArrayList`  | Index-based access |
| Set       | No duplicates | ❌          | `HashSet`    | Uniqueness         |
| Queue     | FIFO          | Allowed    | `LinkedList` | Task scheduling    |


**Q10. Explain the difference between HashMap and Hashtable. Why is Hashtable considered obsolete? What are the modern alternatives for thread-safe Map implementations?**

| Feature     | HashMap | Hashtable |
| ----------- | ------- | --------- |
| Thread-safe | ❌       | ✅         |
| Performance | Fast    | Slow      |
| Null keys   | Allowed | ❌         |
| Status      | Modern  | Obsolete  |

Hashtable is considered obsolete because its approach to thread safety is "heavy-handed" and inefficient for modern software needs:

Coarse-Grained Locking: It synchronizes every single method at the object level. If one thread is reading, all other threads (even those wanting to read a different part of the map) are blocked.

Legacy Design: It extends Dictionary, a class that was superseded by the Map interface.

Redundancy: Better alternatives now exist for every use case. If you don't need thread safety, HashMap is faster. If you do need thread safety, ConcurrentHashMap is far more scalable.

Modern Thread-safe Alternatives

- ConcurrentHashMap

- Collections.synchronizedMap()

