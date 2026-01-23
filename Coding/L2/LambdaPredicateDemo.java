package L2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LambdaPredicateDemo {

    public static List<Integer> filter(List<Integer> numbers, Predicate<Integer> predicate) {
        List<Integer> result = new ArrayList<>();
        for (Integer n : numbers) {
            if (predicate.test(n)){
                result.add(n);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("Even numbers " + LambdaPredicateDemo.filter(numbers, n -> n % 2 == 0));
        System.out.println("Greater than 5 numbers: " + LambdaPredicateDemo.filter(numbers, n -> n > 5));
        System.out.println("Divisible by 3 number: " + LambdaPredicateDemo.filter(numbers, n -> n % 3 == 0));

        // Combine two predications using and()
        Predicate<Integer> isEven = n -> n % 2 ==0;
        Predicate<Integer> isGreaterFive = n -> n > 5;

        Predicate<Integer> combinedFiveAndEven = isEven.and(isGreaterFive);
        System.out.println("combined even and greater than 5: " + LambdaPredicateDemo.filter(numbers, combinedFiveAndEven));

        // Combine two predications using or()
        Predicate<Integer> lessThree = n -> n < 3;
        Predicate<Integer> greaterSeven = n -> n > 7;
        Predicate<Integer> lessOrGreater = lessThree.or(greaterSeven);

        System.out.println("less than 3 or greater than 7 numbers: " + LambdaPredicateDemo.filter(numbers, lessOrGreater));

        Predicate<Integer> isOdd = isEven.negate();
        System.out.println("Odd numbers: " + LambdaPredicateDemo.filter(numbers, isOdd));

    }
}
