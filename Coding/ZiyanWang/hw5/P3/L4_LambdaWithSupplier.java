import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Main {

    // This method returns the value if it is not null.
    // Otherwise, it calls the supplier to lazily generate a default value.
    public static <T> T getOrDefault(T value, Supplier<T> defaultSupplier) {
        if (value != null) {
            return value;
        }
        return defaultSupplier.get();
    }

    public static void main(String[] args) {

        // Create a Supplier<Double> that returns a random number between 0 and 100
        Supplier<Double> randomNumberSupplier =
                () -> new Random().nextDouble() * 100;

        Double randomNumber = randomNumberSupplier.get();
        System.out.println(randomNumber);

        // Create a Supplier<List<Integer>> that creates a new ArrayList with numbers 1-10
        Supplier<List<Integer>> listSupplier = () -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                list.add(i);
            }
            return list;
        };

        List<Integer> numbers = listSupplier.get();
        System.out.println(numbers);

        // Test getOrDefault() with null value
        String value1 = getOrDefault(null, () -> {
            System.out.println("Generating default value");
            return "Default Value";
        });
        System.out.println(value1);

        // Test getOrDefault() with non-null value
        String value2 = getOrDefault("Existing Value", () -> {
            System.out.println("This should not be printed");
            return "Default Value";
        });
        System.out.println(value2);

        // Create a Supplier<String> that simulates an expensive database query
        Supplier<String> databaseSupplier = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Data from DB";
        };

        // Demonstrate lazy evaluation with expensive supplier
        String dbResult = getOrDefault(null, databaseSupplier);
        System.out.println(dbResult);
    }
}
