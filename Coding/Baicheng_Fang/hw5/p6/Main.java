import java.util.*;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {

        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;

        BiFunction<String, Integer, String> copy = (s, n) -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(s);
            }
            return sb.toString();
        };

        BiFunction<List<Integer>, Integer, List<Integer>> addToAll = (numbers, num) -> {
            List<Integer> results = new ArrayList<>();
            for (Integer n : numbers) {
                results.add(n + num);
            }
            return results;
        };

        System.out.println("1 + 2 = " + BiFunctionUtil.apply(1, 2, add));

        System.out.println("1 * 2 = " + BiFunctionUtil.apply(1, 2, multiply));

        System.out.println("\"Hello\" repeats 3 times: " + BiFunctionUtil.apply("Hello", 3, copy));

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> added = BiFunctionUtil.apply(list, 100, addToAll);
        System.out.println("Original list: " + list);
        System.out.println("List after added 100 to all: " + added);
    }
}
