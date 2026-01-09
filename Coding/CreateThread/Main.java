package CreateThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static public void main(String[] args){
        //Runnable
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("task 0");
            }
        });
        t.start();
        try {
            t.join();
        } catch (Exception e){
            System.out.println(e);
        }


        // thread pool
        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.execute(() -> {
            System.out.println("task 1");
        });

        pool.execute(() -> {
            System.out.println("task 2");
        });

        pool.shutdown();
    }

}
