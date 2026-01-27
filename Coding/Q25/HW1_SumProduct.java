package Q25;

import java.util.concurrent.CompletableFuture;

public class HW1_SumProduct {
    public static void main(String[] args) {
        int a = 6, b = 7;
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> a + b);
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> a * b);

        // After two thread then print result
        CompletableFuture<Void> all = CompletableFuture.allOf(sumFuture, productFuture).thenRun(() -> {
            System.out.println("sum = " + sumFuture.join());
            System.out.println("product = " + productFuture.join());
        });
        all.join();
    }
}
