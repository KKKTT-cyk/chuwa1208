import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    public static void main(String[] args) {

        int a = 10;
        int b = 5;

        // Asynchronously calculate sum
        CompletableFuture<Integer> sumFuture =
                CompletableFuture.supplyAsync(() -> a + b);

        // Asynchronously calculate product
        CompletableFuture<Integer> productFuture =
                CompletableFuture.supplyAsync(() -> a * b);

        // Print results
        sumFuture.thenAccept(sum ->
                System.out.println("Sum: " + sum));

        productFuture.thenAccept(product ->
                System.out.println("Product: " + product));

        // Wait for both tasks to finish
        CompletableFuture.allOf(sumFuture, productFuture).join();

        System.out.println("Computation finished.");
    }
}
