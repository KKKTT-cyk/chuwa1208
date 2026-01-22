# Assignment5 Yichao Chen

### Q1 What is the primary reason Java 8 introduced default methods in interfaces? Explain how default methods help maintain backward compatibility when adding new methods to existing interfaces. Give an example of a JDK interface that uses default methods
Java 8 introduced default method in interface is because of avoiding not destroy current implementation this interface class, implementation interface must use all interface method except default.\
Default methods allow interfaces to provide a default implementation for newly added methods. As a result, existing implementation classes can continue to compile and run without modification, while still gaining access to the new functionality. New implementing classes may choose to override the default method if different behavior is needed.
### Q2 Compare default methods and static methods in interfaces. What are the key differences in (1) how they are called, (2) whether they can be overridden, and (3) their typical use cases?
1. static methods in interfaces will be called by classname like interfacename.static method, default method called by instances like obj.default method.
2. Default method can override, however static method cannot. Since static method belong the interface, you cannot override interface's method.
3. Default method use for a base attributed and can override. Static methods are commonly used to define utility or helper functions that are logically related to the interface but do not depend on instance state.
### Q3 What is a Functional Interface? What role does the @FunctionalInterface annotation play? Can a functional interface have multiple default methods? Explain with an example.
The Functional interface is an interface only have one abstract method. The `@FunctionalInterface` annotation is used to explicitly declare an interface as functional. Its main role is to provide compile-time checking: if the interface accidentally contains more than one abstract method, the compiler will report an error. Functional interface can have multiple default method but only one abstract method.
### Q4 Describe the four major categories of functional interfaces in java.util.function package. For each category (Consumer, Supplier, Predicate, Function), explain (1) its method signature, (2) what it represents, and (3) give one practical use case
(1) Consumer Interface: Consumer<T> void accept(T t). A consumer method represents an operation that takes an input and not returns result. `list forEach(x -> System.out.println(x));`\
(2) Supplier Interface: Supplier<T> T get(). A supplier method represents an operations that provide a value without input value. `Supplier<LocalDateTimer> timerSupplier = () -> LocalDateTime.show();`.\
(3) Predicate Interface: Predicate<T>, boolean test (T t), a predicate method represents an operations that provide a boolean-valued condition with an input. `steam.filter(x -> x > 10);`\
(4) Function Interface: Function<T, R>, R apply(T t), a function method represents an operations that take an input and produce a output. The input and output type can be different. `stream.map(x -> x * 2);`
### Q5 What is "effectively final"? Why does Java require variables used in lambda expressions to be final or efectively final? What happens if you try to modify a variable before or after it's used in a lambda?
Effectively final is after the variable is created not have any changes at further, even though it is not explicitly declared with the final word. Java require variables used in lambda expression to be final or effectively final to avoid ambiguity and keep consistence behaviors.\
If changed before or after lambda express. The variables will no longer be effectively final and will fail to compile.
### Q6 Explain the difference between the three types of method references: (1) `ClassName::staticMethod`, (2) `object::instanceMethod`, and (3) `ClassName::instanceMethod`. For type 3, explain why the first parameter becomes the calling object.
(1) it is references from a static method in a some class (2) it is reference from an instance of specific object (3) it is referenced from an instance method of an arbitrary object of a given class. Because no specific instance is bound when using ClassName::instanceMethod, Java must obtain the target object dynamically. The functional interface supplies this object as its first parameter, allowing the instance method to be invoked correctly.
### Q7 What is the difference between `Optional.of`() and `Optional.ofNullable()`? When should you use each one? What happens if you pass `null` to `Optional.of()`?
`Optional.of()` is create an instance of object, if insert value == null, will have NPE. `Optional.ofNullable()` is created an instance of object, accept the null. `Optional.ofNullable()` can be defined when value could be null. 
### Q8 Compare `orElse()` and `orElseGet()` methods in Optional. Which one is more efficient when the default value is expensive to compute? Explain with an example.
`orElse(T other)` always evaluates the default value, regardless of whether the Optional contains a value or not.

`orElseGet(Supplier<? extends T> supplier)` evaluates the default value lazily, meaning the supplier is only executed when the Optional is empty.
### Q9 Why is `Optional.get()` considered dangerous? What are the recommended alternatives? Explain the best practices for extracting values from Optional objects.
`Optional.get()` is considered dangerous because it throws NoSuchElementException if the Optional is empty. In real code, it’s easy to end up with an empty Optional (e.g., missing data, failed lookup), so calling `get()` without checking presence can cause runtime crashes and defeats the purpose of using Optional safely.\
Alternative way is use .orElse(defaultValue): use when default is cheap Or .orElseGet(() - defaultValue): use when default is expensive/lazy
