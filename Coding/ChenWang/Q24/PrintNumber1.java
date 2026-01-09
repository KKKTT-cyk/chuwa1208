public class PrintNumber1 {
    private static final Object lock = new Object();
    private static int currentThread = 0;
    private static int n = 1;
    private static final int MAX = 30;

    public static void main(String[] args) {
        Thread t0 = new Thread(() -> printNumber(0, 1, 10), "Thread0");
        Thread t1 = new Thread(() -> printNumber(1, 21, 30), "Thread1");
        Thread t2 = new Thread(() -> printNumber(2, 11, 20), "Thread2");

        t0.start();
        t1.start();
        t2.start();
    }

    private static void printNumber(int threadId, int start, int end) {
        synchronized (lock) {
            while (n <= MAX) {
                while (currentThread != threadId && n <= MAX) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                while (n >= start && n <= end && n <= MAX) {
                    System.out.println(Thread.currentThread().getName() + ": " + n);
                    n++;
                }

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
