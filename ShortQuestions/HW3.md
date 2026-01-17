# Assignment 3 Yichao Chen

### Q1. What is an abstract class? Why cannot an abstract class be instantiated directly? Can an abstract class have a constructor? If yes, what access modifier is typically used for it?
Abstract class is an incomplete class, and it cannot be instantiated directly. It must be extended by other classes. It can have abstract methods and non-abstract methods (concrete methods).\
Abstract class cannot be instantiated directly, because it has abstract methods that do not have implementation.

It can have a constructor, and the access modifier is typically used for it is protected. Subclasses can inherit the constructor. And parent class constructor is called in the subclass before the constructor of the subclass.
### Q2. What types of methods and member variables can an abstract class contain? List at least 4 types. Is it correct to say "an abstract class can only contain abstract methods"? Explain.
An abstract class can have abstract methods, static methods and variables, concrete methods and variables, constructor.

No, abstract class can have non-abstract methods which can be implemented and inherited or overridden by subclasses.
### Q3. What are the default modifiers for methods and variables in an interface? Why can't an interface have a constructor?
Default modifiers for methods in an interface are public and abstract. and default modifiers for variables in an interface are public, static and final.

Since interface is cannot be instantiated, and constructor are used to initialize instance state, but interface do not have instance variables and only made for implement.
### Q4. Can a class extend multiple classes in Java? Can a class implement multiple interfaces? Explain the syntax for each case.
A class cannot extend multiple class in jave, however a class can implement multiple interfaces.
`class xxx extends parent{}` , `class xxx extends parent implements interfaceA, interfaceB {},` `class xxx implements interface{}`, `class xxx implements interfaceA, interfaceB{}`.

### Q5. Explain the relationship between interfaces: can an interface extend another interface? If yes, what keyword is used? Can an interface extend multiple interfaces?
Yes, interface can extend another interface and multiple interface. `interfaceB extends A`. `interfaceC extends A, B{}`.
### Q6. Compare abstract classes and interfaces from the following three perspectives: (1) Design intent (what it represents), (2) Multiple inheritance support, (3) Constructors and member variables.
(1) Represents an "is-a" relationship with shared state and behavior. Abstract class are design for override add some new features base on some parents already exists features. Interfaces represents a capability or contract. It defines what a class can do, without specifying how it does it.\
(2) Abstract class can only extend one parent class. Interface can inherit and implement multiple interface.\
(3) Abstract class must have at least one abstract method and have non-abstract method, it can have static, instance variables and constructors. Interface cannot have constructors, and it can have public static variables and public abstract method. 
### Q7. Why does Java not allow multiple inheritance of classes but allows multiple implementation of interfaces? How does this design avoid the Diamond Problem?
Not allow multiple inheritance of classes is because of avoiding ambiguity caused by conflicting method implementations and state, Diamond problem. Allow multiple interfaces are not caused ambiguity because of interfaces not contain state and not provide method implementation.

### Q8. Explain the difference between Aggregation and Composition. Focus on the difference in object lifetime management and provide one practical example for each.
Aggregation represents a weak has-a relationship where the contained object has an independent lifecycle, while composition represents a strong has-a relationship where the contained object’s lifecycle depends on the container.

An engine in a car is aggregation case. A room in a house is composition case.
### Q9. In design patterns and SOLID principles, there is an important principle: "Favor composition over inheritance." Explain why composition is generally more flexible than inheritance.
Composition is more flexible than inheritance. It enables behavior resue without tight coupling. Reducing the dependency on class hierarchies.
### Q10. Describe the three core characteristics of the Singleton design pattern. Why is the Static Inner Class implementation of Singleton thread-safe and efficient? How does this approach achieve lazy initialization?
(1) private constructor, preventing external class from create new instance.
(2) one instance of the class is allowed to exist during the project lifecycle
(3) Global access point, the instance is accessed through public static method.

Static inner class is thread-safe because of inner static class is not loaded until it is referenced. instance is only create once.

Lazy initialization is achieved because of static inner class is not loaded until the `getInstance()` method is called. 
