import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class Main {

    // This generic method applies a BiFunction to two inputs
    public static <T, U, R> R apply(T t, U u, BiFunction<T, U, R> function) {
        return function.apply(t, u);
    }

    public static void main(String[] args) {

        // Create a BiFunction<Integer, Integer, Integer> for addition
        BiFunction<Integer, Integer, Integer> add =
                (a, b) -> a + b;

        // Create a BiFunction<Integer, Integer, Integer> for multiplication
        BiFunction<Integer, Integer, Integer> multiply =
                (a, b) -> a * b;

        // Create a BiFunction<String, Integer, String> that repeats a string n times
        BiFunction<String, Integer, String> repeatString = (str, times) -> {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < times; i++) {
                result.append(str);
            }
            return result.toString();
        };

        // Create a BiFunction<List<Integer>, Integer, List<Integer>>
        // that adds a number to all elements in the list
        BiFunction<List<Integer>, Integer, List<Integer>> addToAll = (list, value) -> {
            List<Integer> result = new ArrayList<>();
            for (Integer num : list) {
                result.add(num + value);
            }
            return result;
        };

        // Test addition
        Integer sum = apply(3, 5, add);
        System.out.println(sum);

        // Test multiplication
        Integer product = apply(4, 6, multiply);
        System.out.println(product);

        // Test string repetition
        String repeated = apply("Hi", 3, repeatString);
        System.out.println(repeated);

        // Test adding a number to all elements in a list
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        List<Integer> updatedNumbers = apply(numbers, 10, addToAll);
        System.out.println(updatedNumbers);
    }
}
