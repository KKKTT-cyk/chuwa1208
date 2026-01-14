package P1_Optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserService {
    private Map<String, User> userDatabase;

    public UserService() {
        userDatabase = new HashMap<>();
        userDatabase.put("SampleUser1", new User());
        userDatabase.put("SampleUser2", new User());
        userDatabase.put("SampleUser3", new User());
    }

    public void addNewUser(User user) {
        // assume name is unique
        userDatabase.put(user.getName(), user);
    }

    public Optional<User> findUserByName(String name) {
        return Optional.ofNullable(userDatabase.get(name));
    }

    public String getUserEmail(String name) {
        return findUserByName(name)
                .flatMap(user -> Optional.ofNullable(user.getEmail()))
                .orElse("No email provided");
    }

    public int getUserAge(String name) {
        return findUserByName(name)
                .flatMap(user -> Optional.ofNullable(user.getAge()))
                .orElse(0);
    }
}
