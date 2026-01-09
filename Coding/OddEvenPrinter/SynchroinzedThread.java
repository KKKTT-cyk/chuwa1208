package OddEvenPrinter;

public class SynchroinzedThread {
    private static final Object lock = new Object();
    private static boolean isOddTurn = true;

    public static void main(String[] args) {

        Thread odd = new Thread(() -> {
            for (int i = 1; i <= 9; i += 2) {
                synchronized (lock) {
                    while (!isOddTurn) {
                        try {
                            lock.wait(); // release lock and sleep
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println(i);
                    isOddTurn = false;
                    lock.notify(); // evoke other thread
                }
            }
        });

        Thread even = new Thread(() -> {
            for (int i = 2; i <= 10; i += 2) {
                synchronized (lock) {
                    while (isOddTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println(i);
                    isOddTurn = true;
                    lock.notify();
                }
            }
        });

        odd.start();
        even.start();
    }
}
