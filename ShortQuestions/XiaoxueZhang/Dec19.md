Q1. What is the primary reason Java 8 introduced default methods in interfaces? Explain how default
methods help maintain backward compatibility when adding new methods to existing interfaces. Give an
example of a JDK interface that uses default methods.
Before Java 8, adding a new method to an interface would break all existing implementations.
If a class doesn’t implement the new method, it automatically inherits the default implementation. So existing code keeps working unchanged.
A common example is the List.sort() method added in Java 8.

Q2. Compare default methods and static methods in interfaces. What are the key differences in (1) how they are called, (2) whether they can be overridden, and (3) their typical use cases?
static methods called on interface name, not on an instance
static methods cant be overridden
use default methods when add new behavior to an interface without breaking existing implementations
use static methods when the logic does not depend on instance state

Q3. What is a Functional Interface? What role does the @FunctionalInterface annotation play? Can a
functional interface have multiple default methods? Explain with an example.
A functional interface is an interface with exactly one abstract method.
@FunctionalInterface annotation: The compiler will enforce the rule: only one abstract method allowed. If you accidentally add a second abstract method, you’ll get a compile-time error.
A functional interface can still have multiple default and static methods.

Q4. Describe the four major categories of functional interfaces in java.util.function package. For
each category (Consumer, Supplier, Predicate, Function), explain (1) its method signature, (2) what it
represents, and (3) give one practical use case.
Consumer<T>
void accept(T t)
“Consumes” a value — does something with T but returns nothing.
Printing/logging, writing to a file, sending a message.
Supplier<T>
T get()
“Supplies” a value — produces a T with no input.
Lazy object creation, generating random values, default values.
Predicate<T>
boolean test(T t)
A boolean condition (true/false check) on a value.
Filtering lists/streams, validation rules.
Function<T, R>
R apply(T t)
A transformation from one type to another: T → R
Mapping objects, converting types, extracting fields.


Q5. What is "effectively final"? Why does Java require variables used in lambda expressions to be final or
effectively final? What happens if you try to modify a variable before or after it's used in a lambda?
Q6. Explain the difference between the three types of method references: (1)
ClassName::staticMethod, (2) object::instanceMethod, and (3)
ClassName::instanceMethod. For type 3, explain why the first parameter becomes the calling object.
Q7. What is the difference between Optional.of() and Optional.ofNullable()? When should you
use each one? What happens if you pass null to Optional.of()?
Q8. Compare orElse() and orElseGet() methods in Optional. Which one is more efficient when the
default value is expensive to compute? Explain with an example.
Q9. Why is Optional.get() considered dangerous? What are the recommended alternatives? Explain the
best practices for extracting values from Optional objects.