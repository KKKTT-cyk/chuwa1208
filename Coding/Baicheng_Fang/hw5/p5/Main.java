import java.util.*;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<Double> doubleSupplier = () -> Math.random() * 100;
        System.out.println("Random double: " + doubleSupplier.get());

        Supplier<List<Integer>> listSupplier = () -> {
            List<Integer> ret = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                ret.add(i);
            }
            return ret;
        };
        System.out.println("Supplier list: " + listSupplier.get());

        String nullTest = SupplierUtil.getOrDefault(
                null,
                () -> {
                    System.out.println("Generating default value");
                    return "Default Value";
                }
                );

        String realTest= SupplierUtil.getOrDefault(
                "Real Value",
                () -> {
                    System.out.println("This should not print");
                    return "Default Value";
                }
                );

        Supplier<String> databaseSupplier = () -> {
            System.out.println("Querying Database...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Fetched Data";
        };

        System.out.println("DB result: " + databaseSupplier.get());
    }
}
