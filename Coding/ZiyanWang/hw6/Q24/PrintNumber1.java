public class PrintNumber1 {
    private static final Object lock = new Object();
    private static int currentThread = 0; // 0 -> 2 -> 1
    private static int n = 1;
    private static final int MAX = 30;

    public static void main(String[] args) {
        Thread t0 = new Thread(() -> printRange(0, 1, 10, 2), "Thread0");
        Thread t2 = new Thread(() -> printRange(2, 11, 20, 1), "Thread2");
        Thread t1 = new Thread(() -> printRange(1, 21, 30, 3), "Thread1"); // 3 means done

        t0.start();
        t1.start();
        t2.start();
    }

    private static void printRange(int threadId, int start, int end, int nextThread) {
        while (true) {
            synchronized (lock) {
                // wait until it's my turn OR finished
                while (n <= MAX && currentThread != threadId) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                // finished
                if (n > MAX) {
                    lock.notifyAll();
                    return;
                }

                // print my range
                while (n >= start && n <= end && n <= MAX) {
                    System.out.println(Thread.currentThread().getName() + ": " + n);
                    n++;
                }

                // move to next
                currentThread = nextThread;
                lock.notifyAll();

                // if I already printed my entire range, I can exit
                return;
            }
        }
    }
}
