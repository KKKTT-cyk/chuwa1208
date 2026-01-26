import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class OnlineStoreDataFetcher {

    private final HttpClient httpClient;

    public OnlineStoreDataFetcher() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public static void main(String[] args) throws Exception {
        OnlineStoreDataFetcher fetcher = new OnlineStoreDataFetcher();

        System.out.println("=== Fetching data from three APIs ===\n");
        long startTime = System.currentTimeMillis();

        // Fetch and merge data
        StoreData storeData = fetcher.fetchAndMergeData(1);

        long endTime = System.currentTimeMillis();

        // Print results
        System.out.println("\n=== Merged Store Data ===");
        System.out.println(storeData);
        System.out.println("\nTotal time: " + (endTime - startTime) + "ms");
    }

    /**
     * Fetch data from three APIs asynchronously and merge
     */
    public StoreData fetchAndMergeData(int id) throws Exception {

        // Asynchronously fetch product data (using JSONPlaceholder posts API)
        CompletableFuture<String> productFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching products from: https://jsonplaceholder.typicode.com/posts/" + id);
            return fetchFromAPI("https://jsonplaceholder.typicode.com/posts/" + id);
        });

        // Asynchronously fetch review data (using JSONPlaceholder comments API)
        CompletableFuture<String> reviewFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching reviews from: https://jsonplaceholder.typicode.com/comments/" + id);
            return fetchFromAPI("https://jsonplaceholder.typicode.com/comments/" + id);
        });

        // Asynchronously fetch inventory data (using JSONPlaceholder users API)
        CompletableFuture<String> inventoryFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching inventory from: https://jsonplaceholder.typicode.com/users/" + id);
            return fetchFromAPI("https://jsonplaceholder.typicode.com/users/" + id);
        });

        // Wait for all futures to complete and merge data
        CompletableFuture<StoreData> mergedFuture = CompletableFuture
                .allOf(productFuture, reviewFuture, inventoryFuture)
                .thenApply(v -> {
                    try {
                        String productData = productFuture.get();
                        String reviewData = reviewFuture.get();
                        String inventoryData = inventoryFuture.get();

                        return new StoreData(productData, reviewData, inventoryData);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        return mergedFuture.get();
    }

    /**
     * Send HTTP GET request to public API
     */
    private String fetchFromAPI(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            return response.body();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch from API: " + url, e);
        }
    }
}