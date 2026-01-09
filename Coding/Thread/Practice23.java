package Coding.Thread;

public class Practice23 {
    private static final Object LOCK = new Object();
    private static boolean isOddTurn = true;

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            for (int i = 1; i <= 9; i += 2) {
                synchronized (LOCK) {
                    while (!isOddTurn) {
                        try {
                            LOCK.wait();
                        } catch (Exception e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    isOddTurn = false;
                    LOCK.notifyAll();
                }
            }
        });

        Thread evenThread = new Thread(() -> {
            for (int i = 2; i <= 10; i += 2) {
                synchronized (LOCK) {
                    while (isOddTurn) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    isOddTurn = true;
                    LOCK.notifyAll();
                }
            }
        });

        oddThread.start();
        evenThread.start();
    }
}
