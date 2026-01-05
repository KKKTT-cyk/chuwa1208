package P1_Optional;

import java.util.Optional;

public class Main {
    final public static String NULLEMAIL = "NullEmail";
    final public static String NULLAGE = "NullAge";
    public static void main(String[] args) {
        // Create a UserService with sample data (one user without email, one without age)
        UserService service = new UserService();

        // Test findUserByName() with existing
        System.out.println(service.findUserByName("SampleUser1"));

        service.addNewUser(new User(NULLEMAIL, null, 10));
        service.addNewUser(new User(NULLAGE, "email", null));


        // Use isPresent() and ifPresent() to check results
        Optional<User> nullEmailUser = service.findUserByName(NULLEMAIL);
        System.out.println(nullEmailUser.isPresent());
        nullEmailUser.ifPresent(user -> System.out.println(user.getName()));

        Optional<User> nullAgeUser = service.findUserByName(NULLAGE);
        System.out.println(nullAgeUser.isPresent());
        nullAgeUser.ifPresent(user -> System.out.println(user.getName()));

        // Test getUserEmail() and getUserAge() with different scenarios
        System.out.println(service.getUserEmail("SampleUser1"));
        System.out.println(service.getUserEmail(NULLEMAIL));
        System.out.println(service.getUserEmail(NULLAGE));

        System.out.println(service.getUserAge("SampleUser1"));
        System.out.println(service.getUserAge(NULLEMAIL));
        System.out.println(service.getUserAge(NULLAGE));

        // Demonstrate the difference between orElse() and orElseGet() with a method that prints when called
        System.out.println("Testing orElse():");
        Optional<User> test1 = service.findUserByName("SampleUser1");
        test1.orElse(createDefaultUser());

        System.out.println("Testing orElseGet():");
        Optional<User> test2 = service.findUserByName("SampleUser1");
        test2.orElseGet(Main::createDefaultUser);
    }

    private static User createDefaultUser() {
        System.out.println("createDefaultUser() called");
        return new User("Default", null, null);
    }
}
