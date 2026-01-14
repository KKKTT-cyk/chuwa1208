import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
                );

        // Even numbers
        List<Integer> evens = FilterUtil.filter(numbers, n -> n % 2 == 0);
        System.out.println("Even numbers: " + evens);

        // Greater than 5
        List<Integer> greaterThanFive = FilterUtil.filter(numbers, n -> n > 5);
        System.out.println("Greater than 5 numbers: " + greaterThanFive);

        // Divisible by 3
        List<Integer> disisibleByThree = FilterUtil.filter(numbers, n -> n % 3 == 0);
        System.out.println("Divisible by 3: " + disisibleByThree);

        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isGreaterThan5 = n -> n > 5;
        Predicate<Integer> isLessThan3 = n -> n < 3;
        Predicate<Integer> isGreaterThan7 = n -> n > 7;

        System.out.println("Even AND Greater than 5: " + FilterUtil.filter(numbers, isEven.and(isGreaterThan5)));
        System.out.println("Less than 3 OR Greater than 7: " + FilterUtil.filter(numbers, isLessThan3.or(isGreaterThan7)));

        System.out.println("Odd: " + FilterUtil.filter(numbers, isEven.negate()));
    }
}
