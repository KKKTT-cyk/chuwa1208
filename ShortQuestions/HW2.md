# Assignment 2 Yichao Chen

### Q1 What is the difference between a class and an object? Provide a real-world example.
A class is blueprint which defines the properties and behaviors of an object.\
An object is an instance of class. For example, Human is a class and a Chinese people is an instance of the Human class.

### Q2 If you create a parameterized constructor in a class, what happens to the default constructor? What must you do if you still need it?
If there is a parameterized constructor in a class, the default constructor no longer provided automatically by java.\
It need manual add a default constructor(must explicitly define it in the class).

### Q3 What are the four access modifiers in Java? List them from most restrictive to least restrictive.
private -> default -> protected -> public

### Q4 Explain the difference between method overloading and method overriding.
Overloading is static, it happens in compiler time. Method overloading occurs when multiple methods in the same class and must have same method name\
different arguments (parameter lists) and return type is not requested to same.\
Overriding is dynamic, it happens in run time. It occurs when a subclass provided a specific implementation of parents already defined.

### Q5 Can you override a final method? Can you override a private method? Explain why or why not.
No, when method add final, it can inherit but not override.\
A private method cannot be overridden because private methods are not accessible to subclasses. A method with the same name in the subclass is treated as a new method, not an override.

### Q6 What is the difference between static polymorphism and dynamic polymorphism? When does each occur?
Static polymorphism is achieved through method overloading and occurs in compiler time. Dynamic polymorphism achieved through method override occurs in run time.\
Static polymorphism must have same method name, different parameters list.\
Dynamic polymorphism occurs a subclass provides it own implementation of a method defined in its parent class.

### Q7 Why does Java not support multiple inheritance with classes? How can you achieve multiple inheritance in Java?
Java does not support multiple inheritance with classes to avoid ambiguity and complexity, especially the diamond problem, where a class inherits the same method from multiple parent classes.

Java achieves multiple inheritance through interfaces, because interfaces do not provide implementation conflicts (before Java 8) and allow a class to implement multiple interfaces.

### Q8 Consider the following code: ```List<Integer> lst = new ArrayList<>();``` which principle of OOP does this demonstrate? Explain.
It belongs to polymorphism.\
List is an interface, and ArrayList is a class that implements the List interface. A reference of the parent interface type is used to refer to an object of a child implementation class.




