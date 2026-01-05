/*
Replace anonymous inner classes with lambda expressions using Consumer.
* Create a List<String> with 5 fruit names
* Use forEach with anonymous inner class that implements Consumer
* Replace the anonymous class with a lambda expression
* Replace the lambda with a method reference
* Create a Consumer<String> that prints fruit name in uppercase, use it with forEach
 */

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaFruitConsumer {
    public static void main(String[] args) {

        // Step 1: Create a List with 5 fruit names
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry", "Date", "Elderberry");

        // Step 2: Use traditional for-loop to print all fruits
        System.out.println("--- 1. Traditional For-Loop ---");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        // Step 3: Use forEach with anonymous inner class that implements Consumer
        System.out.println("\n--- 2. forEach with Anonymous Inner Class ---");
        fruits.forEach(new Consumer<String>() {
            @Override
            public void accept(String fruit) {
                System.out.println(fruit);
            }
        });

        // Step 4: Replace the anonymous class with a lambda expression
        System.out.println("\n--- 3. forEach with Lambda Expression ---");
        // Removing the boilerplate code of the anonymous class
        fruits.forEach(fruit -> System.out.println(fruit));

        // Step 5: Replace the lambda with a method reference
        System.out.println("\n--- 4. forEach with Method Reference ---");
        // Shortest syntax: referencing the println method of System.out
        fruits.forEach(System.out::println);

        // Step 6: Create a Consumer that prints fruit name in uppercase
        System.out.println("\n--- 5. Consumer that prints Uppercase ---");
        // Defining a specific Consumer strategy
        Consumer<String> printUpperCase = fruit -> System.out.println(fruit.toUpperCase());

        // Using the defined Consumer
        fruits.forEach(printUpperCase);
    }
}

