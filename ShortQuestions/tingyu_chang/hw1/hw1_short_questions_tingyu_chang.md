### 1\. What is the difference between JDK, JRE, and JVM?

*   JVM (Java Virtual Machine): It is the core component included in both the JDK and JRE.

*   JRE (Java Runtime Environment): It is the implementation of the JVM used to run applications. It contains the JVM, a set of libraries (e.g., rt.jar), and other supporting files.

*   JDK (Java Development Kit): It is a superset that contains a private JVM and resources to complete the development of a Java Application. It includes development tools such as the compiler (javac), interpreter/loader (java), archiver (jar), and documentation generator (Javadoc).

---

### 2\. What are the main differences between primitive types and reference types?
*   Primitive Types: These store "raw" data values and include 8 specific types: byte, short, int, long, float, double, char, and boolean. They have specific memory sizes, such as int (4 bytes), long (8 bytes), double (8 bytes), and boolean (1 bit). They can be compared using ```==```.
*   Reference Types: These include classes, interfaces, and arrays. Essentially, they function similarly to pointers in C language, storing the address of the object rather than the object itself. They can be compared using ```==``` to check if they point to the same object, or using ```equals()``` to check if they have the same content.

---

### 3\. Is Java pass-by-value or pass-by-reference? Explain.
*   Java is strictly pass-by-value.
*   Pass-by-value definition: It makes a copy in memory of the parameter's value, or a copy of the contents of the parameter.
*   Pass-by-reference definition: A copy of the address (or reference) to the parameter is stored rather than the value itself. In Java, when you pass a variable, you are passing a copy of the content. For primitives, this is the actual data; for objects, this is the reference (address) to the object.

---

### 4\. Why are Strings immutable in Java?
*   Strings are immutable because they cannot be changed once they are created. Strings are immutable to support mechanisms like the String Constant Pool. This immutability ensures that if multiple references point to the same literal "Cat", changing one would not affect the others, which is critical for security and consistency. It also allows the class to be final.
---

### 5\. What is the String Constant Pool and how does it work?
* The String Constant Pool is a memory area in the Java Heap.

* When you create a string literal like ```String s1 = "Cat";```, it is stored in the pool.

* If you create another literal ```String s2 = "Cat";```, Java reuses the existing object from the pool, making ```s1 == s2``` true.

* However, if you use ```new String("Cat")```(e.g., ```String s3 = new String("Cat");```), it creates a distinct object in the heap, not the pool, so ```s1 == s3``` is false.

---
### 6\. What is the purpose of the final keyword in Java?
* The ```final``` keyword is used to restrict the user from changing definitions.

* Variable: Defines constants (e.g., ```public static final String APP_NAME="testApp"```), which means the value cannot be changed once assigned.

* Method: Prevents the method from being overridden by subclasses.

* Class: Prevents inheritance (cannot be subclassed). Examples include ```Integer``` and ```String```.

---

### 7\. What does the static keyword mean for variables or methods?

* Static Variable: There is only one instance of the variable shared across the class. It is used for shared data.
* It is used for shared data, such as the ```employees``` set in ```EmployeeRepository``` which holds data common to the whole application.

* Static Method: It can be called directly using the class name (e.g., ```EmployeeData.getEmployees()```) without creating an object. Utility class methods are often designed as static (e.g., ```Integer```, ```String```, ```Math```, ```System```).
* Code Example: In this utility class, ```getEmployees``` is defined as static. This means it belongs to the class ```EmployeeData``` itself, not to a specific object.

```
public class EmployeeData {
    // defined as 'public static'
    public static List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1001, "Yun Ma", 50, 30000.00));
        list.add(new Employee(1002, "Huateng Ma", 49, 22222.22));
        // ... adds more employees
        return list;
    }
}
```
* Because the method is static, ```EmployeeRepository``` can call ```EmployeeData.getEmployees()``` directly inside its static block. There is no ```new EmployeeData()``` used here.
```
public class EmployeeRepository {
private static Set<Employee> employees = new HashSet<>();

    static {
        // Direct call using Class Name: EmployeeData
        employees.addAll(EmployeeData.getEmployees());
    }
}
```
---

