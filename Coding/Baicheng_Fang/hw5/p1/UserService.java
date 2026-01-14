import java.util.*;

public class UserService {

    private Map<String, User> userDatabase;

    public UserService() {
        userDatabase = new HashMap<>();

        userDatabase.put("Bailey", new User("Bailey", "bailey@crimson.com", 25));
        userDatabase.put("David", new User("David", null, 30));
        userDatabase.put("Frank", new User("Frank", "frank@roguebo.com", null));
    }

    public Optional<User> findUserByName(String name) {
        return Optional.ofNullable(userDatabase.get(name));
    }

    public String getUserEmail(String name) {
        return findUserByName(name)
            .map(User::getEmail)
            .filter(Objects::nonNull)
            .orElse("No email provided");
    }

    public int getUserAge(String name) {
        return findUserByName(name)
            .map(User::getAge)
            .orElse(0);
    }

    public static String expensiveDefaultEmail() {
        System.out.println("expensive default method called");
        return "no email provided";
    }
}
