class SharedResource {
    int data;
    boolean available = false;

    public synchronized void produce(int value) throws InterruptedException {
        while (available) {
            wait(); // wait if data is not consumed
        }
        data = value;
        available = true;
        System.out.println("Produced: " + value);
        notify(); // notify consumer
    }

    public synchronized int consume() throws InterruptedException {
        while (!available) {
            wait(); // wait if no data
        }
        available = false;
        System.out.println("Consumed: " + data);
        notify(); // notify producer
        return data;
    }
}

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    resource.produce(i);
                } catch (InterruptedException e) {
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    resource.consume();
                } catch (InterruptedException e) {
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
