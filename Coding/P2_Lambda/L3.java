package P2_Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class L3 {
    public static void main(String[] args) {
        // Create a List<String> with names
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");
        System.out.println("Original names: " + names);

        // Create a Function<String, Integer> lambda that returns the length of a string
        Function<String, Integer> getLength = str -> str.length();
        System.out.println("\nTesting getLength function:");
        for (String name : names) {
            System.out.println(name + " has length: " + getLength.apply(name));
        }

        // Create a Function<String, String> lambda that converts to uppercase
        Function<String, String> toUpperCase = str -> str.toUpperCase();
        System.out.println("\nTesting toUpperCase function:");
        for (String name : names) {
            System.out.println(name + " -> " + toUpperCase.apply(name));
        }

        // Create a Function<Integer, String> lambda that converts number to "Even" or "Odd"
        Function<Integer, String> evenOrOdd = num -> (num % 2 == 0) ? "Even" : "Odd";
        System.out.println("\nTesting evenOrOdd function:");
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + " is " + evenOrOdd.apply(i));
        }

        // Use andThen() to chain two functions: first get length, then convert to "Even"/"Odd"
        Function<String, String> lengthThenEvenOdd = getLength.andThen(evenOrOdd);
        System.out.println("\nUsing andThen() - get length then check if Even/Odd:");
        for (String name : names) {
            System.out.println(name + " (length " + name.length() + ") -> " + lengthThenEvenOdd.apply(name));
        }

        // Use compose() to chain functions in reverse order
        Function<String, String> composedFunction = evenOrOdd.compose(getLength);
        System.out.println("\nUsing compose() - same result as andThen:");
        for (String name : names) {
            System.out.println(name + " -> " + composedFunction.apply(name));
        }

        // Create a generic method that transforms a list
        System.out.println("\nTesting generic transform method:");
        List<Integer> lengths = transform(names, getLength);
        System.out.println("Name lengths: " + lengths);

        List<String> upperCaseNames = transform(names, toUpperCase);
        System.out.println("Uppercase names: " + upperCaseNames);

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<String> evenOddResults = transform(numbers, evenOrOdd);
        System.out.println("Numbers as Even/Odd: " + evenOddResults);
    }

    public static <T, R> List<R> transform(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }
}
