package ThreeRandomThread;

public class Main {
    final static Object lock = new Object();
    static int cur = 0;
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 10; i++) {
                    cur = cur + 1;
                    System.out.println("T1: " + cur);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("T2: " + (cur + 1));
                    cur = cur + 1;
                }
            }
        });

        Thread t3 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("T3: " + (cur + 1));
                    cur = cur + 1;
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
