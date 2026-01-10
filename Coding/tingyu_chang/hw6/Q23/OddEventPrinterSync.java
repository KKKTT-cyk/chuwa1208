/*
### Q22\. Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10. (solution is in com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter)
1. One solution use synchronized and wait notify
2. One solution use ReentrantLock and await,signal
```
Thread-0: 1
Thread-1: 2
Thread-0: 3
Thread-1: 4
Thread-0: 5
Thread-1: 6
Thread-0: 7
Thread-1: 8
Thread-0: 9
Thread-1: 10
Process finished with exit code 0
```
 */


package tingyu_chang.hw6.Q23;

/**
 * Solution 1: Using synchronized, wait(), and notifyAll()
 */
public class OddEventPrinterSync {
    private static final Object monitor = new Object();
    private static int value = 1;
    private static final int MAX_VALUE = 10;

    public static void main(String[] args) {
        PrintRunnable runnable = new PrintRunnable();
        // Create two threads sharing the same runnable task
        new Thread(runnable, "Thread-1").start();
        new Thread(runnable, "Thread-2").start();
    }

    static class PrintRunnable implements Runnable {
        @Override
        public void run() {
            synchronized (monitor) {
                while (value <= MAX_VALUE) {
                    // 1. Print the current value
                    System.out.println(Thread.currentThread().getName() + ": " + value++);

                    // 2. Wake up the other thread
                    monitor.notifyAll();

                    // 3. Wait if there are still numbers to print
                    try {
                        if (value <= MAX_VALUE) {
                            monitor.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

