package hw.q25;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableSumAndProduct {

    public static void main(String[] args) {
        int a = 5;
        int b = 7;

        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> a + b);
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> a * b);

        try {
            System.out.println("Sum is: " + sumFuture.get());
            System.out.println("Product is: " + productFuture.get());
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
