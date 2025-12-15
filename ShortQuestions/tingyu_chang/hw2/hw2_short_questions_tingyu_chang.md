### 1\. What is the difference between a class and an object? Provide a real-world example.

*   A class serves as the definition or blueprint of a module, outlining its structure and behaviors.

*   An object, conversely, is a specific instance created from that class definition, representing a realization of the blueprint in memory.

*   Real-world example: 
*   Car (The general design: every car has a color, a brand, and can drive).
*   Object: myTesla (A specific red Tesla Model 3 that exists in your driveway).
*   Code Example:
```
// 1. The Class (The Blueprint)
class Car {
    String color;
    String brand;

    void drive() {
        System.out.println("The car is moving.");
    }
}

public class Main {
    public static void main(String[] args) {
        // 2. The Object (The Specific Instance)
        Car myTesla = new Car(); 
        
        // Setting specific properties for this object
        myTesla.color = "Red";
        myTesla.brand = "Tesla";
        
        // Using the object
        myTesla.drive(); 
    }
}

```
---

### 2\. If you create a parameterized constructor in a class, what happens to the default constructor? What must you do if you still need it? 
* If a class does not define any constructor, the JVM automatically provides a default, no-argument constructor. 
* However, once a parameterized constructor is explicitly defined, the JVM stops to provide the default constructor. 
* Consequently, if a no-argument constructor is still required for the implementation, it must be explicitly defined in the code.

---

### 3\. What are the four access modifiers in Java? List them from most restrictive to least restrictive.
* private: Declarations are visible only within the defining class.

* Default (Package-Private): Declarations are visible only within the same package.

* protected: Declarations are visible within the same package or to all subclasses.

* public: Declarations are visible everywhere.

---

### 4\. Explain the difference between method overloading and method overriding.
* Method Overloading occurs within the same class and is resolved at compile time. It requires the argument list (method signature) to be different, while the return type does not affect the distinction.

* Method Overriding occurs between a base class and a derived (child) class and is resolved at runtime. It requires both the argument list and the return type to be identical to the method in the parent class.

### 5\. Can you override a final method? Can you override a private method? Explain why or why not.
* Final Method: No. The specific purpose of the ```final``` modifier is to prevent a method from being modified or overridden by subclasses.

* Private Method: No. Because ```private``` methods are visible only within the class they are defined in, they are not accessible to subclasses and therefore cannot be overridden.

---

### 6\. What is the difference between static polymorphism and dynamic polymorphism? When does each occur?
* Static Polymorphism - Compile Time: 

* Definition: This occurs when the compiler determines which method to call based on the method signatures (the method name and argument types) at compile time.
* Mechanism: It is achieved through Method Overloading within the same class. Since the arguments for each overloaded method are different, the compiler can strictly link the method call to the correct implementation before the program even runs.
* Key Trait: It is faster (better performance) because the binding is static and done early.

* Dynamic Polymorphism - Runtime:
* Definition: This occurs when the JVM determines which method implementation to execute based on the actual object instance in memory, rather than the reference type, at runtime.
* Mechanism: It is achieved through Method Overriding in an inheritance hierarchy (Parent/Child classes). A parent class reference can hold a child class object (e.g., ```Vehicle v = new Car();```). When a method is called on ```v```, the JVM looks at the actual object (```Car```) to decide which behavior to execute.
* Key Trait: It offers flexibility and extensibility, which allows the same code to handle different object types dynamically.

---

### 7\. Why does Java not support multiple inheritance with classes? How can you achieve multiple inheritance in Java?
* Why it is not supported: Java avoids multiple inheritance with classes to prevent ambiguity issues, commonly known as the "Diamond Problem." 
* If a class were to extend two different parent classes that both had a method with the same name (e.g., ```drive())```, the compiler would not know which parent's method to inherit or execute. The class notes explicitly illustrate this with diagrams showing that extending multiple classes is "not allowed".
* How to achieve multiple inheritance: You can achieve multiple inheritance in Java by using Interfaces. While a class can extend only one parent class, it can implement multiple interfaces. This allows a class to inherit behaviors from multiple sources without the ambiguity of state implementation.
* "Illegal" Code Example:
```
class Parent1 {
    void drive() { System.out.println("Parent1 driving"); }
}

class Parent2 {
    void drive() { System.out.println("Parent2 driving"); }
}

// COMPILER ERROR: Java does not allow extending multiple classes
// If this were allowed, calling child.drive() would be ambiguous.
class Child extends Parent1, Parent2 { 
    public static void main(String[] args) {
        Child c = new Child();
        c.drive(); // ERROR: Which drive() should run? Parent1 or Parent2?
    }
}
```
* Valid Code Example: 
```
// Interface 1
public interface Fly {
    void fly();
}

// Interface 2
public interface Swim {
    void swim();
}

// Valid Multiple Inheritance: A Fish can both Fly and Swim
public class Fish implements Fly, Swim {
    
    @Override
    public void fly() {
        System.out.println("A fish can fly in the air.");
    }

    @Override
    public void swim() {
        System.out.println("A fish can swim in the ocean.");
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Fish myFish = new Fish();
        myFish.fly();  // Works perfectly
        myFish.swim(); // Works perfectly
    }
}

```
---

### 8\. Consider the following code: List<Integer> lst = new ArrayList<>();. Which principle of OOP does this demonstrate? Explain
* Principle: This demonstrates Polymorphism, specifically Dynamic Polymorphism.

* Explanation: The code uses a parent reference type (```List```, which is an interface) to hold a reference to a specific child object (```ArrayList```).

* Why it matters: By programming to the interface (```List```) rather than the implementation (```ArrayList```), your code becomes decoupled from the specific details of how the list works. This gives you the flexibility to easily switch the object to a ```LinkedList``` or ```Vector``` later without breaking other parts of your code that rely on ```lst```. 
* The class notes highlight this exact line of code when discussing runtime behavior and overriding.
* Code Example:
```
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PolymorphismDemo {
    public static void main(String[] args) {
        // --- Programming to the Interface (List) ---
        // The reference type is 'List' (Interface), but the object is 'ArrayList'
        List<Integer> lst = new ArrayList<>(); 
        
        lst.add(10);
        lst.add(20);
        System.out.println("Using ArrayList: " + lst);

        // --- The Flexibility Benefit ---
        // Because 'lst' is defined as the interface type 'List', 
        // we can easily switch the underlying object to a 'LinkedList' 
        // without breaking the code that uses 'lst'.
        lst = new LinkedList<>(); 
        
        lst.add(30);
        lst.add(40);
        System.out.println("Switched to LinkedList: " + lst);
    }
}
```
* If you had declared ```ArrayList<Integer> lst = new ArrayList<>();``` instead, you would be strictly tied to ```ArrayList```. You would not be allowed to write ```lst = new LinkedList<>();``` later in the code because a ```LinkedList``` is not an ```ArrayList```. By using the parent interface ```List```, your code becomes flexible enough to accept any implementation of a List.

---
