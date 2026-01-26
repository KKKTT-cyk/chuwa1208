import java.util.*;
import java.util.function.Function;

public class TransformUtil {
    public static <T, R> List<R> transform(
            List<T> list,
            Function<T, R> function
            ) {
        List<R> result = new ArrayList<>();

        for (T element: list) {
            result.add(function.apply(element));
        }

        return result;
            }
}
