import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserService();

        Optional<User> user1 = service.findUserByName("Bailey");
        Optional<User> user2 = service.findUserByName("Emma");

        System.out.println("Bailey found? " + user1.isPresent());
        user1.ifPresent(u -> System.out.println("Found: " + u.getName()));

        System.out.println("Emma found? " + user2.isPresent());
        user2.ifPresent(u -> System.out.println("Found: " + u.getName()));

        System.out.println("Bailey email: " + service.getUserEmail("Bailey"));
        System.out.println("David email: " + service.getUserEmail("David"));
        System.out.println("Frank email: " + service.getUserEmail("Frank"));

        System.out.println("Bailey age: " + service.getUserAge("Bailey"));
        System.out.println("David age: " + service.getUserAge("David"));
        System.out.println("Frank age: " + service.getUserAge("Frank"));

        
        Optional<String> present = Optional.of("present@gmail.com");
        Optional<String> empty = Optional.empty();

        System.out.println("=== orElse with PRESENT ===");
        present.orElse(UserService.expensiveDefaultEmail());

        System.out.println("\n=== orElseGet with PRESENT ===");
        present.orElseGet(UserService::expensiveDefaultEmail);

        System.out.println("=== orElse with EMPTY ===");
        empty.orElse(UserService.expensiveDefaultEmail());

        System.out.println("\n=== orElseGet with EMPTY ===");
        empty.orElseGet(UserService::expensiveDefaultEmail);
    }
}