### 8\. What is a static block and when does it run?
* A static block (e.g., ```static { ... }```) is used for initializing static variables. It runs once when the class is loaded by the JVM, before the constructor or any instance methods are executed.

---
### 9\. Can a static method access non-static variables? Why or why not?
* No, you cannot access non-static variables inside a static context.

* Static methods belong to the class and are loaded before any objects exist. Non-static variables belong to specific instances (objects).

* You can call static methods using the class name, but you cannot reference instance variables (like ```s2``` in the class notes) inside a static context directly.
* Code Example:

```
class Demo {
    // 1. Static variable (Belongs to class)
    private static int n1 = 0; 
    
    // 2. Non-static variable (Belongs to a specific object instance)
    private String s2 = "non static variable"; 

    // Static Block (Runs when class is loaded)
    static {
        System.out.println("******* 1, static block is called *****");
        
        // valid: Accessing static variable
        System.out.println(n1); 
        
        // invalid: Trying to access non-static variable s2 here causes a compile error
        // System.out.println(s2); // Error: Cannot make a static reference to the non-static field s2 
    }

    // Constructor (Runs when 'new Demo()' is called)
    public Demo() {
        System.out.println("******* 3, Constructor is called ******");
        
        // valid: Constructor is non-static, so it can access s2
        System.out.println("s2 have value now: " + s2); 
    }
}

```

---

### 10\. Describe the JVM loading order: static block, static variables, and constructor.
* According to the class notes example ```JvmLoad```:

* Static Variables & Blocks: These are executed first when the class is loaded. The notes show ```static block is called``` happening first.

* Constructor: This is executed strictly after the static initialization, when an object is created (```new Demo()```).
* Code Example: In this code, the static block prints first, and the constructor prints second when ```new Demo()``` is called.
```
public class Demo {
    // Static Block: Runs first when the class is loaded
    static {
        System.out.println("******* 1, static block is called *****");
    }

    // Constructor: Runs after static block, only when 'new Demo()' is called
    public Demo() {
        System.out.println("******* 3, Constructor is called ******");
    }
}

public static void main(String[] args) {
    // Trigger loading and object creation
    Demo demo = new Demo(); 
}
```
---

### 11\. What is the difference between a static variable and a constant defined as public static final?

* Static Variable: (e.g., ```private static int n1```) Its value can be changed (mutable) and defaults to 0 if not set.

* Constant (final): (e.g., ```private static final String s1```) Its purpose is to define constants. Its value is fixed and cannot be changed after initialization.
* Code Example:
```
class Demo {
    // 1. Static Variable
    // Mutable: Value can be changed later. 
    // Default value is 0 if not assigned.
    private static int n1; 

    // 2. Constant (Public Static Final)
    // Immutable: Value is fixed as "static variable".
    // Convention typically uses UPPER_CASE (e.g., APP_NAME).
    private static final String s1 = "static variable"; 
    // public static final String APP_NAME = "testApp"; 

    public static void changeValues() {
        n1 = 5;          // Valie: You can change a static variable
        // s1 = "new";   // Error: You cannot change a final variable
    }
}

```
---

### 12\. Explain why immutable objects (like String) are thread-safe by design. Provide an example scenario.
* Immutable objects are thread-safe because their state cannot be modified after creation.

* In the class notes regarding Singleton patterns, thread safety is a key concern in multi-threaded environments.

* Similarly, because an immutable ```String``` (or a ```final``` instance in a Singleton) cannot be changed, multiple threads can access it without the risk of one thread altering the data while another reads it.

* Example Scenario: Consider a global constant defined in the class notes: ```public static final String APP_NAME="testApp"```.

* Thread A reads ```APP_NAME``` to display it on a dashboard.

* Thread B simultaneously reads ```APP_NAME``` to write it to a log file.

* Because the String is immutable and ```final```, there is zero risk that Thread B could accidentally modify or corrupt the name while Thread A is using it. Both threads are guaranteed to see the exact same value.
---

### 13\. What problem does making a class final solve in terms of immutability and inheritance security?
* Making a class ```final``` (like ```String``` or ```Integer```) serves two main purposes:

* Prevent Inheritance: It ensures no other class can extend it.

* Immutability: By preventing subclasses, it guarantees the class remains immutable and its behavior cannot be modified or overridden by a child class.
