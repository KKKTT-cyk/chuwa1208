package Part2;

import java.util.Optional;

public class Main {
    public static void main(String[] args){
        // Create a UserService with sample data (one user without email, one without age)
        UserService service = new UserService();

        // Test findUserByName() with existing and non-existing names
        // Use isPresent() and ifPresent() to check results
        System.out.println("=====Test : findUserByName()=====");
        Optional<User> user1 = service.findUserByName("SampleUser_1"); // existing user
        Optional<User> nullUser = service.findUserByName("Null_User"); // non-existing user

        System.out.println("isPresent() test:");
        System.out.println(user1.isPresent());
        System.out.println(nullUser.isPresent());

        System.out.println("\nifPresent() test:");
        user1.ifPresent(user ->
                System.out.println(user.getName())
        );

        nullUser.ifPresent(user ->
                System.out.println("This line should NOT be printed")
        );

        // Test getUserEmail() and getUserAge() with different scenarios
        System.out.println("\n=====Test : getUserEmail()=====");
        System.out.println("SampleUser_1 email: " + service.getUserEmail("SampleUser_1"));
        System.out.println("SampleUser_2 email: " + service.getUserEmail("SampleUser_2"));
        System.out.println("Null_User email: " + service.getUserEmail("Null_User"));

        System.out.println("\n=====Test : getUserAge()=====");
        System.out.println("SampleUser_1 age: " + service.getUserAge("SampleUser_1"));
        System.out.println("SampleUser_3 age: " + service.getUserAge("SampleUser_3"));
        System.out.println("Null_User age: " + service.getUserAge("Null_User"));

        // Demonstrate the difference between orElse() and orElseGet() with a method that prints when called
        System.out.println("\n=====Test : orElse() vs orElseGet()=====");

        Optional<User> presentUser = service.findUserByName("SampleUser_1");

        System.out.println("orElse() test:");
        presentUser.orElse(createDefaultUser());

        System.out.println("\norElseGet() test:");
        presentUser.orElseGet(() -> createDefaultUser());

    }

    // a method that prints when called
    private static User createDefaultUser() {
        System.out.println("createDefaultUser() called");
        return new User("DefaultUser", null, null);
    }
}
