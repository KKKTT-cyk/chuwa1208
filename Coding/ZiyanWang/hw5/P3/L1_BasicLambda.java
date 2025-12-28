import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class L1_BasicLambda {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList(
                "apple", "banana", "orange", "grape", "mango"
        );
        // traiditional for loop
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println(fruits.get(i));
        }
        // forEach with an anonymous inner class that implements Consumer
        fruits.forEach(new Consumer<String>() {
            @Override
            public void accept(String fruit) {
                System.out.println(fruit);
            }
        });

        // Replace the anonymous inner class with a lambda expression
        fruits.forEach(fruit -> System.out.println(fruit));

        // Replace the lambda expression with a method reference
        fruits.forEach(System.out::println);

        // Create a Consumer<String> that prints fruit name in uppercase
        Consumer<String> printUpperCase = fruit ->
                System.out.println(fruit.toUpperCase());

        // Use the Consumer with forEach
        fruits.forEach(printUpperCase);
    }
}