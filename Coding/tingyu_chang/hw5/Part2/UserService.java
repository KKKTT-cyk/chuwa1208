/*
Create a UserService class with:
private Map<String, User> userDatabase - initialized in constructor with 3 sample users
public Optional<User> findUserByName(String name) - returns
Optional.ofNullable()
public String getUserEmail(String name) - uses Optional to return email or "No
email provided"
public int getUserAge(String name) - uses Optional to return age or 0
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserService {
    private Map<String, User> userDatabase;

    public UserService() {
        userDatabase = new HashMap<>();
        // Initialize with 3 sample users
        userDatabase.put("Alice", new User("Alice", "alice@example.com", 25));
        userDatabase.put("Bob", new User("Bob", null, 30)); // No email
        userDatabase.put("Charlie", new User("Charlie", "charlie@example.com", null)); // No age
    }

    // Returns Optional.ofNullable()
    public Optional<User> findUserByName(String name) {
        return Optional.ofNullable(userDatabase.get(name));
    }

    // Uses Optional to return email or "No email provided"
    public String getUserEmail(String name) {
        // We flatMap because getEmail might return null, effectively acting as an empty Optional result
        return findUserByName(name)
                .map(User::getEmail)
                .orElse("No email provided");
    }

    // Uses Optional to return age or 0
    public int getUserAge(String name) {
        return findUserByName(name)
                .map(User::getAge)
                .orElse(0);
    }

    // Helper method to demonstrate side-effects in orElse vs orElseGet
    public User createDefaultUser() {
        System.out.println("-> createDefaultUser() was called!");
        return new User("Default", "default@test.com", 0);
    }
}
