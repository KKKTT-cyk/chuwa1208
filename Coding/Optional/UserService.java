package Coding.Optional;

import java.util.*;

public class UserService {
    private Map<String, User> userDatabase;

    public UserService() {
        userDatabase = new HashMap<>();
        // Initialize with 3 sample users
        userDatabase.put("Alice", new User("Alice", "alice@example.com", 30));
        userDatabase.put("Bob", new User("Bob", null, 25)); // no email
        userDatabase.put("Charlie", new User("Charlie", "charlie@example.com", null)); // no age
    }

    // Return Optional<User>
    public Optional<User> findUserByName(String name) {
        return Optional.ofNullable(userDatabase.get(name));
    }

    // Return email or "No email provided"
    public String getUserEmail(String name) {
        return findUserByName(name)
                .map(User::getEmail) // Optional<String>
                .orElse("No email provided");
    }

    // Return age or 0
    public int getUserAge(String name) {
        return findUserByName(name)
                .map(User::getAge) // Optional<Integer>
                .orElse(0); // Unboxing null safely returns 0 here
    }

    // For demonstrating orElse vs orElseGet
    public String getEmailWithOrElse(String name) {
        return findUserByName(name)
                .map(User::getEmail)
                .orElse(expensiveDefaultEmail());
    }

    public String getEmailWithOrElseGet(String name) {
        return findUserByName(name)
                .map(User::getEmail)
                .orElseGet(this::expensiveDefaultEmail);
    }

    private String expensiveDefaultEmail() {
        System.out.println("Computing expensive default email...");
        return "default@example.com";
    }
}
