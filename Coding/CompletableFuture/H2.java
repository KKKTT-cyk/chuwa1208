package CompletableFuture;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
public class H2 {
    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    private static final ExecutorService IO_POOL = Executors.newFixedThreadPool(8);

    // -------- Models (very simple) --------
    static class Product {
        final int id;
        final String title;

        Product(int id, String title) {
            this.id = id;
            this.title = title;
        }
    }

    static class Review {
        final int productId;
        final String email;
        final String body;

        Review(int productId, String email, String body) {
            this.productId = productId;
            this.email = email;
            this.body = body;
        }
    }

    static class Inventory {
        final int productId;
        final boolean inStock;

        Inventory(int productId, boolean inStock) {
            this.productId = productId;
            this.inStock = inStock;
        }
    }

    static class ProductView {
        final Product product;
        final List<Review> reviews;
        final Inventory inventory;

        ProductView(Product product, List<Review> reviews, Inventory inventory) {
            this.product = product;
            this.reviews = reviews;
            this.inventory = inventory;
        }
    }

    public static void main(String[] args) {
        // choose some product IDs you want to assemble
        List<Integer> productIds = List.of(1, 2, 3, 4, 5);

        // 1) fetch products list (simulate products API)
        CompletableFuture<List<Product>> productsFuture = CompletableFuture.supplyAsync(
                () -> fetchProducts(productIds),
                IO_POOL
        );

        // 2) fetch reviews for each product concurrently (simulate reviews API)
        CompletableFuture<Map<Integer, List<Review>>> reviewsFuture = CompletableFuture.supplyAsync(
                () -> fetchReviewsByProduct(productIds),
                IO_POOL
        );

        // 3) fetch inventory for each product concurrently (simulate inventory API)
        CompletableFuture<Map<Integer, Inventory>> inventoryFuture = CompletableFuture.supplyAsync(
                () -> fetchInventoryByProduct(productIds),
                IO_POOL
        );

        // 4) merge all results after they’re all complete
        CompletableFuture<List<ProductView>> merged =
                CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture)
                        .thenApply(v -> {
                            List<Product> products = productsFuture.join();
                            Map<Integer, List<Review>> reviewsMap = reviewsFuture.join();
                            Map<Integer, Inventory> inventoryMap = inventoryFuture.join();

                            return products.stream()
                                    .map(p -> new ProductView(
                                            p,
                                            reviewsMap.getOrDefault(p.id, List.of()),
                                            inventoryMap.getOrDefault(p.id, new Inventory(p.id, false))
                                    ))
                                    .collect(Collectors.toList());
                        });

        // 5) further processing
        List<ProductView> views = merged.join();
        for (ProductView pv : views) {
            System.out.println("==== Product " + pv.product.id + " ====");
            System.out.println("Title: " + pv.product.title);
            System.out.println("InStock: " + pv.inventory.inStock);
            System.out.println("Reviews: " + pv.reviews.size());
            if (!pv.reviews.isEmpty()) {
                System.out.println("Sample review email: " + pv.reviews.get(0).email);
            }
        }

