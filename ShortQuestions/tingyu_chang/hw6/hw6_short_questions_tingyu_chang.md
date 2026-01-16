### Q1\. 1. Write a thread-safe Singleton class
* The class notes explicitly recommend the "Double-Checked Locking" pattern to solve thread safety issues while maintaining performance.
* The Volatile Necessity: The `volatile` keyword is critical here. The notes explain that without it, the JVM might perform "instruction reordering" during object instantiation. This could lead to a scenario where another thread sees a non-null `instance` that is not fully initialized.
* Double-Checked Logic: The code performs two checks. The first check avoids the expensive `synchronized` block if the object is already created, ensuring high performance. The second check, inside the synchronized block, ensures thread safety.

```
public class Singleton {
// volatile ensures visibility and prevents instruction reordering 
private static volatile Singleton instance;

    // Private constructor prevents instantiation from outside 
    private Singleton() {}

    public static Singleton getInstance() {
        // First check: avoids locking overhead if instance already exists 
        if (instance == null) {
            // Locking the class to ensure only one thread enters 
            synchronized (Singleton.class) {
                // Second check: ensures only the first thread to enter creates the instance
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance; 
    }
}
```

---

### Q2\. How to create a new thread (Including ThreadPool approach)?
* The class notes detail the standard methods and place significant emphasis on the Thread Pool approach for production environments.
* Extending Thread: This creates a direct dependency. The notes clarify that the JVM does not create the actual thread when `new MyThread()` is called, but only when `t.start()` is invoked.
* Implementing Runnable: This is preferred for flexibility. The notes show that `Runnable` is a `@FunctionalInterface`, allowing the use of Lambda expressions.
* Thread Pool (ExecutorService): The notes highlight that creating a single thread for every task consumes excessive resources. Instead, using an `ExecutorService` allows for thread reuse and task queuing.

* The notes list specific types of thread pools available in `Executors` :
1. `FixedThreadPool`: A pool with a fixed number of threads.
2. `CachedThreadPool`: A pool that adjusts the number of threads dynamically based on tasks.
3. `SingleThreadExecutor`: A pool with only one thread executing tasks sequentially.

---

### Q3\. Difference between Runnable and Callable?
* The elaboration in the notes connects `Callable` directly to the concept of `Future` for asynchronous result retrieval.
* Return Values: `Runnable` is `void`, meaning it cannot return a result. `Callable` returns a generic type `V`.
* Exception Handling: `Runnable` cannot throw checked exceptions, while `Callable` is designed to throw `Exception`.
* Retrieving Results: The notes explain that when you submit a `Callable` to a thread pool, you receive a `Future` object. You can then call `future.get()` to retrieve the result, though this method will block the current thread until the task completes.
```
// Example from class notes showing Callable usage 
class Task implements Callable<String> {
    public String call() throws Exception {
        return "Result";
    }
}
```
---

### Q4\. What is the difference between t.start() and t.run()?
* The class notes provide a clear distinction based on thread execution context.
* t.start(): This method instructs the JVM to create a new thread stack and execute the `run()` method within that new context. It transitions the thread state from `NEW` to `RUNNABLE`.
* t.run(): This method simply executes the `run()` code in the current thread (e.g., the main thread). No new thread is started, and it behaves like a standard synchronous method call.

---

### Q5\. Which way of creating threads is better: Thread class or Runnable interface?
* The class notes strongly advocate for separating the task from the execution mechanism, favoring `Runnable` and Thread Pools over extending `Thread`.
* Resource Management: The comparison table in the notes highlights that manually creating threads (`new Thread`) requires manual lifecycle management and makes concurrency control difficult.
* Composition over Inheritance: Implementing `Runnable` avoids the single inheritance limit of Java.
* Thread Pool Integration: The notes explain that Thread Pools (`ExecutorService`) accept `Runnable` or `Callable` tasks. This allows the pool to manage the lifecycle, queue tasks, and handle errors, which is significantly more efficient than managing individual `Thread` objects.

```
import java.util.concurrent.*;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // 1. Creation: Create a Fixed Thread Pool with 4 worker threads
        // The pool manages these 4 threads internally.
        ExecutorService executor = Executors.newFixedThreadPool(4); 

        // 2. Integration with Runnable (Fire-and-forget)
        // We submit a task that does not return a value.
        executor.submit(() -> {
            System.out.println("Runnable task executed by: " + Thread.currentThread().getName());
        }); 

        // 3. Integration with Callable (Asynchronous result)
        // We submit a task that returns a value. The pool handles execution and returns a Future.
        Callable<String> callableTask = () -> {
            Thread.sleep(1000); // Simulate work
            return "Callable Result";
        };

        Future<String> future = executor.submit(callableTask); 

        // 4. Retrieve Result
        try {
            // .get() blocks until the result is ready
            System.out.println("Result from pool: " + future.get()); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 5. Lifecycle Management: Shutdown
        // Gracefully closes the pool after tasks are finished.
        executor.shutdown(); 
    }
}

```

---

