import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Create a UserService with sample data (one user without email, one without age)
        UserService service = new UserService();

        // Test findUserByName() with existing and non-existing names
        System.out.println(service.findUserByName("Alice"));
        System.out.println(service.findUserByName("David"));

        // Use isPresent() and ifPresent() to check results
        Optional<User> aliceResult = service.findUserByName("Alice");
        System.out.println(aliceResult.isPresent());
        aliceResult.ifPresent(user -> System.out.println(user));

        Optional<User> davidResult = service.findUserByName("David");
        System.out.println(davidResult.isPresent());
        davidResult.ifPresent(user -> System.out.println(user));

        // Test getUserEmail() and getUserAge() with different scenarios
        System.out.println(service.getUserEmail("Alice"));
        System.out.println(service.getUserEmail("Bob"));
        System.out.println(service.getUserEmail("David"));

        System.out.println(service.getUserAge("Alice"));
        System.out.println(service.getUserAge("Charlie"));
        System.out.println(service.getUserAge("David"));

        // Demonstrate the difference between orElse() and orElseGet() with a method that prints when called
        System.out.println("Testing orElse():");
        Optional<User> test1 = service.findUserByName("Alice");
        test1.orElse(createDefaultUser());

        System.out.println("Testing orElseGet():");
        Optional<User> test2 = service.findUserByName("Alice");
        test2.orElseGet(() -> createDefaultUser());
    }

    private static User createDefaultUser() {
        System.out.println("createDefaultUser() called");
        return new User("Default", null, null);
    }
}