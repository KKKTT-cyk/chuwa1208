package P1;

import java.util.Optional;

/**
 * Create a UserService with sample data (one user without email, one without age)
 * Test findUserByName() with existing and non-existing names
 * Use isPresent() and ifPresent() to check results
 * Test getUserEmail() and getUserAge() with different scenarios
 * Demonstrate the difference between orElse() and orElseGet() with a method that prints when called
 */
public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();

        Optional<User> u1 = userService.findUserByName("Alice");
        Optional<User> u2 = userService.findUserByName("NO Name");

//        Optional<User> u3 = userService.findUserByName(userService.getUserEmail("Bob"));

        System.out.println("======.isPresent() Test======");

        System.out.println("Alice exists? " + u1.isPresent());
        System.out.println("No Name exists? " + u2.isPresent());

        System.out.println("\n ====== ifPresent() Test======");
        u1.ifPresent(user -> System.out.println("Found user: " + user.getName()));
        u2.ifPresent(usr -> System.out.println("xxxxxx"));

        System.out.println("\n ====== getUserEmail and get UserAge======");
        System.out.println("Alice email: " + userService.getUserEmail("Alice"));
        System.out.println("Bob email: " + userService.getUserEmail("Bob"));
        System.out.println("Nobody email: " + userService.getUserEmail("Nobody"));// user not exist -> default text

        System.out.println("Alice age: " + userService.getUserAge("Bob"));
        System.out.println("Cathy age: " + userService.getUserAge("Cathy"));
        System.out.println("Nobody age: " + userService.getUserAge("Nobody"));// user not exist -> default text
        // Demonstrate the difference between orElse and orElseGet

        String a1 = userService.findUserByName("Bob")
                        .map(User::getEmail)
                .orElse(UserService.expensiveDefaultEmail());
        System.out.println("Bob orElse result: " + a1);

        String a2 = userService.findUserByName("Bob")
                .map(User :: getEmail)
                .orElseGet(UserService::expensiveDefaultEmail);

        System.out.println("Bob orElseGet result: " + a2);

        System.out.println("\n ==== Alice====");
        String a3 = userService.findUserByName("Alice")
                .map(User::getEmail)
                .orElse(UserService.expensiveDefaultEmail());
        System.out.println("Alice orElse result: " + a3);

        String a4 = userService.findUserByName("Alice")
                .map(User :: getEmail)
                .orElseGet(UserService::expensiveDefaultEmail);

        System.out.println("Alice orElseGet result: " + a4);
    }
}
