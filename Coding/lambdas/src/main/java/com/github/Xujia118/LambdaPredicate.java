package com.github.Xujia118;

import java.util.List;
import java.util.function.Predicate;

public class LambdaPredicate {

    private static List<Integer> filter(List<Integer> numbers, Predicate<Integer> predicate) {
        return numbers.stream().filter(predicate).toList();
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("\nEven numbers");
        Predicate<Integer> isEven = n -> n % 2 == 0;

        System.out.println("\nGreater than five");
        Predicate<Integer> greaterThanFive = n -> n > 5;

        System.out.println("\nDivisible by three");
        Predicate<Integer> divisibleByThree = n -> n % 3 == 0;

        System.out.println("\nEven and greater than five");
        Predicate<Integer> evenAndGreaterThanFive = isEven.and(greaterThanFive);
        System.out.println(filter(numbers, evenAndGreaterThanFive));

        System.out.println("\nLess than three or greater than seven");
        Predicate<Integer> lessThanThree = n -> n < 3;
        Predicate<Integer> greaterThanSeven = n -> n > 7;
        Predicate<Integer> lessThanThreeOrGreaterThanSeven = lessThanThree.or(greaterThanSeven);
        System.out.println(filter(numbers, lessThanThreeOrGreaterThanSeven));

        System.out.println("\nOdd numbers");
        Predicate<Integer> oddNumbers = isEven.negate();
        System.out.println(filter(numbers, oddNumbers));
    }
}
