import java.util.*;
import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        
        List<String> fruits = Arrays.asList(
                "apple", "banana", "cranberry", "kiwi", "orange"
                );

        for (int i = 0; i < fruits.size(); i++) {
            System.out.println(fruits.get(i));
        }

        fruits.forEach(new Consumer<String>(){
            @Override
            public void accept(String fruit) {
                System.out.println(fruit);
            }
        });

        fruits.forEach(fruit -> System.out.println(fruit));

        fruits.forEach(System.out::println);

        Consumer<String> printUpperCase = fruit -> System.out.println(fruit.toUpperCase());
        fruits.forEach(printUpperCase);
    }
}
