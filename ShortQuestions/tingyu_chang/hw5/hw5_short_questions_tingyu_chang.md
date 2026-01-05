### Q1\. What is the primary reason Java 8 introduced default methods in interfaces? Explain how default methods help maintain backward compatibility when adding new methods to existing interfaces. Give an example of a JDK interface that uses default methods.

* Primary Reason: The primary reason was to allow interface evolution. Before Java 8, if you added a new method to an interface, every single class implementing that interface would break (compile error) until they implemented the new method.
* Backward Compatibility: Default methods allow library designers to add new methods to interfaces with a default implementation provided directly in the interface. Existing classes that implement the interface will automatically inherit this default implementation. They do not need to be modified or recompiled, ensuring backward compatibility.
* JDK Example: The `java.util.Collection` interface. Java 8 added the `stream()` method to `Collection`. Because `stream()` was defined as a default method, all existing `ArrayList`, `HashSet`, and custom `Collection` implementations continued to work without needing to write a `stream()` method themselves.

---

### Q2\. Compare default methods and static methods in interfaces. What are the key differences in (1) how they are called, (2) whether they can be overridden, and (3) their typical use cases?
1. How they are called
* Default Methods: These are called on an instance of the class that implements the interface.
* Example: `myObject.defaultMethod()`
* Static Methods: These are called directly using the Interface name itself, similar to static methods in a class.
* Example: `MyInterface.staticMethod()`

2. Whether they can be overridden
* Default Methods: Yes, they can be overridden. Implementing classes are free to provide their own implementation if the default behavior is not suitable.
* Static Methods: No, they cannot be overridden. Static methods belong to the interface context, not the inheritance chain, so implementing classes cannot change their behavior.

3. Typical Use Cases
* Default Methods: Their primary use is interface evolution. They allow library designers to add new methods to existing interfaces without breaking the code of all the classes that currently implement that interface (backward compatibility).
* Static Methods: Their primary use is for utility or helper methods. They are used for functionality that is relevant to the interface concept, like factory methods or validators, but does not depend on the state of a specific object instance.

---

### Q3\.What is a Functional Interface? What role does the @FunctionalInterface annotation play? Can a functional interface have multiple default methods? Explain with an example.
* Definition: A Functional Interface is an interface that contains exactly one abstract method (Single Abstract Method - SAM). It serves as the type for Lambda expressions and Method References.
* Role of Annotation: `@FunctionalInterface` is informative and acts as a compiler check. It is not mandatory, but if you add it, the compiler will throw an error if the interface does not satisfy the "exactly one abstract method" rule.
* Multiple Default Methods: Yes. Default methods and static methods do not count toward the abstract method limit.
* Example:
```
@FunctionalInterface
public interface MyFunction {
    // One abstract method (Required)
    void execute(); 

    // Default methods are allowed (Optional)
    default void logStart() { System.out.println("Start"); }
    default void logEnd() { System.out.println("End"); }
}

```
---

### Q4\.Describe the four major categories of functional interfaces in java.util.function package.
1. Consumer:
* Signature: `void accept(T t)`
* Represents: An operation that takes a single input argument and returns no result, performs an action or side-effect.
* Use Case: Printing elements in a list: `names.forEach(name -> System.out.println(name));`

2. Supplier:
* Signature: `T get()`
* Represents: A supplier of results. It takes no arguments but returns a value, often used for lazy generation.
* Use Case: Generating a random number or a timestamp: `Supplier<Double> random = () -> Math.random();`

3. Predicate:
* Signature: `boolean test(T t)`
* Represents: A boolean-valued function of one argument. Used for conditional checks.
* Use Case: Filtering a stream: `list.stream().filter(n -> n > 10)...`

4. Function:
Signature: `R apply(T t)`
Represents: A function that accepts one argument and produces a result (transformation).
Use Case: Converting data types, like mapping a String to its length: `Function<String, Integer> lengthMapper = s -> s.length();`

