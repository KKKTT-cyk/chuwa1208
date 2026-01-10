package q25.hw2_and_hw3;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OnlineStore {
    private static final HttpClient client = HttpClient.newHttpClient();

    private static CompletableFuture<String> fetchData(String url,
                                                       String defaultValue /*added for hw3*/) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                //.thenApply(HttpResponse::body); // hw2

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    System.out.println("Error fetching data from " + url
                            + "\nError Details :" + ex.getMessage());
                    return defaultValue;
                }); // hw3
    }

    public static void main(String[] args) {

        // default values added for hw3
        String PRODUCTS_DEFAULT = "PRODUCTS_DEFAULT";
        String REVIEWS_DEFAULT = "REVIEWS_DEFAULT";
        String INVENTORY_DEFAULT = "INVENTORY_DEFAULT";

        // fake APIs
        String productsApi = "https://jsonplaceholder.typicode.com/posts";
        String reviewsApi = "https://jsonplaceholder.typicode.com/comments";
        String inventoryApi = "https://jsonplaceholder.typicode.com/todos";

        CompletableFuture<String> productsFuture = fetchData(productsApi, PRODUCTS_DEFAULT);
        CompletableFuture<String> reviewsFuture = fetchData(reviewsApi, REVIEWS_DEFAULT);
        CompletableFuture<String> inventoryFuture = fetchData(inventoryApi, INVENTORY_DEFAULT);

        CompletableFuture<Void> all =
                CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture)
                        .thenRun(() -> {
                                String products = productsFuture.join();
                                String reviews = reviewsFuture.join();
                                String inventory = inventoryFuture.join();

                                System.out.println("Products: " + products);
                                System.out.println("Reviews: " + reviews);
                                System.out.println("Inventory: " + inventory);

                                System.out.println("Data merged successfully!");
                        });
        all.join();


    }
}
