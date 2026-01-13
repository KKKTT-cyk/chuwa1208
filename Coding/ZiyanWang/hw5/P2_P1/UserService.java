import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserService {
    private final Map<String, User> userDatabase;

    public UserService() {
        this.userDatabase = new HashMap<>();
        // 3 sample users
        userDatabase.put("Jan", new User("Jan", "jan@gmail.com", 20));
        userDatabase.put("Andy", new User("Andy", "andy@gmail.com", 25));
        userDatabase.put("May", new User("May", "may@gmail.com", 30));
    }

    public Optional<User> findUserByName(String name) {
        return Optional.ofNullable(userDatabase.get(name));
    }

    public String getUserEmail(String name) {
        return findUserByName(name)
                .map(User::getEmail)
                .filter(email -> email != null && !email.isBlank())
                .orElse("No email provided");
    }

    public int getUserAge(String name) {
        return findUserByName(name)
                .map(User::getAge)
                .orElse(0);
    }
}