---

### Q5\. What is "effectively final"? Why does Java require variables used in lambda expressions to be final or effectively final? What happens if you try to modify a variable before or after it's used in a lambda?
* Definition: A local variable is "effectively final" if it is initialized once and never assigned a new value, even if the `final` keyword is not explicitly written.
* Why Required: Java Lambdas create a copy of the variables they use (capturing values). They do not capture the variable "box" itself. If Java allowed the variable to change outside the lambda while the lambda was running, perhaps in a different thread, it would create concurrency issues and inconsistent behavior.
* Modification Consequence: If you try to modify a local variable that is referenced inside a lambda, either before or after the lambda definition, the compiler will throw an error: "local variables referenced from a lambda expression must be final or effectively final."

---

### Q6\. Explain the difference between the three types of method references: (1) ClassName::staticMethod, (2) object::instanceMethod, and (3) ClassName::instanceMethod. For type 3, explain why the first parameter becomes the calling object.
1. ClassName::staticMethod (Static):
* Refers to a static method of a class.
* Lambda equivalent: `(args) -> ClassName.staticMethod(args)`
2. object::instanceMethod (Bound):
* Refers to an instance method of a specific, existing object.
* Lambda equivalent: `(args) -> specificObject.instanceMethod(args)`
3. ClassName::instanceMethod (Unbound):
* Refers to an instance method, but you don't have the object yet.
* Why the first parameter becomes the calling object: The lambda context determines that the first argument passed to the lambda is the object upon which the method should be called. The remaining arguments are passed as parameters to that method.
* Lambda equivalent: `(obj, args) -> obj.instanceMethod(args)`

---

### Q7\. What is the difference between Optional.of() and Optional.ofNullable()? When should you use each one? What happens if you pass null to Optional.of()?
1. Optional.of(value): Expects a non-null value. Use this when you are strictly certain the value cannot be null (e.g., a constant or a result from a safe method).
* Result of passing null: It throws a `NullPointerException` immediately.

2. Optional.ofNullable(value): Accepts either a value or null. Use this when the data might be missing.
* Result of passing null: It returns an empty Optional (`Optional.empty()`).

---

### Q8\. Compare orElse() and orElseGet() methods in Optional. Which one is more efficient when the default value is expensive to compute? Explain with an example.
1. orElse(T other): This method always evaluates the argument, even if the Optional contains a value.

2. orElseGet(Supplier other): This method takes a Supplier and is lazy. It only executes the Supplier to generate the default value if the Optional is empty.

* Efficiency: `orElseGet()` is more efficient when the default value is expensive to compute (e.g., a database call or complex calculation).
* Example:
```
// Assuming userOpt contains a User
// The database query runs even though userOpt has a value -> Inefficient
User user = userOpt.orElse(database.findDefaultUser()); 

// The database query only runs if userOpt is empty. -> Efficient
User user = userOpt.orElseGet(() -> database.findDefaultUser());

```
---

### Q9\. Why is Optional.get() considered dangerous? What are the recommended alternatives? Explain the best practices for extracting values from Optional objects.
* Why Dangerous: `Optional.get()` throws a `NoSuchElementException` if the Optional is empty. If you use it without checking, you are essentially risking the same crash as a `NullPointerException`, just with a different exception name.
* Recommended Alternatives:
1. `orElse(defaultVal)`: Return a default value.
2. `orElseGet(supplier)`: Lazily return a default value.
3. `orElseThrow(exSupplier)`: Throw a specific exception suitable for your API.
4. `ifPresent(consumer)`: Perform an action only if the value exists.
* Best Practice: Do not treat Optional as a simple wrapper for null checks (i.e., avoid `if(opt.isPresent()) { return opt.get(); }`). 
* Instead, use the functional style:

```
// Good practice
String result = optionalName.map(String::toUpperCase)
.orElse("UNKNOWN");
```
---

