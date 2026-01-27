package Coding.Thread.CompletableFuture;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class Practice2 {
    public static void main(String[] args) {
        CompletableFuture<String> future = Practice2.fetchData("https://jsonplaceholder.typicode.com/posts");

        future.thenAccept(response -> {
            System.out.println("Response length: " + response.length());
            System.out.println("Sample data: " + response.substring(0, 200));
        }).exceptionally(ex -> {
            System.err.println("Error fetching data: " + ex.getMessage());
            return null;
        });

        // Wait for async to finish before exiting main
        future.join();
    }

    public static CompletableFuture<String> fetchData(String urlString) {
        return CompletableFuture.supplyAsync(() -> {
            HttpURLConnection conn = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(urlString);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);

                int status = conn.getResponseCode();
                if (status != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + status);
                }

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                return response.toString();

            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (reader != null)
                        reader.close();
                    if (conn != null)
                        conn.disconnect();
                } catch (Exception ignored) {
                }
            }
        });
    }
}
