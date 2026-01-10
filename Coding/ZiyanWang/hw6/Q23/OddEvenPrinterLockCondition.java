import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinterLockCondition {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition turnChanged = lock.newCondition();

    private int current = 1;
    private final int max = 10;

    public void printOdd() {
        while (true) {
            lock.lock();
            try {
                while (current <= max && current % 2 == 0) {
                    try {
                        turnChanged.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                if (current > max) {
                    turnChanged.signalAll();
                    return;
                }

                System.out.println(Thread.currentThread().getName() + ": " + current);
                current++;
                turnChanged.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printEven() {
        while (true) {
            lock.lock();
            try {
                while (current <= max && current % 2 == 1) {
                    try {
                        turnChanged.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                if (current > max) {
                    turnChanged.signalAll();
                    return;
                }

                System.out.println(Thread.currentThread().getName() + ": " + current);
                current++;
                turnChanged.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OddEvenPrinterLockCondition printer = new OddEvenPrinterLockCondition();

        Thread tOdd = new Thread(printer::printOdd, "Thread-0");
        Thread tEven = new Thread(printer::printEven, "Thread-1");

        tOdd.start();
        tEven.start();

        tOdd.join();
        tEven.join();
        System.out.println("Process finished with exit code 0");
    }
}
