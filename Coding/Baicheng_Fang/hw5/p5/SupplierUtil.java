import java.util.function.Supplier;

public class SupplierUtil {
    public static <T> T getOrDefault(T value, Supplier<T> defaultSupplier) {
        if (value != null) {
            return value;
        }
        return defaultSupplier.get();
    }
}
