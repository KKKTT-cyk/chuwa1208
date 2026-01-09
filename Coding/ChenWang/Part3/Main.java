import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    // L2: filter method
    public static List<Integer> filter(List<Integer> numbers, Predicate<Integer> predicate) {
        List<Integer> result = new ArrayList<>();
        for (Integer n : numbers) {
            if (predicate.test(n)) {
                result.add(n);
            }
        }
        return result;
    }

    // L3: generic transform method
    public static <T, R> List<R> transform(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }

    // L4: lazy default method
    public static <T> T getOrDefault(T value, Supplier<T> defaultSupplier) {
        if (value != null) return value;
        return defaultSupplier.get();
    }

    // L5: generic apply method
    public static <T, U, R> R apply(T t, U u, BiFunction<T, U, R> function) {
        return function.apply(t, u);
    }

    public static void main(String[] args) throws InterruptedException {

        // L1. Basic Lambda with Consumer
        System.out.println("L1: Consumer");

        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange", "Mango", "Grape");

        System.out.println("-- for-loop --");
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println(fruits.get(i));
        }

        System.out.println("-- forEach + anonymous Consumer --");
        fruits.forEach(new Consumer<String>() {
            @Override
            public void accept(String fruit) {
                System.out.println(fruit);
            }
        });

        System.out.println("-- forEach + lambda --");
        fruits.forEach(fruit -> System.out.println(fruit));

        System.out.println("-- forEach + method reference --");
        fruits.forEach(System.out::println);

        System.out.println("-- uppercase Consumer --");
        Consumer<String> printUppercase = fruit -> System.out.println(fruit.toUpperCase());
        fruits.forEach(printUppercase);

        // L2. Lambda with Predicate
        System.out.println("\n L2: Predicate ");

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        System.out.println("Even: " + filter(numbers, n -> n % 2 == 0));

        System.out.println("> 5: " + filter(numbers, n -> n > 5));

        System.out.println("Divisible by 3: " + filter(numbers, n -> n % 3 == 0));

        Predicate<Integer> even = n -> n % 2 == 0;
        Predicate<Integer> greaterThan5 = n -> n > 5;
        System.out.println("Even AND >5: " + filter(numbers, even.and(greaterThan5)));

        Predicate<Integer> lessThan3 = n -> n < 3;
        Predicate<Integer> greaterThan7 = n -> n > 7;
        System.out.println("<3 OR >7: " + filter(numbers, lessThan3.or(greaterThan7)));

        System.out.println("Odd (negate even): " + filter(numbers, even.negate()));

        // L3. Lambda with Function
        System.out.println("\nL3: Function");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        Function<String, Integer> lengthFn = s -> s.length();
        Function<String, String> upperFn = s -> s.toUpperCase();
        Function<Integer, String> evenOddFn = n -> (n % 2 == 0) ? "Even" : "Odd";

        System.out.println("Lengths: " + transform(names, lengthFn));
        System.out.println("Uppercase: " + transform(names, upperFn));

        Function<String, String> lengthThenEvenOdd = lengthFn.andThen(evenOddFn);
        System.out.println("Length -> Even/Odd (andThen): " + transform(names, lengthThenEvenOdd));

        Function<String, Integer> upperThenLength = lengthFn.compose(upperFn);
        System.out.println("Uppercase -> Length (compose): " + transform(names, upperThenLength));

        // L4. Lambda with Supplier
        System.out.println("\n L4: Supplier ");

        Supplier<Double> random0to100 = () -> new Random().nextDouble() * 100.0;
        System.out.println("Random (0-100): " + random0to100.get());

        Supplier<List<Integer>> list1to10Supplier = () -> {
            List<Integer> lst = new ArrayList<>();
            for (int i = 1; i <= 10; i++) lst.add(i);
            return lst;
        };
        System.out.println("New list 1-10: " + list1to10Supplier.get());

        System.out.println("-- getOrDefault with null --");
        String v1 = getOrDefault(null, () -> {
            System.out.println("Generating default value");
            return "DEFAULT";
        });
        System.out.println("Result: " + v1);

        System.out.println("-- getOrDefault with non-null --");
        String v2 = getOrDefault("NOT_NULL", () -> {
            System.out.println("Generating default value (should NOT print)");
            return "DEFAULT";
        });
        System.out.println("Result: " + v2);

        Supplier<String> expensiveDbQuery = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "Interrupted";
            }
            return "Data from DB";
        };

        System.out.println("-- expensive supplier demo --");
        long start = System.currentTimeMillis();
        String db1 = getOrDefault(null, expensiveDbQuery);
        long end = System.currentTimeMillis();
        System.out.println("db1: " + db1 + " (took ~" + (end - start) + "ms)");

        start = System.currentTimeMillis();
        String db2 = getOrDefault("Cached value", expensiveDbQuery);
        end = System.currentTimeMillis();
        System.out.println("db2: " + db2 + " (took ~" + (end - start) + "ms)");

        // L5. Lambda with BiFunction
        System.out.println("\n L5: BiFunction ");

        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;

        BiFunction<String, Integer, String> repeat = (s, n) -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) sb.append(s);
            return sb.toString();
        };

        BiFunction<List<Integer>, Integer, List<Integer>> addToAll = (lst, x) -> {
            List<Integer> out = new ArrayList<>();
            for (Integer n : lst) out.add(n + x);
            return out;
        };

        System.out.println("apply add(3,4): " + apply(3, 4, add));
        System.out.println("apply multiply(3,4): " + apply(3, 4, multiply));
        System.out.println("apply repeat(\"Hi\", 3): " + apply("Hi", 3, repeat));
        System.out.println("apply addToAll([1,2,3], 10): " + apply(Arrays.asList(1,2,3), 10, addToAll));
    }
}
