/*
Practice using Predicate functional interface with lambdas.
Create a List<Integer> with numbers: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
Write a method List<Integer> filter(List<Integer> numbers, Predicate<Integer>
predicate) that filters the list
In Main, use this filter method with different lambda predicates:
Filter even numbers
Filter numbers greater than 5
Filter numbers divisible by 3
Combine two Predicates using and() method: even numbers AND greater than 5
Combine two Predicates using or() method: less than 3 OR greater than 7
Use negate() to get odd numbers
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LambdaPredicatePractice {

    public static void main(String[] args) {
        // Step 1: Create a List with numbers 1 to 10
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Original List: " + numbers);

        // Step 3a: Filter even numbers
        // Lambda: Returns true if number % 2 is 0
        List<Integer> evens = filter(numbers, n -> n % 2 == 0);
        System.out.println("Even Numbers: " + evens);

        // Step 3b: Filter numbers greater than 5
        // Lambda: Returns true if number > 5
        List<Integer> greaterThan5 = filter(numbers, n -> n > 5);
        System.out.println("Numbers > 5: " + greaterThan5);

        // Step 3c: Filter numbers divisible by 3
        // Lambda: Returns true if number % 3 is 0
        List<Integer> divisibleBy3 = filter(numbers, n -> n % 3 == 0);
        System.out.println("Divisible by 3: " + divisibleBy3);

        // Step 3d: Combine two Predicates using and() (Even AND > 5)
        // We define the predicates first to call .and() on them
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isGt5 = n -> n > 5;

        List<Integer> evenAndGt5 = filter(numbers, isEven.and(isGt5));
        System.out.println("Even AND > 5: " + evenAndGt5);

        // Step 3e: Combine two Predicates using or() (< 3 OR > 7)
        Predicate<Integer> isLessThan3 = n -> n < 3;
        Predicate<Integer> isGt7 = n -> n > 7;

        List<Integer> lessThan3OrGt7 = filter(numbers, isLessThan3.or(isGt7));
        System.out.println("< 3 OR > 7: " + lessThan3OrGt7);

        // Step 3f: Use negate() to get odd numbers
        // We take the existing 'isEven' predicate and flip it using .negate()
        List<Integer> odds = filter(numbers, isEven.negate());
        System.out.println("Odd Numbers (negate even): " + odds);
    }

    // Step 2: Write a method to filter the list based on a Predicate
    // This generic method iterates the list and adds items that pass the test to a new list.
    public static List<Integer> filter(List<Integer> numbers, Predicate<Integer> predicate) {
        List<Integer> result = new ArrayList<>();
        for (Integer number : numbers) {
            // predicate.test(number) executes the lambda logic
            if (predicate.test(number)) {
                result.add(number);
            }
        }
        return result;
    }
}

