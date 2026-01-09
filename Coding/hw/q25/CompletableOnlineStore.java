package hw.q25;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableOnlineStore {
    
    private static final HttpClient client = HttpClient.newHttpClient();

    // Generic asycn fetch function
    public static CompletableFuture<String> fetchApi(String url, String defaultValue) {
        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .GET()
                                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                                .thenApply(HttpResponse::body)
                                .exceptionally(ex -> {
                                    System.out.println("Error fetching " + url + ": " + ex.getMessage());
                                    return defaultValue;
                                })
        ;
    }

    public static void main(String[] args) {
        
        // URLs for fake APIs
        String productsUrl = "https://jsonplaceholder.typicode.com/posts";
        String reviewsUrl = "https://jsonplaceholder.typicode.com/comments";
        String inventoryUrl = "https://jsonplaceholder.typicode.com/todos";

        // Default values in case of exception
        String defaultProducts = "[]";
        String defaultReviews = "[]";
        String defaultInventory = "[]";

        // Create CompletableFutures for each API
        CompletableFuture<String> productsFuture = fetchApi(productsUrl, defaultProducts);
        CompletableFuture<String> reviewsFuture = fetchApi(reviewsUrl, defaultReviews);
        CompletableFuture<String> inventoryFuture = fetchApi(inventoryUrl, defaultInventory);

        // Combine all futures
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        // When all are done, merge results
        allFutures.thenRun(() -> {
            try {
                String products = productsFuture.get();
                String reviews = reviewsFuture.get();
                String inventory = inventoryFuture.get();

                System.out.println("=== Products ===");
                System.out.println(products.substring(0, Math.min(200, products.length())) + "...");
                System.out.println("\n=== Reviews ===");
                System.out.println(reviews.substring(0, Math.min(200, reviews.length())) + "...");
                System.out.println("\n=== Inventory ===");
                System.out.println(inventory.substring(0, Math.min(200, inventory.length())) + "...");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).join(); 
    }
}
