package q25.hw1;

import java.util.concurrent.CompletableFuture;

public class SumAndProductMain {

    public static void main(String[] args) {

        int a = 5;
        int b = 8;

        CompletableFuture<Integer> sumFuture =
                CompletableFuture.supplyAsync(() -> a + b);

        CompletableFuture<Integer> productFuture =
                CompletableFuture.supplyAsync(() -> a * b);

        sumFuture.thenAccept(result ->
                System.out.println("Sum: " + result)
        );

        productFuture.thenAccept(result ->
                System.out.println("Product: " + result)
        );

        sumFuture.join();
        productFuture.join();
    }
}
