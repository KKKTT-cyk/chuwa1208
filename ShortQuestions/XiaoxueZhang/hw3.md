Q1. What is an abstract class? Why cannot an abstract class be instantiated directly? Can an abstract class have a constructor? If yes, what access modifier is typically used for it?
An abstract class is a class that cannot be instantiated and may include abstract and concrete methods. 
It cannot be instantiated because it may have unimplemented abstract methods, making it incomplete. 
Yes, abstract classes can have constructors, and they are typically declared as protected so only subclasses can call them during object creation.

Q2. What types of methods and member variables can an abstract class contain? List at least 4 types. Is it correct to say "an abstract class can only contain abstract methods"? Explain.
An abstract class can have everything else as same as a normal Java class has i.e. constructor, static variables and methods .

Q3. What are the default modifiers for methods and variables in an interface? Why can't an interface have a constructor?
Methods in an interface are implicitly public abstract, and variables are implicitly public static final. 
An interface cannot have a constructor because interfaces cannot be instantiated, and they have no instance fields to initialize. They define behavior, not object creation logic.

Q4. Can a class extend multiple classes in Java? Can a class implement multiple interfaces? Explain the syntax for each case.
A class cant extend multiple classes.
A class can implement multiple interfaces.
class MyClass implements InterfaceA, InterfaceB, InterfaceC {
}

Q5. Explain the relationship between interfaces: can an interface extend another interface? If yes, what keyword is used? Can an interface extend multiple interfaces?
Yes, an interface can extend another interface using the extends keyword. 
Interfaces can also extend multiple interfaces at once, supporting multiple inheritance of behavior.

Q6. Compare abstract classes and interfaces from the following three perspectives: (1) Design intent (what it represents), (2) Multiple inheritance support, (3) Constructors and member variables.
(1) Ddesign intent: abstract class represents an incomplete base class or a “is-a” relationship with shared behavior.
interface represents a contract or a capability, defines what a class must do, not how. No expected shared state; focuses on behavior only.
(2) Class doesn't support multiple inheritance. 
A class can implement multiple interfaces.
An interface can extend multiple interfaces.
(3) Abstract class can have constructors or everything else as same as a normal Java class has.
Interfaces cannot have constructors and only allow public static final constants.

Q7. Why does Java not allow multiple inheritance of classes but allows multiple implementation of interfaces? How does this design avoid the Diamond Problem?
Java forbids multiple inheritance of classes to avoid complexity and ambiguity, especially the Diamond Problem, where a class could inherit conflicting implementations from two parent classes. However, Java allows multiple interface implementations because interfaces traditionally contain no state and no concrete method bodies, so there is no ambiguity. Even with Java 8 default methods, Java requires the implementing class to explicitly resolve any conflict, thus completely avoiding the Diamond Problem.

Q8. Explain the difference between Aggregation and Composition. Focus on the difference in object lifetime management and provide one practical example for each.
Aggregation represents a weak has-a relationship where the contained object can exist independently; destroying the container does not destroy the part. Composition represents a strong has-a relationship with full ownership; if the container object is destroyed, the contained objects cease to exist. For example, a Student aggregates a Course (course exists independently), while a Car composes an Engine (engine depends on the car’s lifetime).

Q9. In design patterns and SOLID principles, there is an important principle: "Favor composition over inheritance." Explain why composition is generally more flexible than inheritance.
Composition is more flexible than inheritance because it allows behavior to be assigned and changed at runtime, avoids tight coupling, prevents deep fragile inheritance hierarchies, and aligns better with SOLID principles—especially the Open/Closed and Single Responsibility principles. While inheritance locks you into a fixed relationship at compile time, composition lets you build objects by combining small, reusable components, allowing more adaptability and easier maintenance.

Q10. Describe the three core characteristics of the Singleton design pattern. Why is the Static Inner Class implementation of Singleton thread-safe and efficient? How does this approach achieve lazy initialization?
A Singleton must enforce three characteristics: only one instance exists, the instance is globally accessible, and the constructor is private to prevent external creation.
The Static Inner Class implementation is thread-safe because the JVM guarantees class initialization is atomic and synchronized. It is efficient because it avoids synchronization completely. 
It also achieves lazy initialization because the instance is only created when the static inner class is first accessed, not when the outer class is loaded.

