package Part3.L3;

import java.util.*;
import java.util.function.Function;

public class LambdawithFunction {
    public static void main(String[] args){
        // Step 1: Create a List<String> with names: ["Alice", "Bob", "Charlie", "David"]
        List<String> names = Arrays.asList(
                "Alice", "Bob", "Charlie", "David"
        );

        // Step 2: Create a Function<String, Integer> lambda that returns the length of a string
        Function<String, Integer> stringLengthFunction =
                s -> s.length();

        // Step 3: Create a Function<String, String> lambda that converts to uppercase
        Function<String, String> upperCaseFunction =
                s -> s.toUpperCase();

        // Step 4: Create a Function<Integer, String> lambda that converts number to "Even" or "Odd"
        Function<Integer, String> evenOddFunction =
                n -> (n % 2 == 0) ? "Even" : "Odd";

        // Step 5: Use andThen() to chain two functions: first get length, then convert to "Even"/"Odd"
        Function<String, String> lengthThenEvenOdd =
                stringLengthFunction.andThen(evenOddFunction);

        // Step 6: Use compose() to chain functions in reverse order
        Function<String, String> composedFunction =
                evenOddFunction.compose(stringLengthFunction);

        // Tests:
        System.out.println("String lengths:");
        System.out.println(transform(names, stringLengthFunction));

        System.out.println("\nUppercase:");
        System.out.println(transform(names, upperCaseFunction));

        System.out.println("\nLength andThen Even/Odd:");
        System.out.println(transform(names, lengthThenEvenOdd));

        System.out.println("\nLength compose Even/Odd:");
        System.out.println(transform(names, composedFunction));
    }

    // Step 7: Create a generic method <T, R> List<R> transform(List<T> list, Function<T, R> function) that transforms a list
    public static <T, R> List<R> transform(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }

        return result;
    }
}
