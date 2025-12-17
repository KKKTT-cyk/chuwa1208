**Q1. What is an abstract class? Why cannot an abstract class be instantiated directly? Can an abstract class have a constructor? If yes, what access modifier is typically used for it?**

An abstract class is a blueprint for other classes. It allows you to define a common set of characteristics (fields) and behaviors (methods) that all subclasses should share. Unlike an interface, it can hold state (instance variables) and logic (fully implemented methods).

An abstract class cannot be instantiated directly because it may contain incomplete (abstract) methods. These methods represent abstract concepts rather than concrete implementations.

Yes, an abstract class can have a constructor, typically with a `protected` or `public` access modifier, to allow initialization in subclasses.


**Q2. What types of methods and member variables can an abstract class contain? List at least 4 types. Is it correct to say "an abstract class can only contain abstract methods"? Explain.**

An abstract class can contain:
1. Abstract methods
2. Concrete methods with implementation
3. Instance variables
4. Static variables and methods
5. Final variables and methods

It is incorrect to say that an abstract class can only contain abstract methods. An abstract class can have fully implemented methods and fields to provide shared behavior to its subclasses.


**Q3. What are the default modifiers for methods and variables in an interface? Why can't an interface have a constructor?**

In an interface:
- Methods are implicitly `public` and `abstract` (unless `default` or `static`)  
- Variables are implicitly `public`, `static`, and `final`  

An interface cannot have a constructor because it is not meant to be instantiated; it only defines a contract for classes to implement.


**Q4. Can a class extend multiple classes in Java? Can a class implement multiple interfaces? Explain the syntax for each case.**

A class **cannot extend multiple classes** in Java because Java does not support multiple inheritance for classes.  
A class **can implement multiple interfaces** using a comma-separated list. 

```
class MyClass extends BaseClass implements Interface1, Interface2 {
    // class body
}
```


**Q5. Explain the relationship between interfaces: can an interface extend another interface? If yes, what keyword is used? Can an interface extend multiple interfaces?**

Yes, an interface can extend another interface using the extends keyword. An interface can also extend multiple interfaces by separating them with commas.

```
interface SubInterface extends Interface1, Interface2 {
    // interface body
}
```


**Q6. Compare abstract classes and interfaces from the following three perspectives: (1) Design intent (what it represents), (2) Multiple inheritance support, (3) Constructors and member variables.**

1. Design intent:

    - Abstract class: represents a shared base with common behavior and possibly incomplete methods.

    - Interface: represents a contract or capability that implementing classes must fulfill.

2. Multiple inheritance support:

    - Abstract class: cannot inherit from multiple classes.

    - Interface: can be implemented multiple times (supports multiple inheritance of type).

3. Constructors and member variables:

    - Abstract class: can have constructors and instance variables.

    - Interface: cannot have constructors; variables are implicitly public static final.


**Q7. Why does Java not allow multiple inheritance of classes but allows multiple implementation of interfaces? How does this design avoid the Diamond Problem?**

Java disallows multiple inheritance of classes to avoid the Diamond Problem, where a subclass could inherit conflicting implementations from multiple parent classes.
Interfaces allow multiple implementation because they primarily provide method signatures without implementation (except default/static methods), so there is no ambiguity in inheritance.


**Q8. Explain the difference between Aggregation and Composition. Focus on the difference in object lifetime management and provide one practical example for each.**

Aggregation: A weak "has-a" relationship. The lifetime of the contained object is independent of the container.
```
public class Aggregation {
    /**
     * Engine and Car are inner classes.
     * They can only exist inside an instance of the outer class.
     * That's why we have to make them static.
     */

    static class Car {
        private Engine engine;

        public Car(Engine engine) {
            this.engine = engine;
        }

        public void start() {
            engine.start();
            System.out.println("Car started");
        }
    }

    static class Engine {
        void start() {
            System.out.println("Engine started");
        }
    }

    public static void main(String[] args) {
        Engine engine = new Engine();
        Car car = new Car(engine);
        car.start();
    }
}
```

Composition: A strong "has-a" relationship. The lifetime of the contained object depends on the container.
```
public class composition {

    /**
     * Composition creates strong coupling.
     * Only use composition when the inner class is dependent on the outer class.
     * In other words, the inner class can't exist without the outer class.
     */

    static class Engine {
        void start() {
            System.out.println("Engine started");
        }
    }

    static class Car {
        private Engine engine;

        public Car() {
            engine = new Engine();
            System.out.println("Car created");
        }

        void start() {
            engine.start();
            System.out.println("Car started");
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.start();
    }
}
```


**Q9. In design patterns and SOLID principles, there is an important principle: "Favor composition over inheritance." Explain why composition is generally more flexible than inheritance.**

Inheritance creates tight coupling. Subclasses are tightly coupled to their parent classes. This creates the Fragile Base Class problem: if you change a single method in the base class, it can cause a ripple effect that breaks multiple levels of subclasses.

Composition is loosely coupled. The containing class only interacts with components through their public interfaces. It doesn't care how they are implemented, making it much easier to swap or update individual parts without breaking the whole system.

Example:

If you create an Orc class that inherits from MeleeAttacker, but then decide you want an Orc that can cast spells, you are stuck. Does it inherit from MeleeAttacker or Spellcaster?

Composition Solution: Give your Character class a list of Behaviors. An Orc can simply have an AxeAttack behavior and a Fireball behavior added to its list.


**Q10. Describe the three core characteristics of the Singleton design pattern. Why is the Static Inner Class implementation of Singleton thread-safe and efficient? How does this approach achieve lazy initialization?**

1. Only one instance of the class exists.

2. Global access point to that instance.

3. Private constructor to prevent external instantiation.

In many implementations, you have to use the synchronized keyword to prevent two threads from creating two different instances at the exact same time. However, synchronization is expensive and slows down performance.

The Static Inner Class approach is thread-safe without using synchronization. It relies on the Java Virtual Machine (JVM) class-loading guarantees:

The JVM ensures that a class is only loaded once.

The JVM guarantees that all static initializers are executed in a thread-safe manner before any thread can use the class.

It's efficient it doesn't use the synchronized keyword on the getInstance() method, there is no "lock" overhead. Once the instance is created, every call to getInstance() is a direct, high-speed return of the existing object.

```
private static Singleton instance;

public static Singleton getInstance() {
    if (instance == null) {
        // lazy initialization happens here
        instance = new Singleton();
    }
    return instance;
}
```