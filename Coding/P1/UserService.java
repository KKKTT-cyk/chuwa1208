import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserService {
    private Map<String, User> userDatabase;

    // Constructor - initialize with 3 sample users
    public UserService() {
        userDatabase = new HashMap<>();
        
        // Sample users: one without email, one without age
        userDatabase.put("Alice", new User("Alice", "alice@example.com", 30));
        userDatabase.put("Bob", new User("Bob", null, 25));  // No email
        userDatabase.put("Charlie", new User("Charlie", "charlie@example.com", null));  // No age
    }

    // Returns Optional.ofNullable()
    public Optional<User> findUserByName(String name) {
        return Optional.ofNullable(userDatabase.get(name));
    }

    // Uses Optional to return email or "No email provided"
    public String getUserEmail(String name) {
        return findUserByName(name)
                .flatMap(user -> Optional.ofNullable(user.getEmail()))
                .orElse("No email provided");
    }

    // Uses Optional to return age or 0
    public int getUserAge(String name) {
        return findUserByName(name)
                .flatMap(user -> Optional.ofNullable(user.getAge()))
                .orElse(0);
    }
}
