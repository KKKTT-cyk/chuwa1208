package L1;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {

        // L1
        List<String> fruits = Arrays.asList("apple", "orange", "banana", "grape", "mango");

        System.out.println("===traditional method===");
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println(fruits.get(i));
        }
        System.out.println("===foreach===");
        fruits.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(fruits);
            }
        });
        // lambda expression
        System.out.println("===foreach lambda expression===");
        fruits.forEach(fruit -> System.out.println(fruit));
        // lambda expression method reference
        System.out.println("===lambda with a method reference===");
        fruits.forEach(System.out::println);

        System.out.println("===Consumer===");
        Consumer<String> printUpperCase =
                fruit -> System.out.println(fruit.toUpperCase());
        fruits.forEach(printUpperCase);
    }
}
