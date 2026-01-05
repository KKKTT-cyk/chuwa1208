/*
Practice using BiFunction for two-argument operations.
Create a BiFunction<Integer, Integer, Integer> lambda for addition
Create a BiFunction<Integer, Integer, Integer> lambda for multiplication
Create a BiFunction<String, Integer, String> lambda that repeats a string n times
Create a BiFunction<List<Integer>, Integer, List<Integer>> that adds a number to all elements in the list
Write a method <T, U, R> R apply(T t, U u, BiFunction<T, U, R> function) that applies the BiFunction
Test with different BiFunction lambdas
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class LambdaBiFunctionPractice {

    public static void main(String[] args) {

        // Step 1: BiFunction for addition (Integer, Integer) -> Integer
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

        System.out.println("Addition (5 + 3): " + apply(5, 3, add));

        // Step 2: BiFunction for multiplication (Integer, Integer) -> Integer
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;

        System.out.println("Multiplication (5 * 3): " + apply(5, 3, multiply));

        // Step 3: BiFunction that repeats a string n times (String, Integer) -> String
        // (Using Java 11+ String.repeat() method, or a loop for older versions)
        BiFunction<String, Integer, String> repeatString = (str, n) -> str.repeat(n);

        System.out.println("Repeat String ('Hi', 3): " + apply("Hi", 3, repeatString));

        // Step 4: BiFunction that adds a number to all elements in a list
        // (List<Integer>, Integer) -> List<Integer>
        BiFunction<List<Integer>, Integer, List<Integer>> addToList = (list, num) -> {
            List<Integer> result = new ArrayList<>();
            for (Integer n : list) {
                result.add(n + num);
            }
            return result;
        };

        List<Integer> numbers = Arrays.asList(1, 2, 3);
        List<Integer> addedNumbers = apply(numbers, 10, addToList);

        System.out.println("Original List: " + numbers);
        System.out.println("List + 10: " + addedNumbers);
    }

    // Step 5: A generic method to apply the BiFunction
    // T = Type of first argument
    // U = Type of second argument
    // R = Return type
    public static <T, U, R> R apply(T t, U u, BiFunction<T, U, R> function) {
        return function.apply(t, u);
    }
}
