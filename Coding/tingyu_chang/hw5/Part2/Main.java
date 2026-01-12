/*
In Main:
Create a UserService with sample data (one user without email, one without age)
Test findUserByName() with existing and non-existing names
Use isPresent() and ifPresent() to check results
Test getUserEmail() and getUserAge() with different scenarios
Demonstrate the difference between orElse() and orElseGet() with a method that prints
when called
 */

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserService();

        System.out.println("--- 1. Testing findUserByName() ---");
        Optional<User> alice = service.findUserByName("Alice");
        Optional<User> unknown = service.findUserByName("Unknown");

        // isPresent() check
        System.out.println("Is Alice present? " + alice.isPresent());
        System.out.println("Is Unknown present? " + unknown.isPresent());

        // ifPresent() check
        alice.ifPresent(u -> System.out.println("Found: " + u.getName()));


        System.out.println("\n--- 2. Testing getUserEmail() ---");
        System.out.println("Alice's Email: " + service.getUserEmail("Alice"));
        System.out.println("Bob's Email (Null in DB): " + service.getUserEmail("Bob"));
        System.out.println("Unknown User Email: " + service.getUserEmail("Unknown"));


        System.out.println("\n--- 3. Testing getUserAge() ---");
        System.out.println("Alice's Age: " + service.getUserAge("Alice"));
        System.out.println("Charlie's Age (Null in DB): " + service.getUserAge("Charlie"));


        System.out.println("\n--- 4. orElse() vs orElseGet() ---");

        System.out.println("Test A: User Exists (Alice)");
        // orElse: Expect "createDefaultUser() was called" to print
        User u1 = service.findUserByName("Alice")
                .orElse(service.createDefaultUser());

        // orElseGet: Expect no print
        User u2 = service.findUserByName("Alice")
                .orElseGet(() -> service.createDefaultUser());

        System.out.println("\nTest B: User Missing (David)");
        // orElseGet: Expect "createDefaultUser() was called" to print now
        User u3 = service.findUserByName("David")
                .orElseGet(() -> service.createDefaultUser());
    }
}


