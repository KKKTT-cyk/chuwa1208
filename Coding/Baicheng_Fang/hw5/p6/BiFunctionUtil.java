import java.util.function.BiFunction;

public class BiFunctionUtil {
    public static <T, U, R> R apply(T t, U u, BiFunction<T, U, R> function) {
        return function.apply(t, u);
    }
}
