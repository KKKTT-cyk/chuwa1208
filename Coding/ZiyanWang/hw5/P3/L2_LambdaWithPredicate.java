import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    // This method filters a list of integers using a Predicate
    public static List<Integer> filter(List<Integer> numbers,
                                       Predicate<Integer> predicate) {

        List<Integer> result = new ArrayList<>();

        for (Integer number : numbers) {
            if (predicate.test(number)) {
                result.add(number);
            }
        }

        return result;
    }

    public static void main(String[] args) {

        // Create a List<Integer> with numbers 1 to 10
        List<Integer> numbers = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        );

        // Filter even numbers
        List<Integer> evenNumbers =
                filter(numbers, n -> n % 2 == 0);
        System.out.println(evenNumbers);

        // Filter numbers greater than 5
        List<Integer> greaterThanFive =
                filter(numbers, n -> n > 5);
        System.out.println(greaterThanFive);

        // Filter numbers divisible by 3
        List<Integer> divisibleByThree =
                filter(numbers, n -> n % 3 == 0);
        System.out.println(divisibleByThree);

        // Combine two Predicates using and(): even AND greater than 5
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> greaterThanFivePredicate = n -> n > 5;

        List<Integer> evenAndGreaterThanFive =
                filter(numbers, isEven.and(greaterThanFivePredicate));
        System.out.println(evenAndGreaterThanFive);

        // Combine two Predicates using or(): less than 3 OR greater than 7
        Predicate<Integer> lessThanThree = n -> n < 3;
        Predicate<Integer> greaterThanSeven = n -> n > 7;

        List<Integer> lessThanThreeOrGreaterThanSeven =
                filter(numbers, lessThanThree.or(greaterThanSeven));
        System.out.println(lessThanThreeOrGreaterThanSeven);

        // Use negate() to get odd numbers
        List<Integer> oddNumbers =
                filter(numbers, isEven.negate());
        System.out.println(oddNumbers);
    }
}
