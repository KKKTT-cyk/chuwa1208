package hw.q23;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinterLock {
    private int number = 1;
    private final int MAX = 10;
    private boolean isOddTurn = true;

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private void printOdd() {
        lock.lock();

        try {
            while (number <= MAX) {
                while (!isOddTurn) {
                    condition.await();
                }

                if (number <= MAX) {
                    System.out.println("Odd thread: " + number);
                    number++;
                    isOddTurn = false;
                    condition.signal();
                }
            }
        } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    private void printEven() {
        lock.lock();

        try {
            while (number <= MAX) {
                while (isOddTurn) {
                    condition.await();
                }

                if (number <= MAX) {
                    System.out.println("Even thread: " + number);
                    number++;
                    isOddTurn = true;
                    condition.signal();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        OddEvenPrinterLock printer = new OddEvenPrinterLock();

        Runnable task1 = printer::printOdd;
        Runnable task2 = printer::printEven;

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();
    }
}
