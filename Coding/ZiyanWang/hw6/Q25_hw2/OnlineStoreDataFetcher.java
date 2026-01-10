import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class OnlineStoreDataFetcher {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {

        CompletableFuture<String> productFuture =
                fetchData("https://jsonplaceholder.typicode.com/posts");

        CompletableFuture<String> reviewFuture =
                fetchData("https://jsonplaceholder.typicode.com/comments");

        CompletableFuture<String> inventoryFuture =
                fetchData("https://jsonplaceholder.typicode.com/todos");

        // Merge all results
        CompletableFuture<Void> mergedFuture =
                CompletableFuture.allOf(productFuture, reviewFuture, inventoryFuture)
                        .thenRun(() -> {
                            try {
                                String products = productFuture.get();
                                String reviews = reviewFuture.get();
                                String inventory = inventoryFuture.get();

                                System.out.println("Products fetched: " + products.length());
                                System.out.println("Reviews fetched: " + reviews.length());
                                System.out.println("Inventory fetched: " + inventory.length());

                                System.out.println("Data merged successfully. Ready for further processing.");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

        // Wait for all tasks to complete
        mergedFuture.join();
    }

    private static CompletableFuture<String> fetchData(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}
