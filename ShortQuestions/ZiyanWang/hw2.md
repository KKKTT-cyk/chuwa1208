1. What is difference between a class and an object? Example
- Class is a blueprint or template that define properties and behaviors, not occupy runtime memory
- Object is an instance of a class created at runtime.
- Example: "Vehicle"class -> "Car" object

2. If you create a parameterized constructor in a class, what happens to the default constructor? What must you do if you still need it?
- Java does not automatically provide a default (no-argument) constructor.
- If you still need the default constructor, you must explicitly define it in the class.

3. What are the four access modifiers in Java? List them from most restrictive to least restrictive.
- private
- default
- protected
- public

4. Explain the difference between method overloading and method overriding.
- Overloading: 1. occurs when multiple methods in the same class have the same name but different parameter lists. 2.It is resolved at compile time.
- Overriding: 1. occurs when a subclass provides a specific implementation of a method defined in its superclass 2. It is resolved at runtime. 3. happen in inheritance 4. demonstrate dynamic polymorphism.

5. Can you override a final method? Can you override a private method? Explain why or why not.
- No. A final method cannot be overridden because it is explicitly prevented from being modified in subclasses.
- No. A private method cannot be inherited by subclass.

6. What is the difference between static polymorphism and dynamic polymorphism? When does each occur?
- static polymorphism: occurs at compile time and is achieved through method overloading.
- dynamic polymorphism: occurs at runtime through method overriding in an inheritance relationship, where the method call is resolved using dynamic binding based on the actual object type.

7. Why does Java not support multiple inheritance with classes? How can you achieve multiple inheritance in Java?
- multiple inheritance will occur Diamond problem, cause ambiguity and conflict.
- How to achieve multiple inheritance in Java: Interfaces. Interfaces provide method declarations without implementation conflicts (or use default methods with explicit resolution).

8. Consider the following code:

   List<Integer> lst = new ArrayList<>();
   Which principle of OOP does this demonstrate? Explain.
- programming to an interface
- abstraction + polymorphism
- The reference type is an interface, while the actual object is an implementation, allowing the implementation to be changed without affecting the code that uses it.