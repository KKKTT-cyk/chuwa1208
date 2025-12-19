Q1. What is an abstract class? Why cannot an abstract class be instantiated directly? Can an abstract class have a constructor? If yes, what access modifier is typically used for it?
- An abstract class cannot be instantiated. It may contain: Abstract methods (no implementation), Concrete methods (with implementation), Fields and constructors
- It cannot be instantiated because an abstract class may contain abstract methods without implementations.
  Instantiating it would create an object with incomplete behavior, which is not allowed.
- Yes. An abstract class can have a constructor. Mostly use protected. (1. Prevents direct instantiation from outside. 2. Allows subclasses to call it via super())


Q2. What types of methods and member variables can an abstract class contain? List at least 4 types. Is it correct to say "an abstract class can only contain abstract methods"? Explain.
- An abstract class can have everything else as same as a normal Java class has i.e. constructor, static
  variables and methods, Final methods.
- Not correct. It can mix partial implementation + shared state to reuse code across subclasses.


Q3. What are the default modifiers for methods and variables in an interface? Why can't an interface have a constructor?
- Variables are implicitly public static final (constants) and methods are implicitly public and abstract.
- An interface cannot be instantiated, it has no instance state to initialize. Constructors exist to initialize object instances; interfaces define a contract, not an object.


Q4. Can a class extend multiple classes in Java? Can a class implement multiple interfaces? Explain the syntax for each case.
- A class cannot extend multiple classes. A class can extend only one class: class B extends A {}
- A class can implement multiple interfaces. class MyClass implements InterfaceA, InterfaceB, InterfaceC {}


Q5. Explain the relationship between interfaces: can an interface extend another interface? If yes, what keyword is used? Can an interface extend multiple interfaces?
- Yes. An interface can extend another interface using extends: interface B extends A { }
- Yes. It can extend multiple interfaces: interface C extends A, B { }


Q6. Compare abstract classes and interfaces from the following three perspectives: (1) Design intent (what it represents), (2) Multiple inheritance support, (3) Constructors and member variables.
- (1) Design intent
Abstract class: “is-a” base type with shared state + shared implementation (template/partial class).
Interface: a capability/contract (“can-do”), focuses on behavior guarantees.
- (2) Multiple inheritance
Abstract class: no (a class can extend only one abstract/concrete class).
Interface: yes (a class can implement many; an interface can extend many).
- (3) Constructors & member variables
Abstract class: can have constructors, instance fields, non-final fields.
Interface: no constructors, fields are public static final constants only (no instance fields).


Q7. Why does Java not allow multiple inheritance of classes but allows multiple implementation of interfaces? How does this design avoid the Diamond Problem?
- Multiple inheritance of classes causes ambiguity when two parents provide the same method implementation (Diamond Problem).
- How does it avoid: 1. concrete method in the class hierarchy overrides interface defaults. 2. If two interfaces provide the same default method, the implementing class must override and choose

Q8. Explain the difference between Aggregation and Composition. Focus on the difference in object lifetime management and provide one practical example for each.
- Aggregation (weak “has-a”)
- - Part can exist independently of the whole.
- - Lifetime is not owned by the container.
- - Example: Team has Players. Players can exist without the team.

- Composition (strong “has-a”)
- - Part’s lifetime is owned by the whole.
- - If the whole is destroyed, parts are typically destroyed/unreachable too.
- - Example: House has Rooms. Rooms conceptually don’t exist without the house.

Q9. In design patterns and SOLID principles, there is an important principle: "Favor composition over inheritance." Explain why composition is generally more flexible than inheritance.
- Less coupling
- Runtime swapping
- Avoids fragile base class
- Better for single-responsibility
- Composition builds systems from interchangeable parts; inheritance builds rigid hierarchies.

Q10. Describe the three core characteristics of the Singleton design pattern. Why is the Static Inner Class implementation of Singleton thread-safe and efficient? How does this approach achieve lazy initialization?
- 1. Single instance in the JVM 
- 2. Global access point (getInstance())
- 3. Controlled instantiation (private constructor prevents new)
- Why thread-safe: Class initialization in Java is guaranteed thread-safe by the JVM (classloader initialization lock). Holder is initialized once, even under concurrency.
- Why efficient: No synchronization on every call (unlike synchronized getInstance()). getInstance() is just returning a pre-initialized reference after first load.
- How lazy initialization works: Holder class is not loaded until getInstance() is called. So the instance is created only when first needed.