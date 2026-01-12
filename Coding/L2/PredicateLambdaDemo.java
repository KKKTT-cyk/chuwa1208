import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateLambdaDemo {
    public static List<Integer> filter(List<Integer> numbers, Predicate<Integer> predicate) {
        List<Integer> result = new ArrayList<>();
        for (Integer num : numbers) {
            if (predicate.test(num)) {
                result.add(num);
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        // Create a List<Integer> with numbers: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        System.out.println("Original list: " + numbers);
        System.out.println();
        
        // 1. Filter even numbers
        Predicate<Integer> isEven = n -> n % 2 == 0;
        List<Integer> evenNumbers = filter(numbers, isEven);
        System.out.println("Even numbers: " + evenNumbers);
        
        // 2. Filter numbers greater than 5
        Predicate<Integer> greaterThanFive = n -> n > 5;
        List<Integer> numbersGreaterThan5 = filter(numbers, greaterThanFive);
        System.out.println("Numbers greater than 5: " + numbersGreaterThan5);
        
        // 3. Filter numbers divisible by 3
        Predicate<Integer> divisibleByThree = n -> n % 3 == 0;
        List<Integer> divisibleBy3 = filter(numbers, divisibleByThree);
        System.out.println("Numbers divisible by 3: " + divisibleBy3);
        
        // 4. Combine two Predicates using and(): even numbers AND greater than 5
        Predicate<Integer> evenAndGreaterThan5 = isEven.and(greaterThanFive);
        List<Integer> evenAndGreater5 = filter(numbers, evenAndGreaterThan5);
        System.out.println("Even numbers AND greater than 5: " + evenAndGreater5);
        
        // 5. Combine two Predicates using or(): less than 3 OR greater than 7
        Predicate<Integer> lessThanThree = n -> n < 3;
        Predicate<Integer> greaterThanSeven = n -> n > 7;
        Predicate<Integer> lessThan3OrGreaterThan7 = lessThanThree.or(greaterThanSeven);
        List<Integer> combined = filter(numbers, lessThan3OrGreaterThan7);
        System.out.println("Numbers less than 3 OR greater than 7: " + combined);
        
        // 6. Use negate() to get odd numbers
        Predicate<Integer> isOdd = isEven.negate();
        List<Integer> oddNumbers = filter(numbers, isOdd);
        System.out.println("Odd numbers (using negate): " + oddNumbers);
    }
}
