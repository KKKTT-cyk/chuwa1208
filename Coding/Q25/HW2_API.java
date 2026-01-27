package Q25;


import java.net.URI;
import java.net.http.*;
        import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class HW2_API {

    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    public static void main(String[] args) {
        int productId = 1;

        CompletableFuture<String> productFuture =
                fetch("https://jsonplaceholder.typicode.com/posts/" + productId);

        CompletableFuture<String> reviewsFuture =
                fetch("https://jsonplaceholder.typicode.com/comments?postId=" + productId);

        CompletableFuture<String> inventoryFuture =
                fetch("https://jsonplaceholder.typicode.com/todos/" + productId);

        // merge：等三个都拿到后组合成一个结果对象（这里用 String 拼接演示）
        CompletableFuture<String> merged =
                productFuture.thenCombine(reviewsFuture, (product, reviews) ->
                        "=== PRODUCT ===\n" + product + "\n\n=== REVIEWS ===\n" + reviews
                ).thenCombine(inventoryFuture, (partial, inventory) ->
                        partial + "\n\n=== INVENTORY ===\n" + inventory
                );

        System.out.println(merged.join());
    }

    private static CompletableFuture<String> fetch(String url) {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        return client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}
