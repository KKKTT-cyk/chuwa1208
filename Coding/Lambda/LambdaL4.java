package Coding.Lambda;

import java.util.*;
import java.util.function.Supplier;

public class LambdaL4 {
    public static <T> T getOrDefault(T value, Supplier<T> defaultSupplier) {
        return (value != null) ? value : defaultSupplier.get();
    }

    public static void main(String[] args) {
        // Supplier that returns random number between 0 and 100
        Supplier<Double> randomSupplier = () -> Math.random() * 100;
        System.out.println("Random number: " + randomSupplier.get());

        // Supplier that creates new ArrayList with 1-10
        Supplier<List<Integer>> listSupplier = () -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                list.add(i);
            }
            return list;
        };
        System.out.println("List from supplier: " + listSupplier.get());

        // Test getOrDefault
        System.out.println("Test getOrDefault with null:");
        String result1 = getOrDefault(null, () -> {
            System.out.println("Generating default value");
            return "Default";
        });
        System.out.println("Result1: " + result1);

        System.out.println("Test getOrDefault with non-null:");
        String result2 = getOrDefault("Actual Value", () -> {
            System.out.println("This should NOT print");
            return "Default";
        });
        System.out.println("Result2: " + result2);

        // Supplier simulating expensive DB query
        Supplier<String> dbSupplier = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Data from DB";
        };

        System.out.println("Simulating expensive DB query:");
        System.out.println(dbSupplier.get());
    }
}
