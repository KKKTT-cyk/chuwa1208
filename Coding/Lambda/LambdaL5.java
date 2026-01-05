package Coding.Lambda;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class LambdaL5 {
    public static <T, U, R> R apply(T t, U u, BiFunction<T, U, R> function) {
        return function.apply(t, u);
    }

    public static void main(String[] args) {
        // BiFunction for addition
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

        // BiFunction for multiplication
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;

        // BiFunction to repeat string n times
        BiFunction<String, Integer, String> repeat = (str, n) -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(str);
            }
            return sb.toString();
        };

        // BiFunction that adds a number to all elements in the list
        BiFunction<List<Integer>, Integer, List<Integer>> addToAll = (list, n) -> list.stream()
                .map(x -> x + n)
                .collect(Collectors.toList());

        // Testing
        System.out.println("Add 5 + 3: " + apply(5, 3, add));
        System.out.println("Multiply 5 * 3: " + apply(5, 3, multiply));
        System.out.println("Repeat 'Hi' 4 times: " + apply("Hi", 4, repeat));

        List<Integer> numbers = Arrays.asList(1, 2, 3);
        List<Integer> resultList = apply(numbers, 10, addToAll);
        System.out.println("Add 10 to each element: " + resultList);
    }
}
