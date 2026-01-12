# Java Technical Interview Guide: Exceptions, Enums, and Collections

---

## Q1. Java Exception Categories
Java exceptions are organized into a hierarchy under the `Throwable` class.



| Category                 | Compile-time Handling | Common Examples                                    | Best Practices                                                                                       |
|:-------------------------| :--- |:---------------------------------------------------|:-----------------------------------------------------------------------------------------------------|
| **Checked Exceptions**   | **Required** (Catch or Declare) | `IOException`, `SQLException`                      | try-catch blocks.                                                                                    |
| **Unchecked Exceptions** | **Optional** | `NullPointerException`, `IndexOutOfBoundException` | Represent programming logic errors; these should be prevented by better coding logic.                |
| **Errors**               | **No** | `OutOfMemoryError`, `StackOverflowError`           | Do not attempt to catch. These indicate fatal JVM failures that the application cannot recover from. |

---

## Q2. try-catch-finally Execution Order
1. **Execution Flow:** The `try` block runs first. If an exception occurs, execution immediately jumps to the matching `catch` block. Regardless of whether an exception was thrown or caught, the `finally` block **always** executes.
2. **Return Value Conflict:** If both a `catch` block and a `finally` block contain return statements, the **`finally` block's return value will be the one actually returned** to the caller.
3. **Why discourage return in finally?**
    * It "swallows" exceptions. If an exception is thrown in `try` but `finally` returns a value, the exception is discarded as if it never happened.
    * It makes the code flow unpredictable and significantly harder to debug.

---

## Q3. Catch Scope: Small to Large
The "Small to Large" rule dictates that catch blocks must be ordered from the **most specific** exception (subclass) to the **most general** (superclass).

* **The Reason:** Java checks catch blocks sequentially. If a general exception like `Exception` is placed first, it will intercept all exceptions, making the more specific blocks unreachable.
* **Violation:** The Java compiler will identify this as "unreachable code" and will fail to compile the program.

---

## Q4. throw vs throws

| Feature | `throw` | `throws` |
| :--- | :--- | :--- |
| **Location** | Used inside the method body. | Used in the method signature. |
| **Followed by** | An instance of an exception object. | The exception class name(s). |
| **Function** | Used to explicitly trigger an exception. | Used to delegate the handling of an exception to the caller. |

**Practical Architecture (DAO-Service-Controller):**
In a standard architecture, the **DAO** might `throw` an exception if data is missing; the **Service** layer uses `throws` to pass that error up the chain; and the **Controller** finally catches it to return a meaningful error message to the user.

```java
import java.sql.SQLException;

// DAO: Throws the exception up
public User findUser(String id) throws SQLException {
    if (id == null) throw new IllegalArgumentException("ID cannot be null");
    // Database logic here...
}

// Service: Passes the responsibility to the Controller
public User getUserService(String id) throws SQLException {
    try {
        return dao.findUser(id);
    } catch (SQLException e){
        throw new Exception("User Not found: " + e.getMessage());
    }
}
```

---

## Q5. Try-with-Resources
Introduced in Java 7, this syntax ensures that each resource is closed at the end of the statement execution.

* **Interface Requirement:** The resource class must implement the `AutoCloseable` or `Closeable` interface.
* **Execution Order:** Resources are initialized from top to bottom (or left to right), but they are **closed in reverse order** of their declaration to handle dependencies correctly.

---

## Q6. Custom Exceptions: Exception vs RuntimeException
* **Extend `Exception` (Checked):** Choose this when the error is a "business exception" that the calling code **must** acknowledge and handle (e.g., a user having insufficient funds).
* **Extend `RuntimeException` (Unchecked):** Choose this for technical or state errors where the caller cannot do anything to fix the situation at runtime (e.g., an internal system configuration is missing).

---

## Q7. Enum Characteristics
1. **Every element is in values:** The compiler automatically provides a static `values()` method that returns an array containing all the constants of the enum in the order they are declared.
2. **Every element is a constructor:** Each enum constant is a static final instance of the enum class. When an enum constant is defined, it implicitly calls the enum's constructor.
3. **Private Constructors:** To implement an enum with parameters, you define a private constructor and fields. Since the constructor is private, the constants are the only possible instances of the enum.

---

## Q8. Enum Template Pattern (Interface + Enum + Exception)
This pattern decouples the definition of error codes from the exception logic.

**The Four Components:**
1. **`IErrorCode` (Interface):** Defines the standard contract (e.g., `getCode()` and `getMessage()`).
2. **`ErrorCodeEnum` (Enum):** Implements the interface to define specific error constants for a module.
3. **`BusinessException` (Class):** A custom exception that holds an instance of the interface.
4. **Global Handler:** A centralized component that catches the exception and extracts the code and message for the response.

**Why use an Interface?** It allows the `BusinessException` to remain generic. You can pass any enum from any module into the same exception class as long as that enum implements the `IErrorCode` interface.

---

## Q9. List, Set, and Queue Comparison

| Feature | List | Set | Queue |
| :--- | :--- | :--- | :--- |
| **Ordering** | Maintains insertion order. | Generally unordered (except `TreeSet`). | FIFO (First-In-First-Out). |
| **Duplicates** | Permitted. | **Prohibited.** | Permitted. |
| **Main Impl.** | `ArrayList` | `HashSet` | `PriorityQueue` or `LinkedList` |
| **Use Case** | Storing an ordered list of items. | Storing a collection of unique IDs. | Managing a list of pending tasks. |

---

## Q10. HashMap vs Hashtable
* **Differences:** `Hashtable` is synchronized and does not allow `null` keys or values. `HashMap` is not synchronized and allows one `null` key and multiple `null` values.
* **Why Obsolete?** `Hashtable` synchronizes the entire object for every operation, which creates a massive performance bottleneck in multi-threaded applications.
* **Modern Alternatives:** * `ConcurrentHashMap`: Uses sophisticated locking mechanisms (like CAS and volatile variables) for high-performance thread safety.