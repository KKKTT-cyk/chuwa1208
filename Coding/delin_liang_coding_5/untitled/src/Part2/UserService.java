package Part2;

import java.util.*;

public class UserService {
    private Map<String, User> userDatabase;
    // initialized in constructor with 3 sample users
    public UserService() {
        userDatabase = new HashMap<>();

        // sample users
        userDatabase.put("SampleUser_1",
                new User("SampleUser_1", "sample_user_1@example.com", 25));
        userDatabase.put("SampleUser_2",
                new User("SampleUser_2", null, 30)); // no email
        userDatabase.put("SampleUser_3",
                new User("SampleUser_3", "sample_user_3@example.com", null)); // no age
    }

    // returns Optional.ofNullable()
    public Optional<User> findUserByName(String name) {
        return Optional.ofNullable(userDatabase.get(name));
    }

    //  uses Optional to return email or "No email provided"
    public String getUserEmail(String name) {
        return findUserByName(name)
                .map(User::getEmail)
                .orElse("No email provided");
    }

    //  uses Optional to return age or 0
    public int getUserAge(String name) {
        return findUserByName(name)
                .map(User::getAge)
                .orElse(0);
    }
}
