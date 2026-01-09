package CompletableFuture;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;

public class H3 {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final ExecutorService IO_POOL = Executors.newFixedThreadPool(6);

    // -------- Models --------
    record Product(int id, String title) {}
    record Review(int productId, String content) {}
    record Inventory(int productId, boolean inStock) {}

    public static void main(String[] args) {

        List<Integer> productIds = List.of(1, 2, 3);

        CompletableFuture<List<Product>> productsFuture =
                CompletableFuture.supplyAsync(() -> fetchProducts(productIds), IO_POOL)
                        .exceptionally(ex -> {
                            log("Products API failed", ex);
                            return List.of();   // default value
                        });

        CompletableFuture<Map<Integer, List<Review>>> reviewsFuture =
                CompletableFuture.supplyAsync(() -> fetchReviews(productIds), IO_POOL)
                        .exceptionally(ex -> {
                            log("Reviews API failed", ex);
                            return Map.of();    // default value
                        });

        CompletableFuture<Map<Integer, Inventory>> inventoryFuture =
                CompletableFuture.supplyAsync(() -> fetchInventory(productIds), IO_POOL)
                        .exceptionally(ex -> {
                            log("Inventory API failed", ex);
                            return Map.of();    // default value
                        });

        CompletableFuture<Void> all =
                CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        all.join(); // wait for all

        // merge results safely
        List<Product> products = productsFuture.join();
        Map<Integer, List<Review>> reviews = reviewsFuture.join();
        Map<Integer, Inventory> inventory = inventoryFuture.join();

        for (Product p : products) {
            System.out.println("Product: " + p.title());
            System.out.println("Reviews: " +
                    reviews.getOrDefault(p.id(), List.of()).size());
            System.out.println("In stock: " +
                    inventory.getOrDefault(p.id(), new Inventory(p.id(), false)).inStock());
        }

        IO_POOL.shutdown();
    }

    // -------- Simulated API calls --------

    private static List<Product> fetchProducts(List<Integer> ids) {
        simulateFailure();
        List<Product> list = new ArrayList<>();
        for (int id : ids) {
            list.add(new Product(id, "Product-" + id));
        }
        return list;
    }

    private static Map<Integer, List<Review>> fetchReviews(List<Integer> ids) {
        simulateFailure();
        Map<Integer, List<Review>> map = new HashMap<>();
        for (int id : ids) {
            map.put(id, List.of(new Review(id, "Nice product")));
        }
        return map;
    }

    private static Map<Integer, Inventory> fetchInventory(List<Integer> ids) {
        simulateFailure();
        Map<Integer, Inventory> map = new HashMap<>();
        for (int id : ids) {
            map.put(id, new Inventory(id, true));
        }
        return map;
    }

    // -------- Helpers --------

    private static void simulateFailure() {
        // 30% chance to throw exception (for demo)
        if (Math.random() < 0.3) {
            throw new RuntimeException("Simulated API failure");
        }
    }

    private static void log(String msg, Throwable ex) {
        System.err.println("[ERROR] " + msg);
        System.err.println("Reason: " + ex.getMessage());
    }
}
