/*
Practice transforming data using Function interface.
Create a List<String> with names: ["Alice", "Bob", "Charlie", "David"]
Create a Function<String, Integer> lambda that returns the length of a string
Create a Function<String, String> lambda that converts to uppercase
Create a Function<Integer, String> lambda that converts number to "Even" or "Odd"
Use andThen() to chain two functions: first get length, then convert to "Even"/"Odd"
Use compose() to chain functions in reverse order
Create a generic method <T, R> List<R> transform(List<T> list, Function<T, R>
function) that transforms a list
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class LambdaFunctionPractice {

    public static void main(String[] args) {
        // Step 1: Create a List with names
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        System.out.println("Original Names: " + names);

        // Step 2: Create a Function lambda that returns the length of a string
        // Input: String, Output: Integer
        Function<String, Integer> getLength = s -> s.length();

        List<Integer> lengths = transform(names, getLength);
        System.out.println("Lengths: " + lengths);

        // Step 3: Create a Function lambda that converts to uppercase
        // Input: String, Output: String
        Function<String, String> toUpper = s -> s.toUpperCase();

        List<String> upperNames = transform(names, toUpper);
        System.out.println("Uppercase: " + upperNames);

        // Step 4: Create a Function lambda that converts number to "Even" or "Odd"
        // Input: Integer, Output: String
        Function<Integer, String> checkEvenOdd = i -> (i % 2 == 0) ? "Even" : "Odd";

        // We cannot apply this directly to 'names' list yet because names are Strings,
        // and this function expects Integers. We need to chain it.

        // Step 5: Use andThen() to chain two functions
        // Logic: String -> (getLength) -> Integer -> (checkEvenOdd) -> String
        Function<String, String> lengthThenCheck = getLength.andThen(checkEvenOdd);

        List<String> parityResults = transform(names, lengthThenCheck);
        System.out.println("andThen (Length -> Parity): " + parityResults);

        // Step 6: Use compose() to chain functions in reverse order
        // Logic: checkEvenOdd(getLength(x))
        // compose() executes the argument (getLength) first, and then the caller (checkEvenOdd).
        // It achieves the same result as above but is defined "backwards".
        Function<String, String> composedCheck = checkEvenOdd.compose(getLength);

        List<String> composedResults = transform(names, composedCheck);
        System.out.println("compose (Parity of Length): " + composedResults);
    }

    // Step 7: Create a generic method transform
    // <T> is the input type, <R> is the return type
    public static <T, R> List<R> transform(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            // function.apply(t) executes the lambda logic
            result.add(function.apply(t));
        }
        return result;
    }
}
