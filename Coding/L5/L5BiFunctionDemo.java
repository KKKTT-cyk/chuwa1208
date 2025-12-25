import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class L5BiFunctionDemo {
    
    // Generic apply method that applies a BiFunction
    public static <T, U, R> R apply(T t, U u, BiFunction<T, U, R> function) {
        return function.apply(t, u);
    }
    
    public static void main(String[] args) {
        System.out.println("=== L5. Lambda with BiFunction ===\n");
        
        // 1. BiFunction for addition
        BiFunction<Integer, Integer, Integer> addition = (a, b) -> a + b;
        System.out.println("Addition BiFunction:");
        System.out.println("  5 + 3 = " + apply(5, 3, addition));
        System.out.println("  10 + 20 = " + apply(10, 20, addition));
        System.out.println();
        
        // 2. BiFunction for multiplication
        BiFunction<Integer, Integer, Integer> multiplication = (a, b) -> a * b;
        System.out.println("Multiplication BiFunction:");
        System.out.println("  5 * 3 = " + apply(5, 3, multiplication));
        System.out.println("  7 * 8 = " + apply(7, 8, multiplication));
        System.out.println();
        
        // 3. BiFunction that repeats a string n times
        BiFunction<String, Integer, String> repeatString = (str, n) -> str.repeat(n);
        System.out.println("String Repeat BiFunction:");
        System.out.println("  'Hello' repeated 3 times: " + apply("Hello", 3, repeatString));
        System.out.println("  'Java ' repeated 5 times: " + apply("Java ", 5, repeatString));
        System.out.println();
        
        // 4. BiFunction that adds a number to all elements in a list
        BiFunction<List<Integer>, Integer, List<Integer>> addToList = (list, num) -> {
            List<Integer> result = new ArrayList<>();
            for (Integer element : list) {
                result.add(element + num);
            }
            return result;
        };
        
        System.out.println("Add to List BiFunction:");
        List<Integer> numbers1 = List.of(1, 2, 3, 4, 5);
        System.out.println("  Original list: " + numbers1);
        System.out.println("  Add 10 to all: " + apply(numbers1, 10, addToList));
        
        List<Integer> numbers2 = List.of(10, 20, 30);
        System.out.println("  Original list: " + numbers2);
        System.out.println("  Add 5 to all: " + apply(numbers2, 5, addToList));
        System.out.println();
        
        // Additional tests with different BiFunctions
        System.out.println("=== Additional BiFunction Tests ===\n");
        
        // Subtraction
        BiFunction<Integer, Integer, Integer> subtraction = (a, b) -> a - b;
        System.out.println("Subtraction: 20 - 7 = " + apply(20, 7, subtraction));
        
        // String concatenation
        BiFunction<String, String, String> concat = (s1, s2) -> s1 + s2;
        System.out.println("Concatenation: " + apply("Hello, ", "World!", concat));
        
        // Power function
        BiFunction<Integer, Integer, Integer> power = (base, exp) -> (int) Math.pow(base, exp);
        System.out.println("Power: 2^8 = " + apply(2, 8, power));
        
        // Max of two numbers
        BiFunction<Integer, Integer, Integer> max = (a, b) -> a > b ? a : b;
        System.out.println("Max: max(15, 23) = " + apply(15, 23, max));
    }
}
