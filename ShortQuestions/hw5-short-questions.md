**Q1. What is the primary reason Java 8 introduced default methods in interfaces? Explain how default
methods help maintain backward compatibility when adding new methods to existing interfaces. Give an
example of a JDK interface that uses default methods.**

The primary reason is to allow interfaces to evolve without breaking existing implementations.

Before Java 8, adding a new method to an interface would break every class that implements it, because those classes would be forced to implement the new method.

How default methods maintain backward compatibility?

A default method provides an implementation inside the interface itself.
So when a new method is added:

- Existing implementing classes inherit the default behavior

- They do not need to change

- New implementations may override it if needed

Example from JDK

```
public interface List<E> extends Collection<E> {
    default void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        for (int i = 0; i < size(); i++) {
            set(i, operator.apply(get(i)));
        }
    }
}

//Older ArrayList, LinkedList, etc. did not break when replaceAll() was added.
```

**Q2. Compare default methods and static methods in interfaces. What are the key differences in (1) how
they are called, (2) whether they can be overridden, and (3) their typical use cases?**

| Aspect                  | Default Method                          | Static Method          |
| ----------------------- | --------------------------------------- | ---------------------- |
| **How they are called** | Via instance                            | Via interface name     |
| **Can be overridden?**  | ✅ Yes                                   | ❌ No                   |
| **Belongs to**          | Implementing class instances            | Interface itself       |
| **Typical use case**    | Provide shared behavior to implementers | Utility/helper methods |


**Q3. What is a Functional Interface? What role does the @FunctionalInterface annotation play? Can a functional interface have multiple default methods? Explain with an example.**

A functional interface is an interface that has exactly one abstract method.

Examples:

- Runnable

- Callable

- Comparator

- Function, Predicate, etc.

Role of @FunctionalInterface

- Enforces the single abstract method rule

- Causes a compile-time error if violated

- Improves readability and intent

A functional interface can have any number of default or static methods. It is only the "abstract" method count that must be exactly one.

```
@FunctionalInterface
interface Formatter {
    String format(String input);

    default String trim(String input) {
        return input.trim();
    }

    default String shout(String input) {
        return input.toUpperCase();
    }
}
```


**Q4. Describe the four major categories of functional interfaces in java.util.function package. For each category (Consumer, Supplier, Predicate, Function), explain (1) its method signature, (2) what it represents, and (3) give one practical use case.**


| Category      | Interface        | Method Signature    | Represents                   | Typical Use Case                             |
| ------------- | ---------------- | ------------------- | ---------------------------- | -------------------------------------------- |
| **Consumer**  | `Consumer<T>`    | `void accept(T t)`  | Takes input, returns nothing | Printing, logging, saving data, side effects |
| **Supplier**  | `Supplier<T>`    | `T get()`           | Provides a value, no input   | Lazy initialization, default values          |
| **Predicate** | `Predicate<T>`   | `boolean test(T t)` | Condition / boolean check    | Filtering, validation                        |
| **Function**  | `Function<T, R>` | `R apply(T t)`      | Transforms input to output   | Mapping, data transformation                 |


**Q5. What is "effectively final"? Why does Java require variables used in lambda expressions to be final or effectively final? What happens if you try to modify a variable before or after it's used in a lambda?**

A variable is effectively final if its value is never changed after initialization, even if it isn't explicitly marked with the final keyword.

The "Why": Lambdas capture the value of the variable, not the variable itself. To prevent "race conditions" in multi-threaded environments and ensure the lambda sees a consistent value, Java requires this stability.

Modifying Variables: If you try to modify the variable before or after the lambda uses it, the compiler will throw an error: "local variables referenced from a lambda expression must be final or effectively final."


**Q6. Explain the difference between the three types of method references: (1)ClassName::staticMethod, (2) object::instanceMethod, and (3) ClassName::instanceMethod. For type 3, explain why the first parameter becomes the calling object.**

Method references are shorthand for lambdas that call a specific method.

1. ```ClassName::staticMethod```: Refers to a static method. Lambda equivalent: (args) -> ClassName.staticMethod(args)

2. ```object::instanceMethod```: Refers to an instance method of a specific, existing object. Lambda equivalent: (args) -> myObject.instanceMethod(args)

3. ```ClassName::instanceMethod```: Refers to an instance method of an arbitrary object of a particular type.

    The First Parameter: In this case, the first argument of the lambda becomes the receiver (the object the method is called on).

    Example: ```String::toUpperCase``` is equivalent to ```(String s) -> s.toUpperCase()```. Here, s is the calling object.


**Q7. What is the difference between Optional.of() and Optional.ofNullable()? When should you
use each one? What happens if you pass null to Optional.of()?**

Optional.of(value): Use this when you are certain the value is not null. If you pass null, it throws a NullPointerException immediately.

Optional.ofNullable(value): Use this when the value might be null. If the value is null, it returns an Optional.empty() instead of crashing.


**Q8. Compare orElse() and orElseGet() methods in Optional. Which one is more efficient when the default value is expensive to compute? Explain with an example.**

```orElse(value)```: The value is evaluated every time, even if the Optional is not empty.

```orElseGet(Supplier)```: The Supplier is executed only if the Optional is empty.

Efficiency: orElseGet() is more efficient for "expensive" computations (like a database call or complex object creation).

Example: opt.orElse(createNewUser()) will create a user object even if opt has a value. opt.orElseGet(() -> createNewUser()) will only create the user if opt is empty.


**Q9. Why is Optional.get() considered dangerous? What are the recommended alternatives? Explain the best practices for extracting values from Optional objects.**

Optional.get() is considered dangerous because if the Optional is empty, it throws a NoSuchElementException, which defeats the purpose of using Optional to avoid exceptions.

Recommended Alternatives & Best Practices:

- orElse() / orElseGet(): To provide a default value.

- ifPresent(): To perform an action only if the value exists.

- map() / flatMap(): To transform the value without manually checking for null.

- orElseThrow(): To throw a specific, meaningful exception if the value is missing.

Best Practice: Use Optional primarily as a return type for methods that might not have a result. Avoid using it for class fields or method parameters.