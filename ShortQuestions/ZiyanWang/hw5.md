Q1. What is the primary reason Java 8 introduced default methods in interfaces? Explain how default methods help maintain backward compatibility when adding new methods to existing interfaces. Give an example of a JDK interface that uses default methods.
- Reason: Maintain backward compatibility while allowing interfaces to evolve.
- How: A default method provides an implementation inside the interface, so existing classes automatically inherit it without modification.
- Example: List.sort() was added in Java 8 without breaking all existing List implementations.

Q2. Compare default methods and static methods in interfaces. What are the key differences in (1) how they are called, (2) whether they can be overridden, and (3) their typical use cases?
- How they are called: 1. Default: called on an instance 2. Static: called on the interface itself
- Overridden: 1. Default: Yes  2. Static: No
- Use case: 1. Default: extend interfaces with new behavior 2. Static: utility/helper methods related to the interface

Q3. What is a Functional Interface? What role does the @FunctionalInterface annotation play? Can a functional interface have multiple default methods? Explain with an example.
- A functional interface is an interface that has exactly one abstract method. This single abstract method allows the interface to be implemented using a lambda expression.
- It is optional. It enforces at compile time that the interface has exactly one abstract method. Prevents accidental addition of extra abstract methods
- Yes.
```java
  @FunctionalInterface
  interface MyFunction {
    void execute();           // single abstract method

    default void log() { }    // allowed
    default void reset() { }  // allowed
  }
```

Q4. Describe the four major categories of functional interfaces in the java.util.function package. For each category (Consumer, Supplier, Predicate, Function), explain (1) its method signature, (2) what it represents, and (3) give one practical use case.
- Consumer
- - Method: void accept(T t)
- - Represents: an operation that takes input and returns nothing
- - Use case: printing, logging, modifying external state
- Supplier
- - Method: T get()
- - Represents: a provider of values
- - Use case: lazy initialization, default values, expensive computations
- Predicate
- - Method: boolean test(T t)
- - Represents: a boolean condition
- - Use case: filtering, validation, conditional checks
- Function
- - Method: R apply(T t)
- - Represents: data transformation
- - Use case: mapping values, converting types

Q5. What is "effectively final"? Why does Java require variables used in lambda expressions to be final or effectively final? What happens if you try to modify a variable before or after it's used in a lambda?
- A variable is effectively final if: It is assigned once; Its value is never changed, even if not explicitly marked final
- Why does Java require this for lambdas?
- - Lambdas may execute later
- - Java captures variable values, not references
- - Enforcing immutability avoids concurrency and lifetime issues
- What happens if you modify the variable: Compilation error

Q6. Explain the difference between the three types of method references:
- (1) ClassName::staticMethod, 
- - Maps directly to a static method call.
- (2) object::instanceMethod, and
- - The method is called on a specific object.
- (3) ClassName::instanceMethod.
- - The instance method is called on the first parameter of the lambda expression.
For type 3, explain why the first parameter becomes the calling object.
- Because the instance method needs a target object.

Q7. What is the difference between Optional.of() and Optional.ofNullable()? When should you use each one? What happens if you pass null to Optional.of()?
- Optional.of(value): 
- - Does not allow null
- - Passing null throws NullPointerException
- Optional.ofNullable(value)
- - Allows null
- - Returns Optional.empty() if value is null
- When to use:
- - Use of() when you guarantee non-null
- - Use ofNullable() when value may be null

Q8. Compare orElse() and orElseGet() methods in Optional. Which one is more efficient when the default value is expensive to compute? Explain with an example.
- orElse(defaultValue)
- - Eager evaluation
- - Default value is computed even if Optional is not empty
- orElseGet(supplier)
- - Lazy evaluation
- - Supplier is called only if Optional is empty
- More efficient when default is expensive: orElseGet()
```java
optional.orElse(expensiveMethod());      // always executed
optional.orElseGet(this::expensiveMethod); // executed only if empty
```

Q9. Why is Optional.get() considered dangerous? What are the recommended alternatives? Explain the best practices for extracting values from Optional objects.
- Reason that Optional.get() is dangerous: Throws NoSuchElementException if value is absent; Reintroduces runtime failures similar to null
- Recommended alternatives
- - orElse()
- - orElseGet()
- - orElseThrow()
- - ifPresent()
- - map() / flatMap()
- Best practice: Treat Optional as a container to be transformed, not a value to be extracted blindly.