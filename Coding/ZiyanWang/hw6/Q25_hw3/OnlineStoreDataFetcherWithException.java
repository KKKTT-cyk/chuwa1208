import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class OnlineStoreDataFetcherWithException {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {

        CompletableFuture<String> productFuture =
                fetchDataWithFallback(
                        "https://jsonplaceholder.typicode.com/posts",
                        "DEFAULT_PRODUCTS");

        CompletableFuture<String> reviewFuture =
                fetchDataWithFallback(
                        "https://jsonplaceholder.typicode.com/comments",
                        "DEFAULT_REVIEWS");

        CompletableFuture<String> inventoryFuture =
                fetchDataWithFallback(
                        "https://jsonplaceholder.typicode.com/todos",
                        "DEFAULT_INVENTORY");

        CompletableFuture<Void> all =
                CompletableFuture.allOf(productFuture, reviewFuture, inventoryFuture)
                        .thenRun(() -> {
                            String products = productFuture.join();
                            String reviews = reviewFuture.join();
                            String inventory = inventoryFuture.join();

                            System.out.println("Products: " + products);
                            System.out.println("Reviews: " + reviews);
                            System.out.println("Inventory: " + inventory);

                            System.out.println("Merged data successfully (with exception handling).");
                        });

        all.join();
    }

    private static CompletableFuture<String> fetchDataWithFallback(String url, String defaultValue) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    // Log exception
                    System.err.println("Error fetching data from " + url);
                    System.err.println("Exception: " + ex.getMessage());

                    // Return default value
                    return defaultValue;
                });
    }
}
