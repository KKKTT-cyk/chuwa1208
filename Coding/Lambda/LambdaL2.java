package Coding.Lambda;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaL2 {
    public static List<Integer> filter(List<Integer> numbers, Predicate<Integer> predicate) {
        return numbers.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Filter even numbers
        List<Integer> even = filter(numbers, n -> n % 2 == 0);
        System.out.println("Even numbers: " + even);

        // Filter numbers greater than 5
        List<Integer> greaterThan5 = filter(numbers, n -> n > 5);
        System.out.println("Greater than 5: " + greaterThan5);

        // Filter numbers divisible by 3
        List<Integer> divisibleBy3 = filter(numbers, n -> n % 3 == 0);
        System.out.println("Divisible by 3: " + divisibleBy3);

        // Combine with and(): even AND greater than 5
        Predicate<Integer> evenAndGreaterThan5 = n -> n % 2 == 0;
        evenAndGreaterThan5 = evenAndGreaterThan5.and(n -> n > 5);
        List<Integer> combinedAnd = filter(numbers, evenAndGreaterThan5);
        System.out.println("Even AND > 5: " + combinedAnd);

        // Combine with or(): less than 3 OR greater than 7
        Predicate<Integer> lessThan3 = n -> n < 3;
        Predicate<Integer> greaterThan7 = n -> n > 7;
        Predicate<Integer> combinedOr = lessThan3.or(greaterThan7);
        List<Integer> combinedOrList = filter(numbers, combinedOr);
        System.out.println("Less than 3 OR greater than 7: " + combinedOrList);

        // Use negate() to get odd numbers
        Predicate<Integer> odd = n -> n % 2 == 0; // even predicate
        Predicate<Integer> oddNegate = odd.negate();
        List<Integer> oddNumbers = filter(numbers, oddNegate);
        System.out.println("Odd numbers: " + oddNumbers);
    }
}