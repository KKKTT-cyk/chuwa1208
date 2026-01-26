import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * Homework 3: Add exception handling to Homework 2
 * If an exception occurs during any API call, return a default value and log the exception
 */
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
     * With exception handling - returns default values on error
     */
    public StoreData fetchAndMergeData(int id) throws Exception {

        // Asynchronously fetch product data with exception handling
        CompletableFuture<String> productFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("Fetching products from: https://jsonplaceholder.typicode.com/posts/" + id);
                    return fetchFromAPI("https://jsonplaceholder.typicode.com/posts/" + id);
                })
                .exceptionally(ex -> {
                    // Log the exception
                    System.err.println("ERROR: Failed to fetch product data");
                    System.err.println("Exception: " + ex.getMessage());
                    // Return default value
                    return getDefaultProductData();
                });

        // Asynchronously fetch review data with exception handling
        CompletableFuture<String> reviewFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("Fetching reviews from: https://jsonplaceholder.typicode.com/comments/" + id);
                    return fetchFromAPI("https://jsonplaceholder.typicode.com/comments/" + id);
                })
                .exceptionally(ex -> {
                    // Log the exception
                    System.err.println("ERROR: Failed to fetch review data");
                    System.err.println("Exception: " + ex.getMessage());
                    // Return default value
                    return getDefaultReviewData();
                });

        // Asynchronously fetch inventory data with exception handling
        CompletableFuture<String> inventoryFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("Fetching inventory from: https://jsonplaceholder.typicode.com/users/" + id);
                    return fetchFromAPI("https://jsonplaceholder.typicode.com/users/" + id);
                })
                .exceptionally(ex -> {
                    // Log the exception
                    System.err.println("ERROR: Failed to fetch inventory data");
                    System.err.println("Exception: " + ex.getMessage());
                    // Return default value
                    return getDefaultInventoryData();
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

    /**
     * Return default product data when API fails
     */
    private String getDefaultProductData() {
        System.out.println("Returning default product data");
        return "{\"id\":0,\"title\":\"Default Product\",\"body\":\"Product data unavailable\"}";
    }

    /**
     * Return default review data when API fails
     */
    private String getDefaultReviewData() {
        System.out.println("Returning default review data");
        return "{\"id\":0,\"name\":\"Default Review\",\"body\":\"Review data unavailable\"}";
    }

    /**
     * Return default inventory data when API fails
     */
    private String getDefaultInventoryData() {
        System.out.println("Returning default inventory data");
        return "{\"id\":0,\"name\":\"Default Inventory\",\"username\":\"N/A\"}";
    }
}