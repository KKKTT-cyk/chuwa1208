package Part3.L4;

import java.util.*;
import java.util.function.Supplier;

public class LambdawithSupplier {
    public static void main(String[] args) {
        // Step 1: Create a Supplier<Double> that returns a random number between 0 and 100
        Supplier<Double> randomSupplier =
                () -> Math.random() * 100;

        System.out.println("Random number:");
        System.out.println(randomSupplier.get());

        // Step 2: Create a Supplier<List<Integer>> that creates a new ArrayList with numbers 1-10
        Supplier<List<Integer>> listSupplier = () -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                list.add(i);
            }
            return list;
        };

        System.out.println("\nList from Supplier:");
        System.out.println(listSupplier.get());

        // Step 4: Test the method:
        // 4-1: Pass null and a Supplier that prints "Generating default value" before returning
        System.out.println("\ngetOrDefault with null:");
        String result1 = getOrDefault(null, () -> {
            System.out.println("Generating default value");
            return "Default Value";
        });
        System.out.println("Result: " + result1);

        // 4-2: Pass a non-null value and verify the Supplier is NOT called (lazy evaluation)
        System.out.println("\ngetOrDefault with non-null:");
        String result2 = getOrDefault("Non-null Value", () -> {
            System.out.println("Generating default value");
            return "Default Value";
        });
        System.out.println("Result: " + result2);

        // Step 5: Create a Supplier<String> that simulates expensive database query (sleep 1 second, return "Data from DB")
        Supplier<String> dbSupplier = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Data from DB";
        };

        System.out.println("\nSimulating expensive DB query:");
        long start = System.currentTimeMillis();
        String data = getOrDefault(null, dbSupplier);
        long end = System.currentTimeMillis();

        System.out.println("Result: " + data);
        System.out.println("Time taken (ms): " + (end - start));
    }

    // Step 3: Write a method <T> T getOrDefault(T value, Supplier<T> defaultSupplier) :
    // If value is not null, return value
    // Otherwise, call supplier.get() and return the result
    public static <T> T getOrDefault(T value, Supplier<T> defaultSupplier) {
        if (value != null) {
            return value;
        }
        return defaultSupplier.get();
    }
}