        IO_POOL.shutdown();
    }

    // -------- Fetchers (call public APIs) --------

    private static List<Product> fetchProducts(List<Integer> productIds) {
        // JSONPlaceholder: /posts/{id}
        List<CompletableFuture<Product>> futures = productIds.stream()
                .map(id -> CompletableFuture.supplyAsync(() -> {
                    String json = httpGet("https://jsonplaceholder.typicode.com/posts/" + id);
                    int pid = extractInt(json, "\"id\":");
                    String title = extractString(json, "\"title\":");
                    return new Product(pid, title);
                }, IO_POOL))
                .toList();

        return futures.stream().map(CompletableFuture::join).toList();
    }

    private static Map<Integer, List<Review>> fetchReviewsByProduct(List<Integer> productIds) {
        // JSONPlaceholder: /comments?postId={id}
        List<CompletableFuture<Map.Entry<Integer, List<Review>>>> futures = productIds.stream()
                .map(pid -> CompletableFuture.supplyAsync(() -> {
                    String json = httpGet("https://jsonplaceholder.typicode.com/comments?postId=" + pid);
                    List<Review> reviews = parseReviewsArray(pid, json);
                    return Map.entry(pid, reviews);
                }, IO_POOL))
                .toList();

        Map<Integer, List<Review>> map = new HashMap<>();
        for (var f : futures) {
            var e = f.join();
            map.put(e.getKey(), e.getValue());
        }
        return map;
    }

    private static Map<Integer, Inventory> fetchInventoryByProduct(List<Integer> productIds) {
        // JSONPlaceholder: /todos/{id}
        // We'll treat "completed=false" as "in stock" (just a silly simulation)
        List<CompletableFuture<Map.Entry<Integer, Inventory>>> futures = productIds.stream()
                .map(pid -> CompletableFuture.supplyAsync(() -> {
                    String json = httpGet("https://jsonplaceholder.typicode.com/todos/" + pid);
                    int id = extractInt(json, "\"id\":");
                    boolean completed = extractBoolean(json, "\"completed\":");
                    boolean inStock = !completed; // simulation rule
                    return Map.entry(pid, new Inventory(id, inStock));
                }, IO_POOL))
                .toList();

        Map<Integer, Inventory> map = new HashMap<>();
        for (var f : futures) {
            var e = f.join();
            map.put(e.getKey(), e.getValue());
        }
        return map;
    }

    // -------- HTTP helper --------

    private static String httpGet(String url) {
        try {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();
            HttpResponse<String> resp = CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
            if (resp.statusCode() >= 200 && resp.statusCode() < 300) return resp.body();
            throw new RuntimeException("HTTP " + resp.statusCode() + " for " + url);
        } catch (Exception e) {
            throw new RuntimeException("Failed GET " + url, e);
        }
    }

    // -------- Tiny JSON helpers (good enough for this demo) --------
    // NOTE: For real projects, use Jackson/Gson.

    private static int extractInt(String json, String key) {
        int idx = json.indexOf(key);
        if (idx < 0) return -1;
        idx += key.length();
        while (idx < json.length() && (json.charAt(idx) == ' ')) idx++;
        int end = idx;
        while (end < json.length() && Character.isDigit(json.charAt(end))) end++;
        return Integer.parseInt(json.substring(idx, end));
    }

    private static boolean extractBoolean(String json, String key) {
        int idx = json.indexOf(key);
        if (idx < 0) return false;
        idx += key.length();
        while (idx < json.length() && (json.charAt(idx) == ' ')) idx++;
        if (json.startsWith("true", idx)) return true;
        if (json.startsWith("false", idx)) return false;
        return false;
    }

    private static String extractString(String json, String key) {
        int idx = json.indexOf(key);
        if (idx < 0) return "";
        idx += key.length();
        // skip spaces and first quote
        while (idx < json.length() && json.charAt(idx) != '"') idx++;
        idx++; // move after quote
        int end = idx;
        while (end < json.length() && json.charAt(end) != '"') end++;
        return json.substring(idx, end);
    }

    private static List<Review> parseReviewsArray(int productId, String jsonArray) {
        // Very naive parsing: split objects by "},{".
        // This works for jsonplaceholder comments format.
        String trimmed = jsonArray.trim();
        if (trimmed.equals("[]")) return List.of();
        // remove [ and ]
        if (trimmed.startsWith("[")) trimmed = trimmed.substring(1);
        if (trimmed.endsWith("]")) trimmed = trimmed.substring(0, trimmed.length() - 1);

        String[] objects = trimmed.split("\\},\\s*\\{");
        List<Review> reviews = new ArrayList<>();
        for (String obj : objects) {
            String o = obj;
            if (!o.startsWith("{")) o = "{" + o;
            if (!o.endsWith("}")) o = o + "}";
            String email = extractString(o, "\"email\":");
            String body = extractString(o, "\"body\":");
            reviews.add(new Review(productId, email, body));
        }
        return reviews;
    }
}
