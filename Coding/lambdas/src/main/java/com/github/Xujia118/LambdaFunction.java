package com.github.Xujia118;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class LambdaFunction {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");

        Function<String, Integer> lengthString = String::length;
        Function<String, String> toUpperCase = String::toUpperCase;
        Function<Integer, String> evenOddFunction = n -> n % 2 == 0 ? "Even" : "Odd";

        Function<String, String> lengthThenEvenOdd = lengthString.andThen(evenOddFunction);
        Function<String, String> reverseOrder = toUpperCase.compose(lengthThenEvenOdd);

        // Testing the transformations
        System.out.println("Original Names: " + names);

        List<Integer> lengths = transform(names, lengthString);
        System.out.println("Lengths: " + lengths);

        List<String> parityResult = transform(names, lengthThenEvenOdd);
        System.out.println("Length Parity: " + parityResult);
    }

    public static <T, R> List<R> transform(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T element : list) {
            result.add(function.apply(element));
        }
        return result;
    }
}