### Q6\. What are the thread statuses?
* The lifecycle of a Java thread consists of several distinct states:
* NEW: The thread object is created but `start()` has not been called yet.
* RUNNABLE: The thread is executing in the JVM. This state encompasses both "Ready" (waiting for CPU time) and "Running" (executing on the CPU).
* BLOCKED: The thread is waiting to acquire a monitor lock to enter a synchronized block or method.
* WAITING: The thread is waiting indefinitely for another thread to perform a specific action, such as `notify()` or `join()`.
* TIMED_WAITING: The thread is waiting for another thread for up to a specified waiting time, often invoked by `sleep(long)` or `join(long)`.
* TERMINATED: The thread has completed its execution.

---

### Q7\. Demonstrate deadlock and how to resolve it in Java code.
* A deadlock occurs when two threads hold resources that the other needs and wait indefinitely for each other to release them. The notes illustrate this with "Thread X" holding "Resource A" and waiting for "Resource B," while "Thread Y" holds "Resource B" and waits for "Resource A".
* Deadlock Code Example:
```
// Thread 1 locks A then tries to lock B
synchronized(resourceA) {
    Thread.sleep(100);
    synchronized(resourceB) { ... }
}

// Thread 2 locks B then tries to lock A
synchronized(resourceB) {
    Thread.sleep(100);
    synchronized(resourceA) { ... }
}

```
* Resolution: To resolve or prevent deadlock, the notes suggest utilizing `ReentrantLock`. Specifically, the `tryLock()` method allows a thread to attempt to acquire a lock only for a specific duration. 
* If it fails, it can back off and retry, breaking the cycle of indefinite waiting.

```
Lock lock1 = new ReentrantLock();
Lock lock2 = new ReentrantLock();

// Resolution using tryLock
if (lock1.tryLock(1, TimeUnit.SECONDS)) { 
    try {
        if (lock2.tryLock(1, TimeUnit.SECONDS)) {
            try {
                // Critical section
            } finally {
                lock2.unlock();
            }
        }
    } finally {
        lock1.unlock(); 
    }
}

```
---

### Q8\.How do threads communicate each other?
* Threads coordinate their actions using specific methods tailored to the locking mechanism being used.
* Synchronized Blocks: Threads use `wait()`, `notify()`, and `notifyAll()` to pause execution and wake up other threads sharing the same monitor lock.
* ReentrantLock: Threads use the `Condition` interface, which provides `await()`, `signal()`, and `signalAll()` methods for similar coordination.

---

### Q9\. What’s the difference between class lock and object lock?
* Object Lock: This uses `synchronized(this)`. It locks a specific instance of the class. Only threads attempting to access synchronized blocks of that specific object are blocked.
* Class Lock: This uses `synchronized(ClassName.class)` or a `static synchronized` method. It locks the class definition itself. This prevents any thread from entering synchronized static methods or blocks across all instances of that class.

---

### Q10\. What is join() method?
* The `join()` method allows one thread to pause its execution until another thread completes its task. 
* When the main thread calls `t.join()`, it enters a `BLOCKED` or `TIMED_WAITING` state and waits for thread `t` to terminate before continuing.

---

### Q11\. What is yield() method?
* The `yield()` method signals to the thread scheduler that the current thread is willing to relinquish its current use of the processor. 
* It transitions the thread from the "Running" state back to the "Ready" (or Runnable) state, allowing other threads of the same priority to execute.

---

### Q12\. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
* ThreadPool: A pool manages a set of reusable threads to execute tasks, reducing the overhead of thread creation.

* Types: The `Executors` factory class provides common types:

* `FixedThreadPool`: Maintains a fixed number of threads.

* `CachedThreadPool`: Creates threads as needed and reuses them.

* `SingleThreadExecutor`: Uses a single worker thread to execute tasks sequentially.

* TaskQueue: This is a buffer that holds tasks submitted to the pool that are waiting for an available thread to execute them.


---

### Q13\. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?
* Library: The `java.util.concurrent` package is used. Specifically, the `Executor`s class acts as a factory to create thread pools.

* Interface: The `ExecutorService` interface defines the main functions for managing the lifecycle of the pool and executing tasks.

---

### Q14\. How to submit a task to ThreadPool?
* Tasks are submitted using the methods provided by the `ExecutorService`.

* `submit()`: Accepts a `Runnable` or `Callable` task and returns a `Future` object representing the pending result.

* `execute()`: Accepts a `Runnable` task for execution without returning a result.

---

### Q15\. What is the advantage of ThreadPool?
* Performance: It reuses existing threads, avoiding the significant cost of frequent thread creation and destruction.

* Resource Management: It controls the number of concurrent threads, preventing resource exhaustion.

* Convenience: It automatically manages the thread lifecycle and provides queues for pending tasks.

---

### Q16\. Difference between shutdown() and shutdownNow() methods of executor
* The class notes mention both methods in the context of closing a thread pool:
* `shutdown()`: Used to close the thread pool. The notes list this as the standard way to close a pool.
* `shutdownNow()`: Also listed as a method to close the thread pool.
* As the notes do not explicitly detail the behavioral difference beyond listing them as closing methods: 
* Typically, `shutdown()` allows previously submitted tasks to execute before terminating, while `shutdownNow()` attempts to stop all actively executing tasks and halts the processing of waiting tasks.

