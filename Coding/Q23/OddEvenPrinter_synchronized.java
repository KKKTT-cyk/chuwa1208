package Q23;

import java.util.Objects;

public class OddEvenPrinter_synchronized {
    private final Object lock = new Object();
    private int num = 1;
    private final int MAX = 10;
    public void printOdd() {
        while(true) {
            synchronized (lock) {
                while (num <= MAX && num % 2 == 0) {
                    try{
                        lock.wait();
                    }catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                if (num > MAX) {
                    lock.notifyAll();
                    return;
                }
                System.out.println(Thread.currentThread().getName() + ": " + num);
                num++;
                lock.notifyAll();
            }
        }
    }
    public void printEven() {
        while (true) {
            synchronized (lock) {
                while (num <= MAX && num % 2 == 1) {
                    try{
                        lock.wait();
                    }catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                if (num > MAX) {
                    lock.notifyAll();
                    return;
                }
                System.out.println(Thread.currentThread().getName() + ": " + num);
                num ++;
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        OddEvenPrinter_synchronized p = new OddEvenPrinter_synchronized();
        Thread t1 = new Thread(p :: printOdd, "Thread-0");
        Thread t2 = new Thread(p :: printEven, "Thread-1");
        t1.start();
        t2.start();
    }
}
