1. What is the difference between JDK, JRE, and JVM?
- JVM is engine that executes Java bytecode.
- JRE provides runtime environment including the JVM and core libraries.
- JDK includes everything needed to develop, compile, and run Java applications.
- In all, JDK contains JRE, JRE contains JVM

2. What are the main differences between primitive types and
reference types?
- Primitive types: store actual values (e.g. byte, short, int, long, float, double, char, boolean.) Can be compared by using '=='
- Reference types: store references to objects, (e.g. class, interface, array). '==' compares whether they point to same object, but compare content needs 'equals()'
- Primitive types cannot be null, but reference types can.

3. Is Java pass-by-value or pass-by-reference? Explain.
- **Java is pass-by-value**
- For primitive types, Java passes a copy of the value.
- For reference types, Java passes a copy of the reference, not the object itself.

4. Why are Strings immutable in Java?
- Strings are immutable for security, performance, and thread safety.
- Immutability allows Strings to be safely shared in the String pool and used across threads without synchronization.

5. What is the String Constant Pool and how does it work?
- The String Constant Pool is a special memory area in the heap where Java stores string literals. When a string literal is created, Java checks the pool first: if the string already exists, it reuses it; otherwise, it adds a new one to the pool.

6. What is the purpose of the final keyword in Java?
- Restrict modification. A final variables cannot be reassigned, method cannot be overridden, Class cannot be inherited.

7. What does the static keyword mean for variables or methods?
- means the variable or method belongs to the class itself, not to any specific object.
- static method: only one copy shared by all instances of the class.
- static method: can be called without creating an object and can only directly access static members.

8. What is a static block and when does it run?
- a block of code used to initialize static variables
- It runs once at class loading time, before any objects are created or static methods are called.

9. Can a static method access non-static variables? Why or why
not?
- No.
- Static methods belong to the class and are not associated with any specific object instance, so they cannot access non-static variables.

10. Describe the JVM loading order: static block, static variables, and
constructor.
- During Class loading: static variables are initialized and static blocks are executed in declaration order.
- During Object creation: constructor is executed

11. What is the difference between a static variable and a constant
defined as public static final?
- A static variable is shared by all instances and can be modified
- A public static final variable is a constant whose value cannot be changed.

12. Explain why immutable objects (like String) are thread-safe by
design. Provide an example scenario.
- Because their state cannot be changed after creation. Since no thread can modify the object, multiple threads can safely access it without synchronization.
- Example: Multiple threads can safely share "Cat" because any operation creates a new String rather than modifying the original one.

13. What problem does making a class final solve in terms of
immutability and inheritance security?
- Ensuring the class’s behavior cannot be changed by subclasses.
- This guarantees that the class’s state and methods cannot be overridden to introduce mutability or security vulnerabilities.