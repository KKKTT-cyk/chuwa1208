# Assignment6 Yichao Chen

### Q2 Write a thread-safe singleton class
```java
public final class Singleton {
    // Private constructor
    private Singleton();
    // Class Holder
    private static class Holder() {
        private static final Singleton INSTANCE = new Singleton();
    }
    // Get Instance method (lazy method)
    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}
```
### Q3 How to create a new thread(Please also consider Thread Pool approach)?
First method: extends
```java
class Mythread extends Thread {
    @Override
    public void run() {
        System.out.println("Running in " + Thread.currentThread().getName());
    }
}

public class Demo {
    public static void main(String[] args) {
        Thread t = new Mythead();
        t.start();
    }
}
```
Runnable:
```java
public class Demo implements Runnable {
    static void main() {
        Runnable task = () -> {
            System.out.println(" Running in " + Thread.currentThread().getName());
        };
        Thread t = new Thread(task);
        t.start();
    }
}
```
Callable:
```java

```
### Q4 Difference between Runnable and Callable?
Runnable have no return, and Callable have return and need check exception.
### Q5 What is the difference between t.start() and t.run()?
start() creates a new thread and asks the JVM to execute the run() method in a separate thread of execution. Each Thread object can call start() only once; calling it more than once will throw an IllegalThreadStateException. run() it means run current thread and can be used multiple times.
### Q6 Which way of creating threads is better: Thread class or Runnable interface?
`Runnable` separates the task (what to run) from the thread (how to run it), which makes the design more flexible and easier to maintain. A class can implement `Runnable` while still extending another class, but a class that extends Thread cannot extend any other class
### Q7 What are the thread statuses?
NEW: Create a new thread however it not be called `start()`.
RUNNABLE: The thread is ready to run or is currently running on the CPU. ("running" and "ready" both are included in RUNNABLE)
BLOCKING: The thread is waiting to acquire a monitor lock
WAITING: The thread is waiting indefinitely for another thread to perform an action
TIMED_WAITING: The thread is waiting for a specified amount of time.
TERMINATED: The thread is finished from the works.
### Q8 Demonstrate deadlock and how to resolve it in Java code.
There are two thread t1 and t2. In t1 get LOCK A and waiting for LOCK B. In t2 thread get LOCK B and waiting for LOCK A. This is a deadlock case. Each thread waiting for get each other lock. We should synchronize get locks order make sure get Lock A and release Lock A then get Lock B and release B.
### Q9 How do threads communicate each other?
Threads communicate through shared memory with proper synchronization, wait/notify mechanisms, or high-level concurrency utilities such as blocking queues and futures.
### Q10 What’s the difference between class lock and object lock?
Class lock will lock all instances in that class. Object lock will only lock that instance.
### Q11 What is join() method?
In Java join() method is pause current thread until other thread finishes its execution.
### Q12 What is yield() method?
In Java yield() method is a static method that suggests give up its CPU execution time and allowing other thread have same or higher priority run first.
### Q13 What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
ThreadPool manage thread numbers and balance the thread creation cost and running cost, and manage thread lifecycle and make sure no redundancy, manage waiting thread. The common threadpool are single thread executor, Fixed thread pool and Cached thread pool. TaskQueue is queue that store submitted task waiting for executed.
### Q14 Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
Thread pool is provided by java.util.concurrent library. The `Executor` interface and `ExecutorService` is main functions for thread pool
### Q15 How to submit a task to ThreadPool?
Using execute() no return value

```java
import java.util.concurrent.ExecutorService;

ExecutorService pool = Executors.newFixedThreadPool(2);

pool.execute(() -> {
    System.out.println("Task running")
});

pool.shutdown();
```
```java
ExecutorService pool = Executors.newFixedThreadPool(2);

// Runnable task
pool.submit(() -> {
    System.out.println("Runnable task");
});

// Callable task (with return value)
Future<Integer> future = pool.submit(() -> 100);
System.out.println(future.get());

pool.shutdown();
```
### Q16 What is the advantage of ThreadPool?
First, it reduces the overhead of thread creation and destruction by reusing a fixed number of threads, which significantly improves performance. Second, it controls the number of concurrent threads, preventing system overload and improving stability. Third, it manages the thread lifecycle automatically, simplifying code and reducing the risk of resource leaks. In addition, thread pools improve task scheduling and responsiveness by using a task queue to manage waiting tasks.
### Q17 Difference between shutdown() and shutdownNow() methods of executor
shutdown() method will close thread after it finished from task and Stops accepting new tasks. shutdownNow() will Stop accepting new tasks and interrupt the running thread. 
### Q18 What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
Atomic Basic: AtomicInteger, AtomicBoolean and AtomicLong. Atomic Array: AtomicIntegerArray and AtomicLongArray. Atomic Field Updaters.

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    private static AtomicInteger count = new AtomicInteger();
    
    public static void main(String[] args) {
        count.incrementAndGet();
        count.decrementAndGet();
        count.addAndGet(5);

        System.out.println(count.get());
    }
}
```
### Q19 What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
Concurrent collections are thread-safe, CopyOnWriteArrayList, ConcurrentHashSet and CopyOnWriteArraySet
### Q20 What kind of locks do you know? What is the advantage of each lock?
Intrinsic Lock, Read-Write Lock, Reentrant Lock and StampedLock. Read-Write Lock allow one thread can write others cannot write and read when no thread is writing other thread can read. A reentrant lock allows the same thread to acquire the same lock multiple times without deadlock. In Java, both synchronized and ReentrantLock are reentrant.
### Q21 What is future and completableFuture? List some main methods of CompletableFuture.
Future represents the result of an asynchronous computation. It allows you to check whether a task is finished, wait for that task or cancel the task.
CompletableFuture is an enhanced version of Java8. It supports non-blocking, asynchronous programming, and task chaining using callbacks. Main method are runAsync(Runnable) and supplyAsync(Supplier<T>).
### Q22 Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)
### Q23 Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
### Q24 Create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)

