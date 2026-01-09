package hw.q23;

public class OddEvenPrinter {
    private int number = 1;
    private final int MAX = 10;
    private boolean isOddTurn = true;
    
    private synchronized void printOdd() {
        while (number <= 10) {
            while (!isOddTurn) {
                try {
                    wait();                    
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            
            if (number <= MAX) {
                System.out.println("Odd printer: " + number);
                number++;
                isOddTurn = false;
                notify();
            }
        }
    }

    private synchronized void printEven() {
        while (number <= MAX) {
            while (isOddTurn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if (number <= MAX) {
                System.out.println("Even printer: " + number);
                number++;
                isOddTurn = true;
                notify();
            }
        }
    }

    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter();

        Runnable task1 = printer::printOdd;
        Runnable task2 = printer::printEven;

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();
    }
}
