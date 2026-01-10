public class OddEvenPrinterWaitNotify {

    private final Object lock = new Object();
    private int current = 1;              // shared state
    private final int max = 10;

    public void printOdd() {
        while (true) {
            synchronized (lock) {
                // wait until it's odd's turn OR finished
                while (current <= max && current % 2 == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                if (current > max) {
                    lock.notifyAll(); // wake up the other thread so it can exit too
                    return;
                }

                System.out.println(Thread.currentThread().getName() + ": " + current);
                current++;
                lock.notifyAll();
            }
        }
    }

    public void printEven() {
        while (true) {
            synchronized (lock) {
                // wait until it's even's turn OR finished
                while (current <= max && current % 2 == 1) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                if (current > max) {
                    lock.notifyAll();
                    return;
                }

                System.out.println(Thread.currentThread().getName() + ": " + current);
                current++;
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OddEvenPrinterWaitNotify printer = new OddEvenPrinterWaitNotify();

        Thread tOdd = new Thread(printer::printOdd, "Thread-0");
        Thread tEven = new Thread(printer::printEven, "Thread-1");

        tOdd.start();
        tEven.start();

        tOdd.join();
        tEven.join();
        System.out.println("Process finished with exit code 0");
    }
}
