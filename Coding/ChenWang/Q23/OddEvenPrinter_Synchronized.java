public class OddEvenPrinter_Synchronized {
    private static final Object lock = new Object();
    private static int counter = 1;
    private static final int MAX = 10;

    public static void main(String[] args) {
        Thread oddThread = new Thread(new OddPrinter(), "Thread0");
        Thread evenThread = new Thread(new EvenPrinter(), "Thread1");

        oddThread.start();
        evenThread.start();
    }

    static class OddPrinter implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (counter <= MAX) {
                    while (counter % 2 == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (counter <= MAX) {
                        System.out.println(Thread.currentThread().getName() + ": " + counter);
                        counter++;
                        lock.notifyAll();
                    }
                }
            }
        }
    }

    static class EvenPrinter implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (counter <= MAX) {
                    while (counter % 2 == 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (counter <= MAX) {
                        System.out.println(Thread.currentThread().getName() + ": " + counter);
                        counter++;
                        lock.notifyAll();
                    }
                }
            }
        }
    }
}
