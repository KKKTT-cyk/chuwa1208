package hw.q24;

class NumberPrinter implements Runnable {
    private final int start;
    private final int end;

    NumberPrinter(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            System.out.println(Thread.currentThread().getName() + " -> " + i);
            try {
                Thread.sleep(10); // small delay to increase randomness
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class RandomThreadPrinter {
    public static void main(String[] args) {
        NumberPrinter printer1 = new NumberPrinter(0, 10);
        NumberPrinter printer2 = new NumberPrinter(11, 20);
        NumberPrinter printer3 = new NumberPrinter(21, 30);

        Thread t1 = new Thread(printer1, "thread-1");
        Thread t2 = new Thread(printer2, "thread-2");
        Thread t3 = new Thread(printer3, "thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
