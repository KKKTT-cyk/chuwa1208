import java.util.Optional;

public class Main {

    // This method is used to demonstrate the difference
    // between orElse() and orElseGet().
    // It prints a message when called.
    private static String defaultEmail() {
        System.out.println("defaultEmail() method is called");
        return "default@example.com";
    }

    public static void main(String[] args) {

        // Create UserService with sample data
        // Jan: normal user
        // Andy: user without email
        // May: user without age
        UserService userService = new UserService();

        // Test findUserByName() with existing and non-existing users
        Optional<User> jan = userService.findUserByName("Jan");
        Optional<User> unknown = userService.findUserByName("Unknown");

        // Use isPresent() to check existence
        System.out.println("Is Jan present? " + jan.isPresent());
        System.out.println("Is Unknown present? " + unknown.isPresent());

        // Use ifPresent() to safely access the value
        jan.ifPresent(user ->
                System.out.println("Found user: " + user.getName())
        );

        unknown.ifPresent(user ->
                System.out.println("This will not be printed")
        );

        // Test getUserEmail() with different scenarios
        System.out.println(userService.getUserEmail("Jan"));
        System.out.println(userService.getUserEmail("Andy"));
        System.out.println(userService.getUserEmail("Unknown"));

        // Test getUserAge() with different scenarios
        System.out.println(userService.getUserAge("Jan"));
        System.out.println(userService.getUserAge("May"));
        System.out.println(userService.getUserAge("Unknown"));

        // Demonstrate orElse() vs orElseGet()
        Optional<String> present = Optional.of("real@example.com");
        Optional<String> empty = Optional.empty();

        // orElse() always evaluates the argument
        String result1 = present.orElse(defaultEmail());
        System.out.println(result1);

        // orElseGet() evaluates the supplier lazily
        String result2 = present.orElseGet(Main::defaultEmail);
        System.out.println(result2);

        // Both methods call defaultEmail() when Optional is empty
        String result3 = empty.orElse(defaultEmail());
        System.out.println(result3);

        String result4 = empty.orElseGet(Main::defaultEmail);
        System.out.println(result4);
    }
}

