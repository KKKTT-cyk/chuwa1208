package CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class H1 {
    public static void main(String[] args) {

        int a = 3;
        int b = 4;

        // 异步计算和
        CompletableFuture<Integer> sumFuture =
                CompletableFuture.supplyAsync(() -> a + b);

        // 异步计算积
        CompletableFuture<Integer> productFuture =
                CompletableFuture.supplyAsync(() -> a * b);

        // 等待并打印结果
        System.out.println("Sum: " + sumFuture.join());
        System.out.println("Product: " + productFuture.join());
    }
}
