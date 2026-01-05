package Part3.L2;

import java.util.*;
import java.util.function.Predicate;

public class LambdawithPredicate {
    public static void main(String[] args) {
        // Step 1: Create a List<Integer> with numbers: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        List<Integer> numbers = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        );

        // Step 3-1: Filter even numbers
        System.out.println("Even numbers:");
        System.out.println(filter(numbers, n -> n % 2 == 0));

        // Step 3-2: Filter numbers greater than 5
        System.out.println("\nNumbers greater than 5:");
        System.out.println(filter(numbers, n -> n > 5));

        // Step 3-3: Filter numbers divisible by 3
        System.out.println("\nNumbers divisible by 3:");
        System.out.println(filter(numbers, n -> n % 3 == 0));

        // Step 4: Combine two Predicates using and() method:
        // even numbers AND greater than 5
        Predicate<Integer> even = n -> n % 2 == 0;
        Predicate<Integer> greaterThan5 = n -> n > 5;

        System.out.println("\nEven numbers AND greater than 5:");
        System.out.println(filter(numbers, even.and(greaterThan5)));

        // Step 5: Combine two Predicates using or() method:
        // less than 3 OR greater than 7
        Predicate<Integer> lessThan3 = n -> n < 3;
        Predicate<Integer> greaterThan7 = n -> n > 7;

        System.out.println("\nLess than 3 OR greater than 7:");
        System.out.println(filter(numbers, lessThan3.or(greaterThan7)));

        // Step 6: Use negate() to get odd numbers
        System.out.println("\nOdd numbers:");
        System.out.println(filter(numbers, even.negate()));
    }

    // Step 2: Write a method List<Integer> filter(List<Integer> numbers
    // Predicate<Integer> predicate) that filters the list
    public static List<Integer> filter(List<Integer> numbers, Predicate<Integer> predicate){
        List<Integer> result = new ArrayList<>();
        for (Integer n : numbers) {
            if (predicate.test(n)) {
                result.add(n);
            }
        }
        return result;
    }
}
