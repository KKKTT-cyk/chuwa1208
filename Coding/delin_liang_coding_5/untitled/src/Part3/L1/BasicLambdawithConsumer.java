package Part3.L1;

import java.util.*;
import java.util.function.Consumer;


public class BasicLambdawithConsumer {
    public static void main(String[] args) {

        // Step 1: Create a List<String> with 5 fruit names
        List<String> fruits = Arrays.asList(
                "apple", "banana", "orange", "grape", "mango"
        );

        // Step 2: Use traditional for-loop to print all fruits
        System.out.println("Traditional for-loop:");
        for (String fruit: fruits){
            System.out.println(fruit);
        }

        // Step 3: Use forEach with anonymous inner class that implements Consumer
        System.out.println("\nforEach w/ anonyous inner class:");
        fruits.forEach(new Consumer<String>() {
            @Override
            public void accept(String fruit) {
                System.out.println(fruit);
            }
        });

        // Step 4: Replace the anonymous class with a lambda expression
        System.out.println("\nReplace anonymous class w/ lambda:");
        fruits.forEach(fruit -> System.out.println(fruit));

        // Step 5: Replace the lambda with a method reference
        System.out.println("\nReplace lambda w/ method reference:");
        fruits.forEach(System.out::println);

        // Step 6: Create a Consumer<String> that prints fruit name in uppercase, use it with forEach
        System.out.println("\nPrint upper case:");
        Consumer<String> upperCasePrinter =
                fruit -> System.out.println(fruit.toUpperCase());

        fruits.forEach(upperCasePrinter);
    }
}
