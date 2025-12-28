import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Main {

    // This generic method transforms a list by applying a Function to each element.
    public static <T, R> List<R> transform(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }

    public static void main(String[] args) {

        // Create a List<String> with names: ["Alice", "Bob", "Charlie", "David"]
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Create a Function<String, Integer> that returns the length of a string
        Function<String, Integer> getLength = s -> s.length();

        // Create a Function<String, String> that converts to uppercase
        Function<String, String> toUpperCase = s -> s.toUpperCase();

        // Create a Function<Integer, String> that converts number to "Even" or "Odd"
        Function<Integer, String> toEvenOrOdd = n -> (n % 2 == 0) ? "Even" : "Odd";

        // Transform names -> lengths
        List<Integer> lengths = transform(names, getLength);
        System.out.println(lengths);

        // Transform names -> uppercase
        List<String> uppercased = transform(names, toUpperCase);
        System.out.println(uppercased);

        // Use andThen() to chain two functions: first get length, then convert to "Even"/"Odd"
        Function<String, String> lengthThenEvenOdd = getLength.andThen(toEvenOrOdd);
        List<String> evenOddByNameLength = transform(names, lengthThenEvenOdd);
        System.out.println(evenOddByNameLength);

        // Use compose() to chain functions in reverse order.
        // toEvenOrOdd.compose(getLength) means: first getLength(String)->Integer, then toEvenOrOdd(Integer)->String
        Function<String, String> evenOddByCompose = toEvenOrOdd.compose(getLength);
        List<String> evenOddByNameLength2 = transform(names, evenOddByCompose);
        System.out.println(evenOddByNameLength2);
    }
}
