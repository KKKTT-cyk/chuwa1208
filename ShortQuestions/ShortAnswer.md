# Java 8 Interfaces, Lambdas, and Optional — Conceptual Review

---

## Q1. Why did Java 8 introduce **default methods** in interfaces?

### Primary Reason
The main reason Java 8 introduced **default methods** was to **maintain backward compatibility** while evolving interfaces.

Before Java 8, adding a new method to an existing interface would **break all implementing classes**, because they would be forced to implement the new method.

### How Default Methods Help
A default method provides a **method implementation inside the interface itself**, so:
- Existing implementations continue to work without changes
- New behavior can be added safely

This was especially critical for updating **core JDK interfaces** used by millions of classes.

### JDK Example
- `java.util.Collection`
- `java.lang.Iterable`
- `java.util.List`

These interfaces gained methods like `forEach`, `removeIf`, and `spliterator` using default methods in Java 8.

---

## Q2. Default Methods vs Static Methods in Interfaces

### (1) How they are called
- **Default methods** are called on an **instance**
- **Static methods** are called on the **interface name**

### (2) Whether they can be overridden
- **Default methods** can be overridden by implementing classes
- **Static methods** cannot be overridden (they belong strictly to the interface)

### (3) Typical use cases
- **Default methods**
    - Provide backward-compatible behavior
    - Add common instance-level functionality
- **Static methods**
    - Utility/helper methods related to the interface
    - Factory methods or validation logic

---

## Q3. What is a Functional Interface?

### Definition
A **Functional Interface** is an interface that has **exactly one abstract method**.

This single abstract method makes the interface compatible with **lambda expressions**.

### Role of `@FunctionalInterface`
- Enforces at compile time that the interface has only one abstract method
- Improves readability and intent
- Prevents accidental addition of extra abstract methods

### Can it have multiple default methods?
Yes.

A functional interface:
- Must have **one abstract method**
- Can have **any number of default or static methods**

Default methods do not count toward the abstract method count.

---

## Q4. Four Major Functional Interface Categories (`java.util.function`)

### 1. Consumer
- **Method signature**: accepts one input, returns nothing
- **Represents**: an operation that consumes a value
- **Use case**: logging, printing, updating external state

---

### 2. Supplier
- **Method signature**: takes no input, returns a value
- **Represents**: value generation or lazy creation
- **Use case**: object factories, lazy initialization, default values

---

### 3. Predicate
- **Method signature**: takes one input, returns boolean
- **Represents**: a condition or test
- **Use case**: filtering collections, validation logic

---

### 4. Function
- **Method signature**: takes one input, returns one output
- **Represents**: transformation from one type to another
- **Use case**: mapping values, data conversion

---

## Q5. What is “effectively final”?

### Definition
A variable is **effectively final** if:
- It is assigned only once
- Its value is never changed after initialization

Even without the `final` keyword, Java treats it as final.

### Why Java Requires This for Lambdas
- Lambdas may execute later or in another thread
- Captured variables must be stable and predictable
- Prevents concurrency and memory consistency issues

### What Happens if You Modify It
- Modifying a variable **before or after** it is used in a lambda causes a **compile-time error**
- Java enforces immutability of captured variables

---

## Q6. Types of Method References

### (1) `ClassName::staticMethod`
- Refers to a static method
- Parameters map directly to method arguments

---

### (2) `object::instanceMethod`
- Refers to a specific object’s instance method
- The object is already known

---

### (3) `ClassName::instanceMethod`
- Refers to an instance method of an arbitrary object of that class
- The **first parameter becomes the calling object**
- Remaining parameters map to method arguments

### Why the First Parameter Becomes the Calling Object
Because Java needs an instance to call an instance method, and the method reference syntax allows the lambda’s first argument to supply that instance implicitly.

---

## Q7. `Optional.of()` vs `Optional.ofNullable()`

### `Optional.of()`
- Requires a **non-null** value
- Passing `null` throws a **NullPointerException**
- Use when null is not allowed and indicates a bug

### `Optional.ofNullable()`
- Accepts null or non-null
- Null becomes `Optional.empty()`
- Use when null is a valid possibility

---

## Q8. `orElse()` vs `orElseGet()`

### Key Difference
- **`orElse()`** eagerly evaluates its argument
- **`orElseGet()`** lazily evaluates the supplier only when needed

### Efficiency
`orElseGet()` is more efficient when the default value is **expensive to compute**, because it avoids unnecessary computation when the Optional is present.

### Best Practice
- Cheap constants → `orElse()`
- Expensive operations → `orElseGet()`

---

## Q9. Why is `Optional.get()` Dangerous?

### Problem
- Calling `get()` on an empty Optional throws `NoSuchElementException`
- Encourages null-check–like behavior, defeating Optional’s purpose

### Recommended Alternatives
- `orElse()` / `orElseGet()` — provide defaults
- `orElseThrow()` — throw a controlled exception
- `ifPresent()` — perform actions safely
- `map()` / `flatMap()` — transform values safely

### Best Practices
- Avoid `get()` unless you are absolutely certain the Optional is non-empty
- Prefer declarative, safe extraction patterns
- Treat Optional as a **value container**, not a nullable replacement

---
