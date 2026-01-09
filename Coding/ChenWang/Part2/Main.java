import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserService();

        Optional<User> aliceOpt = service.findUserByName("Alice");
        System.out.println("Alice isPresent? " + aliceOpt.isPresent());
        aliceOpt.ifPresent(user -> System.out.println("Found: " + user));

        Optional<User> nobodyOpt = service.findUserByName("Nobody");
        System.out.println("Nobody isPresent? " + nobodyOpt.isPresent());
        nobodyOpt.ifPresent(user -> System.out.println("Found: " + user));

        System.out.println("Alice email: " + service.getUserEmail("Alice"));
        System.out.println("Bob email: " + service.getUserEmail("Bob"));
        System.out.println("Nobody email: " + service.getUserEmail("Nobody"));

        System.out.println("Alice age: " + service.getUserAge("Alice"));
        System.out.println("Cindy age: " + service.getUserAge("Cindy"));
        System.out.println("Nobody age: " + service.getUserAge("Nobody"));

        System.out.println("-- Case A: user exists and email exists (Alice) --");
        String emailA1 = service.findUserByName("Alice")
                .map(User::getEmail)
                .orElse(UserService.expensiveDefaultEmail());
        System.out.println("Result with orElse: " + emailA1);

        String emailA2 = service.findUserByName("Alice")
                .map(User::getEmail)
                .orElseGet(UserService::expensiveDefaultEmail);
        System.out.println("Result with orElseGet: " + emailA2);

        System.out.println("\n-- Case B: user exists but email is null (Bob) --");
        String emailB1 = service.findUserByName("Bob")
                .map(User::getEmail)
                .orElse(UserService.expensiveDefaultEmail());
        System.out.println("Result with orElse: " + emailB1);

        String emailB2 = service.findUserByName("Bob")
                .map(User::getEmail)
                .orElseGet(UserService::expensiveDefaultEmail);
        System.out.println("Result with orElseGet: " + emailB2);

        System.out.println("\n-- Case C: user does not exist (Nobody) --");
        String emailC1 = service.findUserByName("Nobody")
                .map(User::getEmail)
                .orElse(UserService.expensiveDefaultEmail());
        System.out.println("Result with orElse: " + emailC1);

        String emailC2 = service.findUserByName("Nobody")
                .map(User::getEmail)
                .orElseGet(UserService::expensiveDefaultEmail);
        System.out.println("Result with orElseGet: " + emailC2);
    }
}
