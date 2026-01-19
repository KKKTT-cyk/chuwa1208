# Assignment4 Yichao Chen
### Q1. What are the three major categories of exceptions in Java's exception hierarchy? For each category, explain: (1) Whether it must be handled at compile-time, (2) Common examples, (3) Best practices for handling.
- Error\
(1) Not required and not recommended to handle at compile-time.\
(2) StackOverFlowError, OutOfMemoryError, JVM.\
(3) Let the JVM handle it, and focus on prevention.
- Checked exception\
(1) Can handle in compiler-time, and must fix it in compiler time.\
(2) IO Exception, SQL Exception.\
(3) try-catch and throws.
- Unchecked exception\
(1) cannot handle in compiler-time, since it happens in run-time.\
(2) NPE, OutOfIndex Exception.\
(3) Fix the rule cause, programming problems. 
### Q2. Explain the execution order of try-catch-finally blocks. If both the catch block and finally block contain return statements, which value will be returned? Why is it strongly discouraged to use return statements in finally blocks?
There are three situations: 1. None Exception: try -> finally; 2. Exception are caught: try -> catch -> finally; 3. Exceptions aren't caught: try -> finally -> exception propagates.\
finally blocks will run at the value, except JVM terminated before finally block\
The finally block will run after try or catch, and will override previous return. That's reason why not discourage finally have return.
### Q3. What is the "catch scope should be from small to large" rule? Why must specific exception types (like OrderNotFoundException) be caught before general ones (like Exception)? What happens if you violate this rule?
This sentence is meaning catch child class to parent class. Specific exceptions must be caught before general exception, since if general exception caught first will handle exception before specific catch block.\
If violate the rules will miss some potential exception.
### Q4. Compare throw and throws: (1) Where is each used in code? (2) What follows each keyword? (3) Provide one practical example demonstrating both keywords working together in a DAO-Service-Controller architecture.
(1) `throw` is a keyword used inside the method body to actively throw a specific exception object during program execution; while `throws` is written in the method declaration (method signature), and its purpose is not to throw an exception, but to declare which exceptions the method might throw, delegating the responsibility of handling them to the caller.
(2) throw follows should be already created exception object, like new xxxException(). throws follows are exception class and can have multiple exception class.
```java
// Custom an exception
public class UserNotFondException extends Exception{
    public UserNotFondException(String message){
        super(message);
    }
}
// DAO layer
public class UserDao{
    public String findUserById(int id) throws UserNotFondException{
        if (id != 1){
            throw new UserNotFondException("User is not found with " + id); 
        }
        return "Bob";
    }
}

// Service layer
public class UserService{
    private UserDao userdao = new UserDao();
    public String getUserName(int id) throws UserNotFondException{
        return userdao.findUserById(id);
    }
}
// Controller layer
public class UserController{
    private UserService userService = new UserService();
    public void handelRequest{
        try{
            String name = userService.getUserName(id);
            System.out.println("User name: " + name);
        }catch (UserNotFondException e){
            System.out.println("Error: " + e.getMessage());
        }
        
    }
}
```
### Q5. What is try-with-resources syntax (introduced in Java 7)? What interface must a class implement to be used with try-with-resources? Explain the execution order when multiple resources are declared.
try-with-resources syntax is try(xxx){use resource}. try-with-resource implementation interface need have `java.lang.AutoCloseable`. The execution order is from resource left to right and declare(close) order is from right to left (LIFO).  
### Q6. When creating custom exceptions, how do you decide between extending Exception vs extending RuntimeException? Provide criteria for each choice and one example scenario for each.
If extends Exception scenario it is Checked Exception which are predictable and force compiler try and catch. If extend RuntimeException, the error happens in coding logic and not force to try-catch. It is need be fixed the programing logic.
```java
// Exception example
public class UserNotFondException extends Exception{
    public UserNotFondException(String message){
        super(message);
    }
}
// Unchecked Exception runtime
public class InvalidOrderStateException extends RuntimeException{
    public InvalidOrderStateException(String message){
        super(message);
    }
}
```
### Q7. Explain the two important features of Enum: "Every element is in values" and "Every element is a constructor". How would you implement an Enum with a private constructor that accepts parameters?
In Enum, every element are get a value() and the order enum is confirmed, enum support iteration. Each enum constant is essentially an instance of the enum class, an object created by the constructor during class loading.
In Enum, the constructor must be private. You cannot to new an instance to get parameters.
### Q8. Describe the popular Enum template pattern (Interface + Enum + Exception). What are its four components? How does using an interface type (IErrorCode) allow the exception class to accept multiple different enum types?
Enum template pattern is for Unified error codes and exception handling system.\
The first part is the Interface (e.g., IErrorCode), which defines "what information an error code object must provide," such as getCode() / getMessage().
This establishes a contract: any enum that implements it becomes a valid "error code type."

The second part consists of multiple Enums (e.g., UserErrorCode, OrderErrorCode), each implementing IErrorCode and storing the code/message using a private constructor.
Each enum constant is an instance carrying structured error information.

The third part is the business exception class (e.g., BizException), which internally stores an IErrorCode or the code/message, and provides a constructor.
Key point: the constructor parameter type is the interface, not a specific enum.

The fourth part is the usage layer/factory or throw point (throw site/helper), such as in a Service/Controller where you might `throw new BizException(UserErrorCode.USER_NOT_FOUND)`, or an ErrorCodeUtil for unified conversion and logging.
In practice, this is the layer that ensures consistency within the team regarding "how and what to throw."
### Q9. Compare the three major Collection interfaces: List, Set, and Queue. For each, explain: (1) Ordering characteristics, (2) Duplicate element handling, (3) Most commonly used implementation class, (4) One typical use case.
List: The insertion order is preserved by index it can have duplicate item. ArrayList is a most common example.
Set: Set not allow duplicated. HashSet is not guarantee any order, LinkHashSet preserve the insertion order, TreeSet can maintain element in order sorted.
Queue: Queue allow duplicate in most cases, and focus on process an order in specific way. LinkList is a common case. 
### Q10. Explain the difference between HashMap and Hashtable. Why is Hashtable considered obsolete? What are the modern alternatives for thread-safe Map implementations?
`HashMap` and `Hashtable` are both implementations of the Map interface, but they differ significantly in design philosophy and modern usage.
`HashMap` is not thread-safe, meaning multiple threads can access it concurrently without synchronization, which may lead to inconsistent data if not handled properly; in contrast, Hashtable is thread-safe by default, because all of its public methods are synchronized.
`Hashtable` is obsolete because of it synchronize in an inefficient way, modern code have lock, and `Hashtable` is lacks flexibility