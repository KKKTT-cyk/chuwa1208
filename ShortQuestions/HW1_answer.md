# Assignment 1 Yichao Chen #
### Q1 What is the difference between JDK, JRE, and JVM?
JDK including JRE and javac compiler, JRE including JVM and others basic library.

### Q2 What are the main differences between primitive types and
reference types?
primitive type:
byte, short, int, long, float, double, char, boolean. Eight\
All other types are reference types:
Reference type not directly store the actual value, instead they store a memory address\
points to the value.

### Q3 Is Java pass-by-value or pass-by-reference? Explain.
Java is pass by value. When a refence type is passed to a method, the value being passed is memory address of the object

### Q4 Why are Strings immutable in Java?
String is immutable, which can help inherently thread-safe

## Q5 What is the String Constant Pool and how does it work?
In the String Constant pool, string literals are store in String constant pool.
```java
String a = "Cat";
String b = "Cat";
```
These two are refer to the same String object in the String constant pool.
```java
String c = new String("Cat");
```
c create a new String object in the heap, even "Cat" already exists in the String constant pool.

### Q6 What is the purpose of the final keyword in Java?
The final word prevents modification, such as a final class cannot be extended, but it can still be instantiated\
a final method cannot overridden by subclass, but it can be inherited; a final variable cannot be reassigned after created.  

### Q7 What does the static keyword mean for variables or methods?
static keyword means that a variable and method belongs to the class rather than an to an instance of  object\
a final variable is shared by all objects of the class\
a final method can be called using the class name and can only access static members

### Q8 What is a static block and when does it run?
A static block executed when class is loaded.

### Q9 Can a static method access non-static variables? Why or why not?
A static method cannot access non-static variables. Because, static methods belong to the class, and non-static variables
belongs to instances.

### Q10 Describe the JVM loading order: static block, static variables, and constructor.
static variable -> static block -> constructor

### Q11 What is the difference between a static variable and a constant defined as public static final?
static variables belongs to the class and can be modified. public static final same belongs to class and cannot be modified\
sharing to all instances.

### Q12 Explain why immutable objects (like String) are thread-safe by design. Provide an example scenario.
String cannot be changed after initialized. Multiple threads can share same instances without synchronization

```java
String s = "Hello";

Runnable task = () -> {
    System.out.println(s);
};

new Thread(task).start();
new Thread(task).start();
```
in this example two threads are using same String object without synchronization.
```java
StringBuilder = new StringBuilder("Hello");
Runnable task = () -> {
    sb.append('i');
}
new Thread(task).start();
new Thread(task).start();
```
In this example, ```StringBuilder ``` is mutable, so the code is not thread-safe.

### Q13 What problem does making a class final solve in terms of immutability and inheritance security?

Making a class final prevents subclassing, ensuring that immutability and behavior cannot be compromised through inheritance.


