package Q23;

import java.util.TreeMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinter_ReentrantLock {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition cond = lock.newCondition();

    private int num = 1;
    private final int MAX = 10;

    public void printOdd() {
        while (true) {
            lock.lock();
            try {
                while (num <= MAX && num % 2 == 0) {
                    cond.await();
                }
                if (num > MAX) {
                    cond.signalAll();
                    return;
                }
                System.out.println(Thread.currentThread().getName() + ": " + num);
                num ++;
                cond.signalAll();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }finally {
                lock.unlock();
            }
        }
    }
    public void printEven() {
        while (true) {
            lock.lock();
            try {
                while (num <= MAX && num % 2 == 1) {
                    cond.await();
                }
                if (num > MAX) {
                    cond.signalAll();
                    return;
                }
                System.out.println(Thread.currentThread().getName() + ": " + num);
                num ++;
                cond.signalAll();
            }catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        OddEvenPrinter_ReentrantLock p = new OddEvenPrinter_ReentrantLock();

        Thread t1 = new Thread(p ::printOdd,"Thread-0");
        Thread t2 = new Thread(p ::printEven , "Thread-1");

        t1.start();
        t2.start();
    }
}
