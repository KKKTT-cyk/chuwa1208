import java.util.*;
import java.util.function.Function;

public class LambdaFunction {
    public static void main(String[] args) {
        List<String> names = Arrays.asList(
                "Alice", "Bob", "Charlie", "David"
                );

        Function<String, Integer> getLength = s -> s.length();
        Function<String, String> toUpper = s -> s.toUpperCase();
        Function<Integer, String> evenOrOdd = n -> (n % 2 == 0) ? "Even" : "Odd";

        System.out.println("Get length: " + TransformUtil.transform(names, getLength));
        System.out.println("to uppder: " + TransformUtil.transform(names, toUpper));

        System.out.println("length to even/odd: " + TransformUtil.transform(names, getLength.andThen(evenOrOdd)));
        System.out.println("length to even/odd: " + TransformUtil.transform(names, evenOrOdd.compose(getLength)));
    }
}
