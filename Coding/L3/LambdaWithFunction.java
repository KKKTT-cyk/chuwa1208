package L3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


public class LambdaWithFunction {
    public static <T, R> List<R> transform(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return  result;
    }
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        Function<String, Integer> getLength = s -> s.length();
        Function<String, String> toUpper = s -> s.toUpperCase();
        Function<Integer, String> convertToString = n -> (n % 2 == 0) ? "Even" : "Odd";
        Function<String, String> lengthEvenOdd = getLength.andThen(convertToString);
        Function<String, Integer>  upperThenLength = getLength.compose(toUpper);

        System.out.println(names);
        System.out.println("Length: " + transform(names, getLength));
        System.out.println("To upper case: " + transform(names, toUpper));
        System.out.println("Convert to String: " + transform(names, lengthEvenOdd));
        System.out.println("Upper then length: " + transform(names, upperThenLength));
    }
}
