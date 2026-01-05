package Coding.Lambda;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaL3 {
    public static <T, R> List<R> transform(List<T> list, Function<T, R> function) {
        return list.stream()
                .map(function)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Function that returns length of string
        Function<String, Integer> lengthFunction = s -> s.length();

        // Function that converts string to uppercase
        Function<String, String> toUpperCaseFunction = s -> s.toUpperCase();

        // Function that converts Integer to "Even"/"Odd"
        Function<Integer, String> evenOddFunction = n -> (n % 2 == 0) ? "Even" : "Odd";

        // andThen: length -> even/odd
        Function<String, String> lengthThenEvenOdd = lengthFunction.andThen(evenOddFunction);
        List<String> results1 = transform(names, lengthThenEvenOdd);
        System.out.println("Length then Even/Odd: " + results1);

        // compose: uppercase then length
        Function<String, Integer> upperThenLength = lengthFunction.compose(toUpperCaseFunction);
        List<Integer> results2 = transform(names, upperThenLength);
        System.out.println("Uppercase then length: " + results2);
    }
}
