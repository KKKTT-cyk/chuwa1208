# Java OOP & Design Patterns – Answers

## Q1. What is an abstract class?

An **abstract class** is a class that cannot be instantiated directly and is intended to be **inherited**. It represents an **incomplete or partially implemented concept** and may contain abstract methods (methods without implementations).

**Why can’t it be instantiated directly?**
Because it may contain abstract methods that have no implementation, so creating an object of abstract class is meaningless.

**Can an abstract class have a constructor?**
Yes. Constructors are used to initialize common state when a subclass is instantiated.

**Typical access modifier:**

* `protected` (most common, accessible to subclasses)
* `public` is also allowed, but less common

---

## Q2. What types of methods and member variables can an abstract class contain?

An abstract class can contain **both abstract and concrete elements**, including:

1. **Abstract methods** (no method body)
2. **Concrete (implemented) methods**
3. **Instance variables**
4. **Static variables and static methods**
5. **Final methods** (cannot be overridden)
6. **Constructors**

❌ **Incorrect:** As listed above, Abstract classes can contain concrete methods.

---

## Q3. Default modifiers in an interface

### Methods

* Implicitly `public abstract`
* Since Java 8: may also have `default` and `static` methods

### Variables

* Implicitly `public static final` (constants)

### Why can’t an interface have a constructor?

* Interfaces do not represent objects or state
* No instance can ever be created from an interface
* Constructors are for object initialization, which does not apply

---

## Q4. Class inheritance vs interface implementation

### Can a class extend multiple classes?

❌ No. Java does **not** support multiple inheritance of classes.

### Can a class implement multiple interfaces?

✅ Yes.

### Syntax

```java
class Child extends Parent { } // can extend only one class 

class MyClass implements InterfaceA, InterfaceB { } // can implement multiple interface

class MyClass extends Parent implements InterfaceA, InterfaceB { }
```

---

## Q5. Relationship between interfaces

### Can an interface extend another interface?

✅ Yes, using the `extends` keyword.

### Can an interface extend multiple interfaces?

✅ Yes.

### Syntax

```java
interface C extends A, B { }
```

This works because interfaces only inherit method signatures (or compatible defaults).

---

## Q6. Abstract classes vs Interfaces

| Aspect                   | Abstract Class              | Interface                      |
| ------------------------ | --------------------------- | ------------------------------ |
| **Design intent**        | "is-a" with shared behavior | "can-do" / capability contract |
| **Multiple inheritance** | ❌ Not supported             | ✅ Supported                    |
| **Constructors**         | ✅ Yes                       | ❌ No                           |
| **Member variables**     | Any type                    | `public static final` only     |
| **Method types**         | Abstract + concrete         | Abstract, default, static      |

---

## Q7. Why Java disallows multiple inheritance of classes

### Problem: Diamond Problem

```text
    A
   / \
  B   C
   \ /
    D
```

* If B and C both override a method from A, which one should D inherit?

### Java’s solution

* ❌ No multiple class inheritance
* ✅ Allow multiple interfaces

Interfaces:

* Have no state
* Conflicts must be explicitly resolved
* Default methods require override if ambiguous

This avoids ambiguity while preserving flexibility.

---

## Q8. Aggregation vs Composition

### Aggregation (weak ownership)

* Objects have **independent lifetimes**
* "has-a" relationship

**Example:**

```java
class Team {
    List<Player> players;
}
```

Players can exist without the team.

### Composition (strong ownership)

* Child object’s lifecycle depends on parent
* Destroy parent → destroy children

**Example:**

```java
class House {
    private Room room = new Room();
}
```

Room cannot exist without the House.

---

## Q9. Favor Composition over Inheritance

### Why composition is more flexible

* Avoids tight coupling
* Behavior can be changed at runtime
* Prevents fragile base class problem
* Encourages modular, testable code

**Inheritance:** compile-time, rigid
**Composition:** runtime, flexible

Used heavily in design patterns like Strategy, Decorator, Adapter.

---

## Q10. Singleton Pattern

### Three core characteristics

1. **Only one instance**
2. **Global access point**
3. **Controlled instantiation**

### Why it is thread-safe and efficient

* Class loading is thread-safe in Java
* `Holder` is not loaded until `getInstance()` is called

### How lazy initialization is achieved

* JVM loads the inner class **only when referenced**
* No synchronization overhead
* Instance created exactly once

---

**Summary:** This approach combines lazy loading, thread safety, and high performance without explicit locks.
