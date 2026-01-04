import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserService {
    private final Map<String, User> userDatabase;

    public UserService() {
        this.userDatabase = new HashMap<>();
        userDatabase.put("Alice", new User("Alice", "alice@example.com", 28));
        userDatabase.put("Bob", new User("Bob", null, 35));          // no email
        userDatabase.put("Cindy", new User("Cindy", "cindy@x.com", null)); // no age
    }

    public Optional<User> findUserByName(String name) {
        return Optional.ofNullable(userDatabase.get(name));
    }

    public String getUserEmail(String name) {
        return findUserByName(name)
                .map(User::getEmail)
                .orElse("No email provided");
    }

    public int getUserAge(String name) {
        return findUserByName(name)
                .map(User::getAge)
                .orElse(0);
    }

    public static String expensiveDefaultEmail() {
        System.out.println("expensiveDefaultEmail() called!");
        return "default@example.com";
    }
}
