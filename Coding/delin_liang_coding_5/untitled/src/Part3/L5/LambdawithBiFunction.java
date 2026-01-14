package Part3.L5;

import java.util.*;
import java.util.function.BiFunction;

public class LambdawithBiFunction {
    public static void main(String[] args) {
        // Step 1: Create a BiFunction<Integer, Integer, Integer> lambda for addition
        BiFunction<Integer, Integer, Integer> add =
                (a, b) -> a + b;

        // Step 2: Create a BiFunction<Integer, Integer, Integer> lambda for multiplication
        BiFunction<Integer, Integer, Integer> multiply =
                (a, b) -> a * b;

        // Step 3: Create a BiFunction<String, Integer, String> lambda that repeats a string n times
        BiFunction<String, Integer, String> repeatString =
                (s, n) -> {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < n; i++) {
                        sb.append(s);
                    }
                    return sb.toString();
                };

        // Step 4: Create a BiFunction<List<Integer>, Integer, List<Integer>> that adds a number to all elements in the list
        BiFunction<List<Integer>, Integer, List<Integer>> addToAll =
                (list, num) -> {
                    List<Integer> result = new ArrayList<>();
                    for (Integer n : list) {
                        result.add(n + num);
                    }
                    return result;
                };

        // Tests:
        System.out.println("Addition:");
        System.out.println(apply(1, 3, add));

        System.out.println("\nMultiplication:");
        System.out.println(apply(4, 6, multiply));

        System.out.println("\nRepeat string:");
        System.out.println(apply("Go", 3, repeatString));

        System.out.println("\nAdd number to list:");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        System.out.println(apply(numbers, 100, addToAll));
    }

    // Step 5: Write a method <T, U, R> R apply(T t, U u, BiFunction<T, U, R> function) that applies the BiFunction Test with different BiFunction lambdas
    public static <T, U, R> R apply(T t, U u, BiFunction<T, U, R> function) {
        return function.apply(t, u);
    }
}
