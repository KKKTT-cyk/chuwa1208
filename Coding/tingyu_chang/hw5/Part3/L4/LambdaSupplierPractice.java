/*
Practice lazy evaluation using Supplier.
Create a Supplier<Double> that returns a random number between 0 and 100
Create a Supplier<List<Integer>> that creates a new ArrayList with numbers 1-10
Write a method <T> T getOrDefault(T value, Supplier<T> defaultSupplier):
If value is not null, return value
Otherwise, call supplier.get() and return the result
Test the method:
Pass null and a Supplier that prints "Generating default value" before returning
Pass a non-null value and verify the Supplier is NOT called (lazy evaluation)
Create a Supplier<String> that simulates expensive database query (sleep 1 second, return
"Data from DB")
 */

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class LambdaSupplierPractice {

    public static void main(String[] args) {

        // Step 1: Create a Supplier that returns a random number between 0 and 100
        Supplier<Double> randomSupplier = () -> Math.random() * 100;
        System.out.println("Random number: " + randomSupplier.get());

        // Step 2: Create a Supplier<List<Integer>> that creates a new ArrayList with numbers 1-10
        Supplier<List<Integer>> listSupplier = () -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                list.add(i);
            }
            return list;
        };
        System.out.println("New List from Supplier: " + listSupplier.get());

        // Step 4: Test the getOrDefault method (Lazy Evaluation)
        System.out.println("\n--- Testing Lazy Evaluation ---");

        // Define a supplier that prints when it runs (to prove if it was called or not)
        Supplier<String> defaultStringSupplier = () -> {
            System.out.println("-> Generating default value... (Supplier Called)");
            return "Default Value";
        };

        // Case A: Value is NULL
        // Expectation: Supplier SHOULD be called.
        String result1 = getOrDefault(null, defaultStringSupplier);
        System.out.println("Result 1: " + result1);

        // Case B: Value is NOT NULL
        // Expectation: Supplier SHOULD NOT be called (no print statement above).
        String result2 = getOrDefault("Existing Value", defaultStringSupplier);
        System.out.println("Result 2: " + result2);

        // Step 5: Simulate expensive database query
        System.out.println("\n--- Testing Expensive Operation ---");
        Supplier<String> dbSupplier = () -> {
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Data from DB";
        };

        long start = System.currentTimeMillis();
        // Since value is null, we force the expensive query to run
        String dbResult = getOrDefault(null, dbSupplier);
        long end = System.currentTimeMillis();

        System.out.println("Result: " + dbResult);
        System.out.println("Time taken: " + (end - start) + "ms");
    }

    // Step 3: Write the generic getOrDefault method
    // This demonstrates lazy evaluation: the 'defaultSupplier' is only executed (.get())
    // if the primary value is actually null.
    public static <T> T getOrDefault(T value, Supplier<T> defaultSupplier) {
        if (value != null) {
            return value;
        } else {
            return defaultSupplier.get();
        }
    }
}
