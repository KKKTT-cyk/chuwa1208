import java.util.*;
import java.util.function.Predicate;

public class FilterUtil {
    
    public static List<Integer> filter(
            List<Integer> numbers,
            Predicate<Integer> predicate
            ) {
        List<Integer> result = new ArrayList<>();
        for (Integer n : numbers) {
            if (predicate.test(n)) {
                result.add(n);
            }
        }
        return result;
            }
}
