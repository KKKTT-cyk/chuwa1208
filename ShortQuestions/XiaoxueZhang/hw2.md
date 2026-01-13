Q1. What is the difference between a class and an object? Provide a real-world example.
Class is like a blueprint, object is instance.
For example, human can be a class, man and woman can be object of human.

Q2. If you create a parameterized constructor in a class, what happens to the default constructor? What must you do if you still need it?
If a parameterized constructor in a class is created, the default constructor won't be generated automatically. We must create it if we need.

Q3. What are the four access modifiers in Java? List them from most restrictive to least restrictive.
private
default
protected
public

Q4. Explain the difference between method overloading and method overriding.
method overloading is that two methods in one class can have the same name and different parameter lists.
method overriding is that the method in child class can rebuild the method from parent class.

Q5. Can you override a final method? Can you override a private method? Explain why or why not.
Final method cant override. Because final means immutable
private method cant override, because private methods are not visible to subclasses.


Q6. What is the difference between static polymorphism and dynamic polymorphism? When does each occur?
static polymorphism occur at compile time
dynamic polymorphism occur at run time

Q7. Why does Java not support multiple inheritance with classes? How can you achieve multiple inheritance in Java?
If a class could inherit from multiple classes, method ambiguity would arise.
interface

Q8. Consider the following code:
List<Integer> lst = new ArrayList<>();
Which principle of OOP does this demonstrate? Explain.

This code demonstrates abstraction by using the List interface to hide the implementation details of ArrayList. It also supports polymorphism, since different implementations of List can be assigned to the same reference and resolved at runtime.