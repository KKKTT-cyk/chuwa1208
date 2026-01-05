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
A local variable is effectively final if: You assign it once, and you never reassign it after that.
Requiring final/effectively final guarantees the captured value is stable and avoids weird “half-mutable” semantics.
If you reassign before the lambda is created, that’s fine—as long as after the final assignment you don’t reassign again
Java rejects it at compile time because the variable is not effectively final.
Mutating the referenced object is allowed (though you should consider thread-safety).

Q6. Explain the difference between the three types of method references: (1)
ClassName::staticMethod, (2) object::instanceMethod, and (3)
ClassName::instanceMethod. For type 3, explain why the first parameter becomes the calling object.
(1) Call this static method on the class.
(2) Call this instance method on this particular object.
(3) Call this instance method on the first argument.
The first parameter of the functional interface becomes the receiver object (this),
and the remaining parameters are passed to the method.


Q7. What is the difference between Optional.of() and Optional.ofNullable()? When should you use each one? What happens if you pass null to Optional.of()?
If value is null, Optional.of() throws NullPointerException immediately.
If value is null Optional.ofNullable() get Optional.empty() (no exception).
Use Optional.of(...) when: Null would indicate a bug / invalid state You want a clear, immediate failure (fail-fast)
Use Optional.ofNullable(...) when: You’re wrapping a value that comes from a place where null is possible (DB, map lookup, external API, legacy code). You want to convert null → empty cleanly

Q8. Compare orElse() and orElseGet() methods in Optional. Which one is more efficient when the
default value is expensive to compute? Explain with an example.
orElse(): The defaultValue is evaluated immediately, even if the Optional is present. So it can waste work if the default is expensive.
orElseGet(): The supplier is called lazily: only if the Optional is empty. This avoids unnecessary computation.
orElseGet() is more efficient when the default is expensive to compute, because it computes it only when needed.

Q9. Why is Optional.get() considered dangerous? What are the recommended alternatives? Explain the
best practices for extracting values from Optional objects.
Optional.get() is considered dangerous because it throws an exception if the Optional is empty.
Need a default → orElse / orElseGet
Missing is an error → orElseThrow
Side effect only → ifPresent / ifPresentOrElse
Transform/chaining → map / flatMap