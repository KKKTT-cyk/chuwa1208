package P2_Lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class L1 {
    public static void main(String[] args) {
        // Step 1: Create a List<String> with 5 fruit names
        List<String> fruits = Arrays.asList("apple", "banana", "cherry", "strawberry", "blueberry");

        System.out.println("=== Traditional for-loop ===");
        // Step 2: Use traditional for-loop to print all fruits
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        System.out.println("\n=== forEach with anonymous inner class ===");
        // Step 3: Use forEach with anonymous inner class that implements Consumer
        fruits.forEach(new Consumer<String>() {
            @Override
            public void accept(String fruit) {
                System.out.println(fruit);
            }
        });

        System.out.println("\n===  Replace anonymous class with lambda expression ===");
        // Step 4: Replace the anonymous class with a lambda expression
        fruits.forEach((String fruit) -> {
            System.out.println(fruit);
        });

        System.out.println("\n=== Replace lambda with method reference ===");
        // Step 5: Replace the lambda with a method reference
        fruits.forEach(System.out::println);

        System.out.println("\n=== Consumer<String> that prints in uppercase ===");
        // Step 6: Create a Consumer<String> that prints fruit name in uppercase
        Consumer<String> uppercasePrinter = fruit -> System.out.println(fruit.toUpperCase());

        // Use it with forEach
        fruits.forEach(uppercasePrinter);

        System.out.println("\n=== Alternative: Direct uppercase lambda ===");
        // Alternative: directly in forEach
        fruits.forEach(fruit -> System.out.println(fruit.toUpperCase()));
    }
}
