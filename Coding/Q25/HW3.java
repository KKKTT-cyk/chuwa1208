package Q25;


import java.net.URI;
import java.net.http.*;
        import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class HW3 {

    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    public static void main(String[] args) {
        int productId = 1;

        CompletableFuture<String> productFuture =
                fetchWithFallback(
                        "https://jsonplaceholder.typicode.com/posts/" + productId,
                        "{ \"product\": \"DEFAULT_PRODUCT\" }"
                );

        CompletableFuture<String> reviewsFuture =
                fetchWithFallback(
                        "https://jsonplaceholder.typicode.com/comments?postId=" + productId,
                        "[]"
                );

        CompletableFuture<String> inventoryFuture =
                fetchWithFallback(
                        "https://jsonplaceholder.typicode.com/todos/" + productId,
                        "{ \"inventory\": \"DEFAULT_INVENTORY\" }"
                );

        CompletableFuture<String> merged =
                productFuture.thenCombine(reviewsFuture, (product, reviews) ->
                        "=== PRODUCT ===\n" + product + "\n\n=== REVIEWS ===\n" + reviews
                ).thenCombine(inventoryFuture, (partial, inventory) ->
                        partial + "\n\n=== INVENTORY ===\n" + inventory
                );

        System.out.println(merged.join());
    }

    private static CompletableFuture<String> fetchWithFallback(String url, String fallbackJson) {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();

        return client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    // log exception
                    System.err.println("[ERROR] API call failed: " + url);
                    System.err.println("[ERROR] " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
                    // return default value
                    return fallbackJson;
                });
    }
}
