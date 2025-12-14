**Q1. What is the difference between a class and an object? Provide a real-world example.**
*Answer:*
A class is a blue print, an abstract concept. An object is the concret substantiaton, or implemtation of that concept.

```
public class Student {
    private String name;
    private int age;
    private String gender;
    private String college;
    private int studentNumber;

    public Student (String name, int age, String gender, String college, int studentNumber) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.college = college;
        this.studentNumber = studentNumber;
    }
}

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student("John", 20, "male", "College of Engineering", 4);
        Student student2 = new Student("Jane", 21, "female", "College of Business", 5);
    }
}

```

**Q2. If you create a parameterized constructor in a class, what happens to the default constructor? What must you do if you still need it?**
*Answer:*
If you generated a parameterized constructor, the compile does not generate a default constructor for you. If you still need a default constructor, you must define one yourself.


**Q3. What are the four access modifiers in Java? List them from most restrictive to least restrictive.**
*Answer:*
private – accessible only within the class
default (no modifier) – accessible within the same package
protected – accessible within the same package and subclasses
public – accessible from anywhere


**Q4. Explain the difference between method overloading and method overriding.**
*Answer:*
Method overloading: Same method name but different parameters in the same class. Occurs at compile time (static polymorphism).

Method overriding: Child class provides a new implementation for a method defined in the parent class. Occurs at runtime (dynamic polymorphism).


**Q5. Can you override a final method? Can you override a private method? Explain why or why not.**
*Answer:*
A final method cannot be overriden, because it's explicitly marked to prevent modification.
A private method cannot be overriden, because it's hidden and inaccessible from outside the class.


**Q6. What is the difference between static polymorphism and dynamic polymorphism? When does each occur?**
*Answer:*
Static polymorphism is overloading. It happens at compile time;
Dynamic polymorphism is overriding. It happens at runtime.


**Q7. Why does Java not support multiple inheritance with classes? How can you achieve multiple inheritance in Java?**
*Answer:*
Java does not support multiple inheritance with classes to avoid ambiguity and complexity, such as the diamond problem, where a class could inherit the same method from multiple parent classes and the compiler would not know which one to use.

Java achieves multiple inheritance through interfaces. A class can implement multiple interfaces, and since interfaces do not provide conflicting implementation state (prior to default methods), this avoids ambiguity. If default methods exist, Java requires the implementing class to explicitly resolve conflicts.
```
interface A {
    void doSomething();
}

interface B {
    void doSomethingElse();
}

class C implements A, B {
    public void doSomething() { }
    public void doSomethingElse() { }
}
```

**Q8. Consider the following code. Which principle of OOP does this demonstrate? Explain.**
```
List<Integer> lst = new ArrayList<>();
```
*Answer:*
The example demonstrates the OOP principle of polymorphism, particularly interface polymorphism.
List is the interface, and ArrayList is a concret class that implements this interface. By declaring lst as List and not ArrayList, the code is programming to an interface, not an implementation. This allows users to switch implementations later without changing any code. For example, the following code would still work:
```
List<Integer> lst = new LinkedList<>(); 
```



