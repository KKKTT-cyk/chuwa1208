1. **What is the difference between JDK, JRE, and JVM?**  
   *Answer:*  
   - **JVM (Java Virtual Machine):** Runs Java bytecode and provides platform independence.  
   - **JRE (Java Runtime Environment):** Contains JVM + core libraries to run Java programs.  
   - **JDK (Java Development Kit):** Contains JRE + development tools (compiler, debugger, etc.).

2. **What are the main differences between primitive types and reference types?**  
   *Answer:*  
   - **Primitive types:** There are eight primitive types: `boolean, byte, short, int, long, float, double, char`. Stored in stack. Hold actual values.  
   - **Reference types:** Objects, arrays, Strings. Stored in heap. Hold references to objects.

3. **Is Java pass-by-value or pass-by-reference? Explain.**  
   *Answer:*  
   Java is **pass-by-value**. The value of a reference is passed, not the actual object. Modifying the object changes it, but reassigning the reference does not affect the original. The following code example illustrates it perfectly.
```
package basics;

public class LearnPassByValue {

    public static void modify(Person p) {
        System.out.println("Inside modify: p = " + System.identityHashCode(p));
        p.age = 30; // modify object
        p = new Person(123); // reassign reference
        System.out.println("Inside modify after p=new: p = " + System.identityHashCode(p));
    }

    public static void main(String[] args) {
        Person john = new Person(20);
        System.out.println("Before modify: john = " + System.identityHashCode(john));

        modify(john);

        System.out.println("After modify: john = " + System.identityHashCode(john));
        System.out.println("John's age: " + john.age);
    }
}


class Person {
    int age;
    Person(int age) {
        this.age = age;
    }
}

Output:

Before modify: john = 1523554304
Inside modify: p = 1523554304
Inside modify after p=new: p = 925858445
After modify: john = 1523554304
John's age: 30
```


4. **Why are Strings immutable in Java?**  
   *Answer:*  
   Strings cannot be changed after creation to ensure **thread-safety**, **security**, and efficient **String Pool reuse**.  
   

5. **What is the String Constant Pool and how does it work?**  
   *Answer:*  
   The **String Constant Pool** is a special memory area in the heap where Java stores literal strings. If a string already exists, Java reuses it instead of creating a new object. The following code example shows how string constant pool works.

```
package basics;

public class SCPExample {
    public static  void main(String[] args) {
        String s1 = "Hello";
        String s2 = "Hello"; // "Hello" exists in SCP
        System.out.println(s1 == s2); // s1 would equal s2 as they are the same!
        System.out.println(System.identityHashCode(s1)); 
        System.out.println(System.identityHashCode(s2)); // hash code proves s1 and s2 are the same
    }
}

Output:

true
1072591677
1072591677

```


6. **What is the purpose of the final keyword in Java?**  
   *Answer:*  
   - **final variable:** value cannot change.  
   - **final method:** cannot be overridden.  
   - **final class:** cannot be subclassed.


7. **What does the static keyword mean for variables or methods?**  
   *Answer:*  
   Static variables/methods belong to the class, not instances. Shared across all objects of the class. The following code example can prove it.

```

class Counter {
    // Static variable shared by all objects
    static int count = 0;

    // Static method to increment the count
    static void increment() {
        count++;
    }

    // Instance method
    void showCount() {
        System.out.println("Count: " + count);
    }
}

public class Main {
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();

        // Access static method using class name
        Counter.increment();
        Counter.increment();

        // Both objects see the same shared static variable
        c1.showCount(); // Output: Count: 2
        c2.showCount(); // Output: Count: 2

        // Increment again
        c2.increment();

        c1.showCount(); // Output: Count: 3
        c2.showCount(); // Output: Count: 3
    }
}
```


8. **What is a static block and when does it run?**  
   *Answer:*  
   A static block initializes static variables. It runs once when the class is loaded by JVM. Note that instance variables and constructor only run when an object of the class is created. 


9. **Can a static method access non-static variables? Why or why not?**  
   *Answer:*  
   No, static methods cannot access instance (non-static) variables because instance variables belong to objects, but static methods belong to the class itself.


10. **Describe the JVM loading order: static block, static variables, and constructor.**  
    *Answer:*  
    - Static variables initialized first (in order of declaration)  
    - Static blocks executed next  
    - Instance variables initialized when an object is created  
    - Constructor runs after instance variables are set

The following code provides an example.

```
class Demo {
    static int staticVar = initStaticVar(); // call function below (1)
    int instanceVar = initInstanceVar(); // call function below (4)

    static {
        System.out.println("Static block executed"); // 2
    } 

    {
        System.out.println("Instance initializer block executed"); // 5
    }

    Demo() {
        System.out.println("Constructor executed"); // 6
    }

    static int initStaticVar() {
        System.out.println("Static variable initialized"); // 1
        return 10;
    }

    int initInstanceVar() {
        System.out.println("Instance variable initialized"); // 4
        return 20;
    }

    public static void main(String[] args) {
        System.out.println("Main method starts"); // 3
        new Demo();
        new Demo();
    }
}

Output:

Static variable initialized
Static block executed
Main method starts
Instance variable initialized 
Instance initializer block executed
Constructor executed
Instance variable initialized
Instance initializer block executed
Constructor executed
```
Again, instance variables and constructor were initialized because we called new Demo().


11. **What is the difference between a static variable and a constant defined as public static final?**  
    *Answer:*  
    - **Static variable:** Shared across objects; value can change.  
    - **public static final:** Shared and immutable; behaves as a constant.


12. **Explain why immutable objects (like String) are thread-safe by design. Provide an example scenario.**  
    *Answer:*  
    Immutable objects cannot change state after creation. Multiple threads can read the same object without synchronization.  
    *Example:* Multiple threads reading the same `String` instance from a cache will never cause data corruption.


13. **What problem does making a class final solve in terms of immutability and inheritance security?**  
    *Answer:*  
    Making a class final prevents subclassing, ensuring **immutability** is preserved and critical methods cannot be overridden maliciously.
