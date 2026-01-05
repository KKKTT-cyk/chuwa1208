package Coding.Optional;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // Test findUserByName with existing user
        Optional<User> user1 = userService.findUserByName("Alice");
        System.out.println("Alice isPresent: " + user1.isPresent());

        // Using ifPresent()
        userService.findUserByName("Bob").ifPresent(user -> System.out.println("Bob's email: " + user.getEmail()));

        // Test findUserByName with non-existing user
        Optional<User> user2 = userService.findUserByName("David");
        System.out.println("David isPresent: " + user2.isPresent());

        // Using ifPresent() on empty Optional - will not print anything
        user2.ifPresent(user -> System.out.println("David's email: " + user.getEmail()));

        // Test getUserEmail with different scenarios
        System.out.println("Alice's email: " + userService.getUserEmail("Alice")); // alice@example.com
        System.out.println("Bob's email: " + userService.getUserEmail("Bob")); // No email provided
        System.out.println("David's email: " + userService.getUserEmail("David")); // No email provided

        // Test getUserAge with different scenarios
        System.out.println("Alice's age: " + userService.getUserAge("Alice")); // 30
        System.out.println("Charlie’s age: " + userService.getUserAge("Charlie")); // 0
        System.out.println("David's age: " + userService.getUserAge("David")); // 0

        // Demonstrate orElse vs orElseGet difference
        System.out.println("\nTesting orElse:");
        System.out.println(userService.getEmailWithOrElse("Alice")); // email present, but default is computed anyway

        System.out.println("\nTesting orElseGet:");
        System.out.println(userService.getEmailWithOrElseGet("Alice")); // email present, default NOT computed

        System.out.println("\nTesting orElse with missing user:");
        System.out.println(userService.getEmailWithOrElse("David")); // default computed

        System.out.println("\nTesting orElseGet with missing user:");
        System.out.println(userService.getEmailWithOrElseGet("David")); // default computed

    }
}
