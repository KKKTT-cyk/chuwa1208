import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * Test version - simulates API failure to test exception handling
 */
public class OnlineStoreDataFetcherTest {

    private final HttpClient httpClient;

    public OnlineStoreDataFetcherTest() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public static void main(String[] args) throws Exception {
        OnlineStoreDataFetcherTest fetcher = new OnlineStoreDataFetcherTest();

        System.out.println("=== Testing exception handling ===\n");
        long startTime = System.currentTimeMillis();

        // Fetch and merge data
        StoreData storeData = fetcher.fetchAndMergeData(1);

        long endTime = System.currentTimeMillis();

        // Print results
        System.out.println("\n=== Merged Store Data ===");
        System.out.println(storeData);
        System.out.println("\nTotal time: " + (endTime - startTime) + "ms");
    }

    public StoreData fetchAndMergeData(int id) throws Exception {

        // Normal product API
        CompletableFuture<String> productFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("Fetching products from: https://jsonplaceholder.typicode.com/posts/" + id);
                    return fetchFromAPI("https://jsonplaceholder.typicode.com/posts/" + id);
                })
                .exceptionally(ex -> {
                    System.err.println("ERROR: Failed to fetch product data");
                    System.err.println("Exception: " + ex.getMessage());
                    return getDefaultProductData();
                });

        // Simulate review API failure with invalid URL
        CompletableFuture<String> reviewFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("Fetching reviews from: https://invalid-url-to-simulate-failure.com/reviews/" + id);
                    return fetchFromAPI("https://invalid-url-to-simulate-failure.com/reviews/" + id);
                })
                .exceptionally(ex -> {
                    System.err.println("ERROR: Failed to fetch review data");
                    System.err.println("Exception: " + ex.getMessage());
                    return getDefaultReviewData();
                });

        // Normal inventory API
        CompletableFuture<String> inventoryFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("Fetching inventory from: https://jsonplaceholder.typicode.com/users/" + id);
                    return fetchFromAPI("https://jsonplaceholder.typicode.com/users/" + id);
                })
                .exceptionally(ex -> {
                    System.err.println("ERROR: Failed to fetch inventory data");
                    System.err.println("Exception: " + ex.getMessage());
                    return getDefaultInventoryData();
                });

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

    private String getDefaultProductData() {
        System.out.println("Returning default product data");
        return "{\"id\":0,\"title\":\"Default Product\",\"body\":\"Product data unavailable\"}";
    }

    private String getDefaultReviewData() {
        System.out.println("Returning default review data");
        return "{\"id\":0,\"name\":\"Default Review\",\"body\":\"Review data unavailable\"}";
    }

    private String getDefaultInventoryData() {
        System.out.println("Returning default inventory data");
        return "{\"id\":0,\"name\":\"Default Inventory\",\"username\":\"N/A\"}";
    }
}