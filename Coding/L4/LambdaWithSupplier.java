import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class LambdaWithSupplier {
    
    public static void main(String[] args) {
        System.out.println("=== Lambda with Supplier - Lazy Evaluation Practice ===\n");
        
        // 1. Supplier<Double> that returns a random number between 0 and 100
        Supplier<Double> randomSupplier = () -> Math.random() * 100;
        
        System.out.println("1. Random number supplier:");
        System.out.println("   First call: " + randomSupplier.get());
        System.out.println("   Second call: " + randomSupplier.get());
        System.out.println();
        
        // 2. Supplier<List<Integer>> that creates a new ArrayList with numbers 1-10
        Supplier<List<Integer>> listSupplier = () -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                list.add(i);
            }
            return list;
        };
        
        System.out.println("2. List supplier:");
        System.out.println("   Generated list: " + listSupplier.get());
        System.out.println();
        
        // 3. Testing getOrDefault method
        System.out.println("3. Testing getOrDefault method:");
        
        // Create a Supplier that prints a message before returning
        Supplier<String> defaultSupplier = () -> {
            System.out.println("   Generating default value");
            return "Default Value";
        };
        
        // Test with null - should call the supplier
        System.out.println("   Test with null:");
        String result1 = getOrDefault(null, defaultSupplier);
        System.out.println("   Result: " + result1);
        System.out.println();
        
        // Test with non-null value - should NOT call the supplier (lazy evaluation)
        System.out.println("   Test with non-null value:");
        String result2 = getOrDefault("Existing Value", defaultSupplier);
        System.out.println("   Result: " + result2);
        System.out.println("   (Notice: Supplier was NOT called - lazy evaluation works!)");
        System.out.println();
        
        // 4. Supplier<String> that simulates expensive database query
        Supplier<String> expensiveDatabaseQuery = () -> {
            System.out.println("   Executing expensive database query...");
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Data from DB";
        };
        
        System.out.println("4. Testing expensive database query supplier:");
        System.out.println("   Starting query...");
        long startTime = System.currentTimeMillis();
        String dbResult = expensiveDatabaseQuery.get();
        long endTime = System.currentTimeMillis();
        System.out.println("   Result: " + dbResult);
        System.out.println("   Time taken: " + (endTime - startTime) + "ms");
        System.out.println();
    }

    public static <T> T getOrDefault(T value, Supplier<T> defaultSupplier) {
        if (value != null) {
            return value;
        } else {
            return defaultSupplier.get();
        }
    }
}
