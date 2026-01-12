package com.github.Xujia118;

import java.util.List;
import java.util.function.Consumer;

public class LambdaConsumer {
    public static void main(String[] args) {
        List<String> fruits = List.of("apple", "banana", "cherry", "grape", "elderberry");

        // Traditional for looop
        System.out.println("\nTraditional for-loop:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        // Anonymous inner class
        System.out.println("\nAnonymous inner class:");
        fruits.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {

            }
        });

        // Lambda expression
        System.out.println("\nLambda expression:");
        fruits.forEach(fruit -> System.out.println(fruit));

        // Method reference
        System.out.println("\nMethod reference:");
        fruits.forEach(System.out::println);

        // Consumer
        System.out.println("\nUppercase Consumer:");
        Consumer<String> printUppercase =
                fruit -> System.out.println(fruit.toUpperCase());

        fruits.forEach(printUppercase);
    }
}
