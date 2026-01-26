package L5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.BiFunction;

public class LambdawithBiFunction {
    public static <T, U, R> R apply(T t, U u, BiFunction<T, U, R> function) {
        return function.apply(t, u);
    }

    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> addition = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> multiplication = (a, b) -> a * b;
        BiFunction<String, Integer, String> repeats = (s, b) -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < b; i++){
                sb.append(sb);
            }
            return sb.toString();
        };
        BiFunction<List<Integer>, Integer, List<Integer>> addNumberToList = (list, value) -> {
            List<Integer> result = new ArrayList<>();
            for (Integer x : list){
                result.add(x + value);
            }
            return result;
        };
        System.out.println("Addition: " + apply(3, 5, addition));           // 8
        System.out.println("Multiplication: " + apply(4, 6, multiplication)); // 24

        System.out.println("Repeat string: " +
                apply("Hi", 3, repeats)); // HiHiHi

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        System.out.println("Add to all: " +
                apply(numbers, 10, addNumberToList)); // [11, 12, 13, 14]
    }
}
