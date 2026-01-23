package P1;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Create a UserService class with:
     * private Map<String, User> userDatabase - initialized in constructor with 3 sample users
     * public Optional<User> findUserByName(String name) - returns
     * Optional.ofNullable()
     * public String getUserEmail(String name) - uses Optional to return email or "No email provided"
     * public int getUserAge(String name) - uses Optional to return age or 0
 */
public class UserService {
    private Map<String, User> userDatabase;

    // Constructor
    public UserService() {
        userDatabase = new HashMap<>();

        // three examples
        userDatabase.put("Alice", new User("Alice", "123@gmail.com", 12));
        userDatabase.put("Bob", new User("Bob", null, 15));
        userDatabase.put("Cathy", new User("Cathy", "789@gmail.com", null));
    }
    // return Optional.ofNullable() since name can be null
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
    // demonstrate orElse vs orElseGet
    public static String expensiveDefaultEmail() {
        System.out.println("[expensiveDefaultEmail()] 我被调用了！");
        return "default@email.com";
    }
}
