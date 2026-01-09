# Java Multithreading & Concurrency

---

## 1. Difference between `Runnable` and `Callable`

- **Runnable**
    - Does not return a result
    - Cannot throw checked exceptions
    - Introduced in Java 1.0
- **Callable**
    - Returns a result
    - Can throw checked exceptions
    - Introduced in Java 5 (concurrency utilities)

---

## 2. Difference between `t.start()` and `t.run()`

- `start()`
    - Creates a **new thread**
    - JVM schedules execution of `run()` in that new thread
- `run()`
    - Executes like a **normal method call**
    - Runs in the **current thread**, no new thread created

---

## 3. Thread vs Runnable: Which is better?

- **Runnable is preferred**
    - Supports multiple inheritance (Java allows only one superclass)
    - Separates **task** from **thread**
    - Works naturally with thread pools

**Conclusion**: Use `Runnable` (or `Callable`) instead of extending `Thread`.

---

## 4. Thread States (Lifecycle)

Java threads have the following states:

1. **NEW** – Thread created but not started
2. **RUNNABLE** – Ready to run or running
3. **BLOCKED** – Waiting for a monitor lock
4. **WAITING** – Waiting indefinitely for another thread
5. **TIMED_WAITING** – Waiting for a fixed time
6. **TERMINATED** – Execution finished

---

## 5. Deadlock: What it is and how to resolve it

### Deadlock occurs when:
- Two or more threads
- Hold resources
- And wait for each other forever

### Common causes:
- Nested synchronized blocks
- Inconsistent lock ordering

### Resolution strategies:
- Always acquire locks in the same order
- Use timeout-based locks
- Reduce lock scope
- Prefer higher-level concurrency utilities

---

## 6. How do threads communicate with each other?

Threads communicate by:
- **Shared memory** (shared variables)
- **Synchronization mechanisms**
    - `wait()`
    - `notify()`
    - `notifyAll()`
- **Concurrency utilities**
    - Blocking queues
    - Latches, barriers, semaphores

---

## 7. Difference between Object Lock and Class Lock

- **Object lock**
    - Lock on a specific instance
    - Acquired via synchronized instance methods or blocks
- **Class lock**
    - Lock on the `Class` object
    - Acquired via synchronized static methods

**Key difference**:  
Object locks allow parallel execution on different instances; class locks do not.

---

## 8. What is `join()` method?

- Causes the **current thread** to wait
- Until the **target thread finishes execution**
- Used to enforce execution order

---

## 9. What is `yield()` method?

- A hint to the scheduler that:
    - Current thread is willing to pause
    - Other threads of same priority may run
- No guarantee that yielding will happen

---

## 10. What is a Thread Pool?

A Thread Pool:
- Maintains a set of reusable threads
- Executes submitted tasks using these threads
- Avoids overhead of creating/destroying threads

---

## 11. Types of Thread Pools

Common types:
- Single-thread pool
- Fixed-size pool
- Cached pool
- Scheduled pool
- Work-stealing pool

---

## 12. What is TaskQueue in ThreadPool?

- A queue that holds **submitted tasks**
- Tasks wait here until a worker thread is available
- Different queue types affect performance and behavior

---

## 13. Which library is used to create ThreadPool?

- **Library**: `java.util.concurrent`
- **Core Interface**: `Executor`
- **Main Interface**: `ExecutorService`

---

## 14. How to submit a task to ThreadPool?

Tasks can be submitted by:
- Fire-and-forget execution
- Submission with result tracking
- Asynchronous execution models

---

## 15. Advantages of ThreadPool

- Reuses threads (better performance)
- Controls concurrency level
- Prevents resource exhaustion
- Simplifies thread management
- Improves system stability

---

## 16. Difference between `shutdown()` and `shutdownNow()`

- `shutdown()`
    - Stops accepting new tasks
    - Allows existing tasks to finish
- `shutdownNow()`
    - Attempts to stop running tasks
    - Interrupts threads
    - Returns pending tasks

---

## 17. What are Atomic Classes?

Atomic classes provide:
- Lock-free
- Thread-safe
- Non-blocking operations

Based on **CAS (Compare-And-Swap)**.

### Types of Atomic Classes
- Atomic integers
- Atomic arrays
- Atomic references
- Atomic field updaters

### When to use:
- Simple counters
- Flags
- Low-contention shared variables

---

## 18. What are Concurrent Collections?

Thread-safe collections optimized for concurrency:
- Avoid full synchronization
- Allow concurrent reads/writes
- Better scalability than synchronized collections

### Examples:
- Concurrent maps
- Concurrent queues
- Copy-on-write collections

---

## 19. Types of Locks and Their Advantages

- **Intrinsic lock (synchronized)**
    - Simple
    - JVM-managed
- **ReentrantLock**
    - Fairness
    - Try-lock
    - Interruptible locking
- **ReadWriteLock**
    - Multiple readers, single writer
    - Improves read-heavy performance
- **StampedLock**
    - Optimistic reads
    - High performance for read-dominated workloads

---

## 20. What is Future?

- Represents result of an asynchronous computation
- Allows:
    - Check completion
    - Wait for result
    - Cancel task

---

## 21. What is CompletableFuture?

An advanced Future that supports:
- Asynchronous chaining
- Non-blocking composition
- Functional-style callbacks

### Key capabilities:
- Task chaining
- Combining multiple futures
- Exception handling
- Asynchronous pipelines

---