---

### Q17\. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic classes and its main methods. when to use it?
* Definition: Atomic classes provide a way to implement lock-free thread safety. They execute "atomic operations," which are indivisible and safe to use without `synchronized` keywords.
* Usage: They are suitable for scenarios like counters and accumulators.
* Types: There are three common atomic data types:
1. `AtomicInteger`
2. `AtomicLong`
3. `AtomicBoolean`
* Code Example (`AtomicInteger`):
```
public class AtomicDemo {
    // 1. Initialize AtomicInteger
    private static AtomicInteger atomicInteger = new AtomicInteger(1); 

    public static void main(String[] args) {
        // 2. Increment and get the value atomically
        System.out.println(atomicInteger.getAndIncrement()); // Equivalent to count++ 
        
        // 3. Get the current value
        System.out.println(atomicInteger.get()); // Equivalent to reading count
    }
}

```
* Main Methods of `AtomicInteger`:
1. `addAndGet(int delta)`: Adds a value to the current value and returns the new result.

2. `incrementAndGet()`: Increments the value by 1 and returns the new value (like `++i`).

3. `getAndSet(int newValue)`: Updates to a new value and returns the old value.

4. `getAndIncrement()`: Increments the value by 1 and returns the old value (like `i++`).

---

### Q18\. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
* Concurrent collections are a set of collection classes provided by Java that are designed to be thread-safe.
* Standard collections (like `ArrayList`, `HashMap`) that we use daily are non-thread-safe.
* Concurrent collections allow multiple threads to access and modify data safely without needing manual synchronization, like adding `synchronized` keywords everywhere.

* Below are the thread-safe equivalents for common Java collection interfaces:
1. List: The thread-safe replacement for `ArrayList` is `CopyOnWriteArrayList`.
2. Map: The thread-safe replacement for `HashMap` is `ConcurrentHashMap`.
3. Set: The thread-safe replacements for `HashSet` or `TreeSet` is `CopyOnWriteArraySet`.
4. Queue: The thread-safe replacements for `ArrayDeque` or `LinkedList` are `ArrayBlockingQueue` or `LinkedBlockingQueue`.
5. Deque: The thread-safe replacement for `ArrayDeque` or `LinkedList` is `LinkedBlockingDeque`.

The class notes outline two main ways to instantiate these collections:
1. Direct Instantiation: Create the concurrent class directly.
* Example: `Map<String, String> map = new ConcurrentHashMap<>();`

2. Wrapper Utility: Use the `Collections` class to wrap a standard non-thread-safe collection.
* Example: `Map threadSafeMap = Collections.synchronizedMap(unsafeMap);`

---

### Q19\. What kind of locks do you know? What is the advantage of each lock?
* The class notes detail several types of locks beyond the standard `synchronized` keyword.
1. ReentrantLock:
* Description: A lock provided by the Java code (`java.util.concurrent.locks`) rather than the language syntax. It allows a thread to acquire the same lock multiple times.
* Advantage: Unlike `synchronized` which waits indefinitely, `ReentrantLock` allows for "try lock" mechanisms, attempting to acquire a lock only for a specific time. It also requires manual unlocking in a `finally` block.
2. ReadWriteLock:
* Description: Separates access into a "read lock" and a "write lock".
* Advantage: It improves performance in scenarios with many readers and few writers ("read-heavy"). It allows multiple threads to read simultaneously but only one thread to write (exclusive access).
3. StampedLock:
* Description: An optimistic locking mechanism.
* Advantage: It allows reading to occur even while a write might be happening (optimistic read). You obtain a "stamp" and validate it later to see if a write occurred during your read. This solves contention issues found in other locks.

---

### Q20\. What is Future and CompletableFuture? List some main methods.
* Future:
* Definition: Represents a result of an asynchronous computation that will be available in the future.
* Limitation: To get the result, you must use `get()`, which blocks the current thread (waits) until the result is ready.
* CompletableFuture:
* Definition: Introduced in Java 8, it implements `Future` but adds powerful asynchronous, non-blocking capabilities. It allows you to chain operations and handle callbacks automatically when a task completes.
* Main Methods of `Future`:
* `get()`: Retrieves the result, blocking if necessary until the computation has completed.
* `get(long timeout, TimeUnit unit)`: Retrieves the result, but waits at most the specified time.
* `cancel(boolean mayInterruptIfRunning)`: Attempts to cancel the execution of the task.
* `isDone()`: Returns `true` if the task completed.

* Main Methods of `CompletableFuture`:
Creation:
`supplyAsync(Supplier<U>)`: Executes a task asynchronously and returns a result.
`runAsync(Runnable)`: Executes a task asynchronously without returning a value.
Transformation:
`thenApply(Function)`: Processes the result and transforms it into another type.
`thenAccept(Consumer)`: Consumes the result without returning a new `CompletableFuture`
Combination:
`thenCombine()`: Combines results from two different `CompletableFutures`.
`allOf()`: Used to parallelize multiple `CompletableFutures`.
Exception Handling:
`exceptionally()`: Handles exceptions and returns a default value.

---

### Q21\. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)

---
