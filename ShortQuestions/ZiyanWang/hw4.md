Q1. What are the three major categories of exceptions in Java's exception hierarchy? For each category, explain: (1) Whether it must be handled at compile-time, (2) Common examples, (3) Best practices for handling.
- Error
- - 1. Not handled at compile-time
- - 2. OutOfMemoryError, StackOverFlowError
- - 3. Do not catch in normal business code. At most, log and fail fast at top-level (so the process can restart). Treat as JVM/infra-level failures.
- Checked
- - 1. Yes. Handled at compile-time, must catch or throws
- - 2. IOException (e.g. FileNotFound), SQLException/NetworkException (e.g. Id/data does not exist)
- - 3. Handle where you can recover meaningfully (retry, fallback, user-friendly message). Otherwise wrap and rethrow as an application exception (often unchecked) with context (IDs, inputs). Avoid catch (Exception) unless you rethrow or translate.
- Unchecked
- - 1. Not handled at compile-time (handled at run-time)
- - 2. NullPointerException (NPE), IndexOutOfBoundsException (e.g. List/Array)
- - 3. Use for programming errors and precondition violations. Validate inputs early (Objects.requireNonNull, argument checks). Let them propagate to a global handler (API layer) and return consistent error responses.

Q2. Explain the execution order of try-catch-finally blocks. If both the catch block and finally block contain return statements, which value will be returned? Why is it strongly discouraged to use return statements in finally blocks?
- Enter try, if no exception, run rest of try then go to finally. If exception occurs, jump to the first matching catch, execute it then go to finally.
- If both the catch block and finally block contain return statements, the value from finally is returned.
- Why: It silently swallows exceptions (the exception can be lost). It overrides intended return values, making logic surprising. It makes debugging and correctness much worse.

Q3. What is the "catch scope should be from small to large" rule? Why must specific exception types (like OrderNotFoundException) be caught before general ones (like Exception)? What happens if you violate this rule?
- Subclass -> Parent Class (exceptions at the same inheritance level can be in any order.)
- Because Java matches catch blocks from top to bottom; catching a general exception first will also catch its subclasses, making later specific catch blocks unreachable and causing a compile-time error.
- Compile-time error: “unreachable catch block” (Java won’t compile).

Q4. Compare throw and throws: (1) Where is each used in code? (2) What follows each keyword? (3) Provide one practical example demonstrating both keywords working together in a DAO-Service-Controller architecture.
- Where used?
- - throw: inside a method body to actually raise an exception.
- - throws: in method signature to declare possible exceptions.
- What follows?
- - throw → an exception object: throw new IllegalArgumentException("...");
- - throws → exception type(s): public void f() throws IOException
- Practical example:
- - DAO → Service → Controller

Q5. What is try-with-resources syntax (introduced in Java 7)? What interface must a class implement to be used with try-with-resources? Explain the execution order when multiple resources are declared.
- Syntax: Resources declared in the try(...) are closed automatically and in reverse order, even if an exception occurs.
- Required interface: A class must implement AutoCloseable to be used with try-with-resources.
- Execution order (multiple resources): Resources are closed automatically in reverse order of their declaration (LIFO) when the try block finishes.

Q6. When creating custom exceptions, how do you decide between extending Exception vs extending RuntimeException? Provide criteria for each choice and one example scenario for each.
- Extend Exception (checked) when:
- - Caller is expected to recover or take alternate action, and you want to force handling.
- - Often used in libraries where failure is part of normal flow (e.g., parsing, external system contract).
- - Example: PaymentGatewayUnavailableException extends Exception. Caller might retry, switch provider, or show “try later”.
- Extend RuntimeException (unchecked) when:
- - It’s a programming/config/business-rule violation where recovery isn’t guaranteed at every call site.
- - You prefer centralized handling (global exception mapper) and cleaner method signatures.
- - Example: OrderNotFoundException extends RuntimeException. API layer maps it to 404; most intermediate layers shouldn’t be forced to declare/catch it.

Q7. Explain the two important features of Enum: "Every element is in values" and "Every element is a constructor". How would you implement an Enum with a private constructor that accepts parameters?
- Feature 1: Every element is in values()
- - Java generates a static values() method returning all enum constants in declared order.
- Feature 2: Every element is a constructor
- - Each enum constant is an instance created by calling the enum’s constructor.
- How implement:
- - Enum constructors are always private (explicit or implicit).
- - Each enum constant calls the constructor with parameters.
- - Enums can have fields and methods like normal classes.

Q8. Describe the popular Enum template pattern (Interface + Enum + Exception). What are its four components? How does using an interface type (IErrorCode) allow the exception class to accept multiple different enum types?
- A common pattern for standardized error handling
- Four components:
- - Interface (e.g., IErrorCode) — defines required fields like getCode(), getMessage().
- - One or more Enums implementing the interface — e.g., UserErrorCode, OrderErrorCode.
- - Exception class that accepts the interface type — e.g., BizException(IErrorCode code).
- - Usage/throwing site (service/controller) — throws new BizException(UserErrorCode.INVALID_EMAIL).
- How: the exception constructor takes the interface type, any enum implementing it is accepted polymorphically.

Q9. Compare the three major Collection interfaces: List, Set, and Queue. For each, explain: (1) Ordering characteristics, (2) Duplicate element handling, (3) Most commonly used implementation class, (4) One typical use case.
- List
- - Ordering: preserves insertion order; index-based access.
- - Duplicates: allowed.
- - Common impl: ArrayList.
- - Use case: ordered items, frequent reads by index (UI list, history list).
- Set
- - Ordering: depends: HashSet no guaranteed order; LinkedHashSet insertion order; TreeSet sorted order
- - Duplicates: not allowed.
- - Common impl: HashSet.
- - Use case: de-duplication, membership checks (unique user IDs).
- Queue
- - Ordering: typically FIFO (depends on implementation).
- - Duplicates: allowed.
- - Common impl: ArrayDeque (or LinkedList, but ArrayDeque is preferred for queue/deque).
- - Use case: task scheduling, BFS, producer-consumer buffers.

Q10. Explain the difference between HashMap and Hashtable. Why is Hashtable considered obsolete? What are the modern alternatives for thread-safe Map implementations?
- HashMap: Not thread-safe, allows one null key and multiple null values, faster in single-threaded use.
- Hashtable: Thread-safe via synchronized methods, does not allow null keys or values, slower due to coarse-grained locking → considered obsolete.
- Why obsolete: Uses inefficient method-level synchronization and outdated design.
- Modern thread-safe alternatives: ConcurrentHashMap