class SharedData {
    volatile int data;
}

public class ThreadCommunicationDemo {
    public static void main(String[] args) {
        SharedData shared = new SharedData();

        Runnable writer = () -> {
            shared.data = 100;
            System.out.println("Writer updated data to 100");
        };

        Runnable reader = () -> {
            System.out.println("Reader sees data: " + shared.data);
        };

        Thread t1 = new Thread(writer, "writer thread");
        Thread t2 = new Thread(reader, "reader thread");

        t1.start();
        t2.start();
    }
}

/*
This code does not guarantee the reader will read 100 each time, because volatile only guarantees visibility.
That means once the writer updates data, it will be directly in memory, therefore visible to all threads.
But there is a chance that reader accesses data first, before it is updated.
We should be mindful about this.
*/
