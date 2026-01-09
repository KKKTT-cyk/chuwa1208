package Coding.Thread.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class Practice1 {
    public static void main(String[] args) {
        int a = 5;
        int b = 8;

        // Asynchronously calculate sum
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Calculating sum...");
            return a + b;
        });

        // Asynchronously calculate product
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Calculating product...");
            return a * b;
        });

        // Print results
        sumFuture.thenAccept(sum -> System.out.println("Sum = " + sum));

        productFuture.thenAccept(product -> System.out.println("Product = " + product));

        // Wait for both to finish (so program doesn't exit early)
        CompletableFuture.allOf(sumFuture, productFuture).join();
    }
}
