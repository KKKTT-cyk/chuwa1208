### Q1\. What is an abstract class? Why cannot an abstract class be instantiated directly? Can an abstract class have a constructor? If yes, what access modifier is typically used for it?

* Definition: An abstract class is a class that is used when there is some common behavior that all subclasses must share (e.g., ```Shape```, ```Animal```), but the specific implementation details may vary.
* Instantiation: An abstract class cannot be instantiated directly (e.g., you cannot do ```new Person()``` if ```Person``` is abstract) because it is incomplete and meant to be a base for subclasses.
* Constructor: Yes, an abstract class can have constructors.
* Access Modifier: The constructor in an abstract class is typically ```protected``` (e.g., ```protected AbstractCollection()```) because it is meant to be invoked by subclass constructors.
* Code Example:
```
// 1. Abstract Class
// Defined using the 'abstract' keyword
abstract class Animal {
    // Abstract Method: Has no body, must be implemented by the child class
    public abstract void makeSound();

    // Normal Method: Can have a body and be used by child classes
    public void sleep() {
        System.out.println("Zzz...");
    }
}

// 2. Concrete Class
// 'Dog' extends 'Animal', so it MUST implement the abstract method 'makeSound'
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark! Bark!");
    }
}

// 3. Main Class
public class Main {
    public static void main(String[] args) {
        // ERROR: You cannot instantiate an abstract class directly
        // Animal myAnimal = new Animal(); // This line would cause a compiler error
        
        // CORRECT: You must instantiate the concrete child class
        Animal myDog = new Dog(); 
        myDog.makeSound(); // Outputs: Bark! Bark!
        myDog.sleep();     // Outputs: Zzz...
    }
}
```
---

### Q2\. What types of methods and member variables can an abstract class contain? List at least 4 types. Is it correct to say "an abstract class can only contain abstract methods"? Explain.

* Types of Members: An abstract class can contain:
* Abstract methods (methods without a body).
* Non-abstract / Normal methods (methods with implementation).
* Constructors.
* Static variables and methods.
* Is the statement ("an abstract class can only contain abstract methods"?) correct? No, it is incorrect. An abstract class is not limited to just abstract methods; it can have everything a normal Java class has.

---

### Q3\. What are the default modifiers for methods and variables in an interface? Why can't an interface have a constructor?

* Default Modifiers in an interface:
* Methods: By default, they are ```public```.
* Variables: By default, they are ```public static final```.
* Why no constructor: Interfaces cannot have constructors. This is because interfaces do not have non-static fields that require initialization, unlike abstract classes which have constructors because they may have non-static fields.

---

### Q4\. Can a class extend multiple classes in Java? Can a class implement multiple interfaces? Explain the syntax for each case.

* Extending Classes: No, Java does not allow multiple inheritance for classes. A class cannot extend more than one parent class.
* Implementing Interfaces: Yes, a class can implement multiple interfaces. Multiple inheritance is applicable using interfaces.

* Syntax:
* Class Inheritance: ```class Child extends Parent```.
* Interface Implementation: ```class Fish implements Fly, Swim``` (Use a comma to separate multiple interfaces).

---

### Q5\. Explain the relationship between interfaces: can an interface extend another interface? If yes, what keyword is used?

* Relationship: Yes, an interface can extend from another interface.
* Keyword: An interface uses the keyword ```extends``` to inherit from another interface, whereas a class uses ```implements``` to use an interface.
---

### Q6\. Compare abstract classes and interfaces from the following three perspectives:

1. Design Intent (What it represents):
* Abstract Class: It represents an "IS-A" relationship. It is used when there is common behavior that all subclasses must share, providing a base for specialized versions of the class.
* Interface: It represents a "Has-A" or behavior capability. It is used when there is a set of behaviors that subclasses must support, but the specific implementation details vary significantly.

2. Multiple Inheritance Support:
* Abstract Class: A class can extend only one abstract class. Java does not support multiple inheritance with classes.
* Interface: A class can implement multiple interfaces.

