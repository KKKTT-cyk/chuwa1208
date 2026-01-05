import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.*;

//Create a UserService with sample data (one user without email, one without age)
//Test findUserByName() with existing and non-existing names
//Use isPresent() and ifPresent() to check results
//Test getUserEmail() and getUserAge() with different scenarios
//Demonstrate the difference between orElse() and orElseGet() with a method that prints
//when called
public class Main {
    public static List<Integer> filter(List<Integer> numbers, Predicate<Integer> predicate){
        List<Integer> result = new ArrayList<>();
        for(Integer n : numbers){
            if(predicate.test(n)){
                result.add(n);
            }
        }
        return result;
    }
    public static <T, R> List<R> transform(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }

    public static <T> T getOrDefault(T value, Supplier<T> defaultSupplier){
        if(value != null){
            return value;
        }
        return  defaultSupplier.get();
    }

    public static <T,U,R> R apply(T t, U u, BiFunction<T,U,R> function){
        return function.apply(t,u);
    }

    public static void main(String[] args) {
        //P1
        UserService userService = new UserService();
        User u1 = new User("test1",null,12);
        User u2 = new User("test2","email@as.com", null);
        userService.addUser("test1",u1);
        userService.addUser("test2",u2);

        Optional<User> uu = userService.findUserByName("test2");
        System.out.println(uu);
        Optional<User> uuu = userService.findUserByName("uuu");
        System.out.println(uuu);

        if(uu.isPresent()){
            System.out.println("uu is Present");
        }else System.out.println("uu is not Present");
        if(uuu.isPresent()){
            System.out.println("uuu is Present");
        }else System.out.println("uuu is not Present");

        uu.ifPresent(user -> System.out.println("Found: " + user.getName()));

        System.out.println("test get age:" + userService.getUserAge("test1"));
        System.out.println("test get age:" + userService.getUserAge("test2"));
        System.out.println("test get email:" + userService.getUserEmail("test1"));
        System.out.println("test get email:" + userService.getUserEmail("test2"));

        User testElse = uu.orElse(u1);
        User testElseGet = uu.orElseGet(()->u1);

        //L1
        List<String> fruits = Arrays.asList("apple", "banana", "orange", "grape", "mango");
        for(String n :fruits){
            System.out.println("traditional print:" + n);
        }
        fruits.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("forEach" + s);
            }
        });
        fruits.forEach(n-> System.out.println(n));
        fruits.forEach(System.out::println);
        Consumer<String> upper = n-> System.out.println(n.toUpperCase());
        fruits.forEach(upper);
        //L2
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Predicate<Integer> isEven = n -> n % 2 ==0;
        Predicate<Integer> greater5 = n -> n > 5;
        Predicate<Integer> divisible3 = n-> n % 3 == 0;
        Predicate<Integer> less3 = n-> n < 3;
        Predicate<Integer> greater7 = n-> n > 7;
        System.out.println(filter(numbers,isEven));
        System.out.println(filter(numbers,isEven.and(greater5)));
        System.out.println(filter(numbers,less3.or(greater7)));
        System.out.println(filter(numbers,isEven.negate()));
        //L3
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        Function<String,Integer> leng = String::length;
        Function<String,String> upperCase = String::toUpperCase;
        Function<Integer,String> evenOrOdd = n-> (n % 2 ==0)? "Even" : "Odd";
        Function<String,String> lengThenEven = leng.andThen(evenOrOdd);
        System.out.println("length: " + transform(names,leng));
        System.out.println("first get length, then convert to \"Even\"/\"Odd\":" + transform(names,lengThenEven));
        Function<String, String> lengthParityCompose = evenOrOdd.compose(leng);
        List<String> lengthParity2 = transform(names, lengthParityCompose);
        System.out.println("Length -> Even/Odd (compose): " + lengthParity2);
        //L4
        Supplier<Double> random = ()->Math.random()*100;
        Supplier<List<Integer>> alist = ()-> {
            List<Integer> list= new ArrayList<>();
            for(int i = 1; i <= 10; i++) list.add(i);
            return list;
        };
        System.out.println("Test supplier method:");
        System.out.println(getOrDefault(null,random));
        System.out.println(getOrDefault("VALUE",()->{
            return "DEFAULT";
        }));
        Supplier<String> expensiveDBQ = () -> {
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();;
            }
            return "Data from DB";
        };
        //L5
        BiFunction<Integer,Integer,Integer> addition = (a,b) -> a + b;
        BiFunction<Integer,Integer,Integer> multiplication = (a,b) -> a * b;
        BiFunction<String,Integer,String> repeat = (s,n) -> {
            if(s == null) return null;
            StringBuilder str = new StringBuilder(s.length() *n);
            for(int i = 0; i < n; i++) str.append(s);
            return str.toString();
        };
        BiFunction<List<Integer>, Integer, List<Integer>> addNum = (list, num) -> {
            if(list == null) return null;
            List<Integer> result = new ArrayList<>(list.size());
            for(Integer i : list){
                result.add(i == null ? null : i + num);
            }
            return result;
        };

        System.out.println(apply(3,5,addition));
        System.out.println(apply(3,5,multiplication));
        System.out.println(apply("apple",5,repeat));
        List<Integer> nums = Arrays.asList(1,2,3,4,5);
        System.out.println(apply(nums,10,addNum));

    }
}

