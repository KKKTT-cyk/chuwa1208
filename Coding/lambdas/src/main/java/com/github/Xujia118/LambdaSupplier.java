package com.github.Xujia118;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class LambdaSupplier {
    public static void main(String[] args) {
        // Supplier: random number
        Supplier<Double> randomSupplier = () -> Math.random() * 100;
        System.out.println(randomSupplier.get());

        // Supplier: list
        Supplier<List<Integer>> listSupplier = () -> {
            List<Integer> result = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                result.add(i);
            }
            return result;
        };
        System.out.println(listSupplier.get());

        // Supplier: default value
        String result1 = getOrDefault(null, () -> {
            System.out.println("Generating default value");
            return "Default Value";
        });

        System.out.println("Result1 = " + result1);

        String result2 = getOrDefault("Existing Value", () -> {
            System.out.println("You should NOT see this");
            return "Default Value";
        });

        System.out.println("Result2 = " + result2);

        // Supplier: expensive operation
        Supplier<String> dbSupplier = () -> {
            try {
                System.out.println("Querying database...");
                Thread.sleep(1000); // simulate expensive operation
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Data from DB";
        };

        // Will NOT trigger DB call
        String fast = getOrDefault("Cached Data", dbSupplier);
        System.out.println(fast);

        // Will trigger DB call
        String slow = getOrDefault(null, dbSupplier);
        System.out.println(slow);
    }

    public static <T> T getOrDefault(T value, Supplier<T> defaultSupplier) {
        if (value == null) return defaultSupplier.get();

        return value;
    }
}