3. Constructors and Member Variables:
* Abstract Class: Can have constructors, usually to initialize non-static fields. It can contain non-static, non-final variables.
* Interface: Cannot have constructors. All variables are implicitly ```public static final``` (constants).
---

### Q7\. Why does Java not allow multiple inheritance of classes but allows multiple implementation of interfaces? How does this design avoid the Diamond Problem?

* Why classes are restricted: Java avoids multiple inheritance with classes to prevent ambiguity issues known as the "Diamond Problem." If a class were to extend two parents that both defined a method with the same name (e.g., ```drive()```), the compiler would not know which parent's implementation to execute.

* Why interfaces are allowed: Interfaces define abstract methods (contracts) without implementation state. When a class implements multiple interfaces, the class itself provides the specific implementation, eliminating ambiguity about which logic to run. The class notes explicitly state that "Multiple and Hybrid inheritance are applicable using interface (implements) only".

---

### Q8\. Explain the difference between Aggregation and Composition. Focus on the difference in object lifetime management and provide one practical example for each.

1. Aggregation ("Has-A"):

* Lifetime: The lifetime of the owned object does not depend on the lifetime of the owner. If the owner is destroyed, the owned object still exists.

* Example: A ```Person``` has a ```Country```. In the code provided in the class notes, the ```Country``` object is created outside the ```Person``` class and passed in. If the ```Person``` object is destroyed, the ```Country``` object remains valid.

2. Composition ("Part-Of"):

* Lifetime: The lifetime of the owned object depends on the lifetime of the owner. If the owner is destroyed, the owned parts are also destroyed.

* Example: A ```Car``` has an ```Engine```. In the code provided in the class notes, the ```Engine``` is created using ```new Engine()``` inside the ```Car```'s constructor. When the ```Car``` object's lifetime ends, the ```Engine``` instance is also lost.

---

### Q9\. In design patterns and SOLID principles, there is an important principle: "Favor composition over inheritance." Explain why composition is generally more flexible than inheritance.

* Inheritance creates a tight coupling ("IS-A") between the parent and child classes. Composition ("Has-A" or "Part-Of") is a tool used to decouple systems.

* By using composition (and aggregation), you can change behavior dynamically at runtime by swapping the objects being composed, whereas inheritance hierarchies are fixed at compile time. The class notes emphasize that the core of Design Patterns/SOLID is to "Decouple".

---

### Q10\. Describe the three core characteristics of the Singleton design pattern. Why is the Static Inner Class implementation of Singleton thread-safe and efficient? How does this approach achieve lazy initialization?

* Three Core Characteristics:

1. A private static variable to hold the single instance.

2. A private constructor to prevent instantiation from outside the class.

3. A public static method (```getInstance```) to provide a global access point.

* Static Inner Class Implementation:

* Thread-Safe: It relies on the Java class loading mechanism. The JVM guarantees that a class is loaded only once, which naturally handles thread safety without needing explicit synchronization keywords.

* Lazy Initialization: The Singleton instance is held inside a static inner class (```SingletonHolder```). This inner class is not loaded into memory until the ```getInstance()``` method is called for the first time. This ensures the instance is created only when actually needed, rather than at the start of the program (Eager Load).
* How the code works: 
* Start Up: When the program starts, it loads the main ```Singleto```n class. However, it does not load the inner class ```SingletonHolder``` yet. This is "Lazy Loading".
* When you call ```getInstance()```, it references ```SingletonHolder.INSTANCE```.
* This reference forces the JVM to load ```SingletonHolder``` immediately. Because of the JVM's safety guarantee, even if multiple threads call ```getInstance()``` at the same time, the class loads only once.
* Result: The ```INSTANCE``` is created safely and singly.
* Why it is efficient: We could avoid using the ```synchronized``` keyword on the method. Synchronization can slow down the program. Since the JVM handles the locking internally during the class load, accessing the instance later is very fast.

---