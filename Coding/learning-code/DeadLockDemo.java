public class DeadLockDemo {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Runnable task1 = () -> {
            synchronized (lock1) {
                System.out.println("Thread 1: Holding lock1");
                sleep(100);

                synchronized (lock2) {
                    System.out.println("Thread 1: Holding lock2");
                }
            }
        };

        Runnable task2 = () -> {
            synchronized (lock1) {
                System.out.println("Thread 2: Holding lock1");
                sleep(100);

                synchronized (lock2) {
                    System.out.println("Thread 2: Holding lock2");
                }
            }
        };

        Thread t1 = new Thread(task1, "T1");
        Thread t2 = new Thread(task2, "T2");

        t1.start();
        t2.start();
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
