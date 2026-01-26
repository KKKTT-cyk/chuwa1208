package L4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class LazyEvaluation {

    // generic method: get a value or call supplier lazily
    public static <T> T getOrDefault(T value, Supplier<T> defaultSupplier) {
        if (value != null) {
            return value;
        }
        return defaultSupplier.get();
    }

    public static void main(String[] args) {
        Supplier<Double> randomSupplier = () -> Math.random() * 100;
        Supplier<List<Integer>> newArray = () -> {
            List<Integer> list= new ArrayList<>();
            for (int i = 1; i < 11; i++) {
                list.add(i);
            }
            return list;
        };
        System.out.println("Random number: " + randomSupplier.get());
        System.out.println("New Array: " + newArray.get());

        System.out.println("\n===Test null===");
        String r1 = getOrDefault(null, () -> {
            System.out.println("Generate a default value");
            return "Default value";
        });
        System.out.println("Test null: " + r1);

        System.out.println("\n===Test nons null value===");
        String r2 = getOrDefault("Exists Value", () -> {
            System.out.println("You should NOT see this line");
            return "Default Value";
        });
        System.out.println("None null value " + r2);

        Supplier<String> dbSupplier = () -> {
            try{
                System.out.println("Querying database...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "db from database";
        };

        System.out.println("\n=== Expensive DB supplier test ===");
        long start = System.currentTimeMillis();
        String dbResult = getOrDefault("Cached Data", dbSupplier);
        long end = System.currentTimeMillis();

        System.out.println("Result: " + dbResult);
        System.out.println("Time used: " + (end - start) + " ms");
    }
}
