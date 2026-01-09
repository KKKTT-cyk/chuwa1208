/*
create 3 threads, one thread ouput 1-10, one thread output 11-20, one thread output 21-22. threads run sequence is random. (solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)
Thread-0: 1
Thread-0: 2
Thread-0: 3
Thread-0: 4
Thread-0: 5
Thread-0: 6
Thread-0: 7
Thread-0: 8
Thread-0: 9
Thread-0: 10
Thread-2: 11
Thread-2: 12
Thread-2: 13
Thread-2: 14
Thread-2: 15
Thread-2: 16
Thread-2: 17
Thread-2: 18
Thread-2: 19
Thread-2: 20
Thread-1: 21
Thread-1: 22
Thread-1: 23
Thread-1: 24
Thread-1: 25
Thread-1: 26
Thread-1: 27
Thread-1: 28
Thread-1: 29
Thread-1: 30
 */

package tingyu_chang.hw6.Q24;

public class Q24_PrintNumber {
    // Shared static variable to keep track of the number across threads
    private static int n = 1;

    public static void main(String[] args) {
        // Create 3 threads, each executing the same printNumber task
        Thread t1 = new Thread(() -> printNumber());
        Thread t2 = new Thread(() -> printNumber());
        Thread t3 = new Thread(() -> printNumber());

        // Start threads. The OS decides which one runs first (Random Sequence)
        t1.start();
        t2.start();
        t3.start();
    }

    // 'static synchronized' locks the Class object.
    // Only one thread can execute this method at a time.
    private static synchronized void printNumber() {
        int count = 10;
        // Loop 10 times
        while (count-- > 0) {
            System.out.println(Thread.currentThread().getName() + ": " + n++);
            try {
                // Sleep to simulate work (and prove the lock holds even during sleep)
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Notify any other threads waiting on the class lock
        Q24_PrintNumber.class.notifyAll();
    }
}

