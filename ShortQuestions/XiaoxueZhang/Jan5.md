3. How to create a new thread(Please also consider Thread Pool approach)?
1) Using Thread + Runnable
2) Implement Runnable / extend Thread
3) Thread Pool

4. Difference between Runnable and Callable?
runnable doesnt return value, callable returns value

5. What is the difference between t.start() and t.run()?
t.start(): Starts a new OS-level thread, then that new thread calls run().
t.run(): Does NOT start a new thread.

6. Which way of creating threads is better: Thread class or Runnable interface?
Runnable is usually better.
Separates “task” from “thread”
Runnable = what to do, Thread = where/how it runs.
More flexible: Your class can still extend another class (Java only allows one parent class).
Works naturally with thread pools (ExecutorService)
Easier to test/reuse
A Runnable can be run in a thread, a pool, or even directly in unit tests.

7. What are the thread statuses?
NEW → created
RUNNABLE → executing/ready
BLOCKED → waiting for a monitor lock
WAITING → waiting forever for a signal
TIMED_WAITING → waiting with a timeout
TERMINATED → finished

8. Demonstrate deadlock and how to resolve it in Java code.

9. How do threads communicate each other?
synchronized
wait() / notify() / notifyAll(): Used when one thread must wait for a condition produced by another thread.
volatile: Used for simple signals, not compound operations.
Blocking queues
Locks and Conditions
Atomic variables
Futures / CompletableFuture
Thread pools

10. What’s the difference between class lock and object lock?
An object lock synchronizes on a specific instance, while a class lock synchronizes on the Class object itself, coordinating access across all instances.

11. What is join() method?
Thread.join() makes the current thread wait until another thread finishes.
Caller will wait

12. what is yield() method
Current thread moves from RUNNING → RUNNABLE
Scheduler may switch to another thread of the same priority
The yielding thread can be scheduled again immediately

13. What is ThreadPool? How many types of ThreadPool? What is the TaskQueue in ThreadPool?
A thread pool reuses a fixed or managed set of threads to execute tasks efficiently. Tasks are stored in a task queue when threads are busy, preventing uncontrolled thread creation.
Fixed Thread Pool
Cached Thread Pool
Single Thread Executor
Scheduled Thread Pool
ForkJoinPool (work-stealing)
 
14. Which Library is used to create ThreadPool? Which Interface provide main functions of thread-pool?


15. How to submit a task to ThreadPool?
16. What is the advantage of ThreadPool?
17. Difference between shutdown() and shutdownNow() methods of executor
18. What is Atomic classes? How many types of Atomic classes? Give me some code example of Atomic
classes and its main methods. when to use it?
19. What is the concurrent collections? Can you list some concurrent data structure (Thread-safe)
20. What kind of locks do you know? What is the advantage of each lock?
21. What is future and completableFuture? List some main methods of ComplertableFuture.
22. Type the code by your self and try to understand it. (package com.chuwa.tutorial.t08_multithreading)