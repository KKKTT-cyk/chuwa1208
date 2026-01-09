public class PrintNumber1 {
    private static final Object lock = new Object();
    private static int currentThread = 0; // 0, 1, 2 to control which thread should run
    private static int n = 1;
    private static final int MAX = 30;

    public static void main(String[] args) {
        Thread t0 = new Thread(() -> printNumber(0, 1, 10), "Thread-0");
        Thread t1 = new Thread(() -> printNumber(1, 21, 30), "Thread-1");
        Thread t2 = new Thread(() -> printNumber(2, 11, 20), "Thread-2");

        t0.start();
        t1.start();
        t2.start();
    }

    private static void printNumber(int threadId, int start, int end) {
        synchronized (lock) {
            while (n <= MAX) {
                // Wait until it's this thread's turn
                while (currentThread != threadId && n <= MAX) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Print this thread's range
                while (n >= start && n <= end && n <= MAX) {
                    System.out.println(Thread.currentThread().getName() + ": " + n);
                    n++;
                }

                // Move to next thread (0 -> 2 -> 1 -> done)
                if (threadId == 0) {
                    currentThread = 2;
                } else if (threadId == 2) {
                    currentThread = 1;
                } else {
                    currentThread = 3; // Done
                }

                lock.notifyAll();
            }
        }
    }
}