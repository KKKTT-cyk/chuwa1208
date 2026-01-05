package Coding.Lambda;

import java.util.*;
import java.util.function.Consumer;

public class LambdaL1 {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry", "Date", "Elderberry");

        // Traditional for-loop
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        // forEach with anonymous inner class
        fruits.forEach(new Consumer<String>() {
            @Override
            public void accept(String fruit) {
                System.out.println(fruit);
            }
        });

        // Replace with lambda expression
        fruits.forEach(fruit -> System.out.println(fruit));

        // Replace with method reference
        fruits.forEach(System.out::println);

        // Consumer printing uppercase fruit names
        Consumer<String> printUpperCase = fruit -> System.out.println(fruit.toUpperCase());
        fruits.forEach(printUpperCase);
    }
}
