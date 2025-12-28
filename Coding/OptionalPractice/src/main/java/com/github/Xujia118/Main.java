package com.github.Xujia118;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // find user by name
        Optional<User> user1 = userService.findUserByName("Alice");
        Optional<User> user2 = userService.findUserByName("David"); // not exist

        // isPresent
        if (user1.isPresent()) {
            System.out.println("User found: " + user1.get().getName());
        }

        if (user2.isPresent()) {
            System.out.println("User found: " + user2.get().getName());
        } else {
            System.out.println("User not found");
        }

        // ifPresent
        user1.ifPresent(u -> System.out.println("User found: " + u.getName()));
        user2.ifPresent(u -> System.out.println("This won't print"));

        // getUserEmail
        System.out.println(userService.getUserEmail("Alice"));
        System.out.println(userService.getUserEmail("Bob")); // didn't provide email
        System.out.println(userService.getUserEmail("Carol"));
        System.out.println(userService.getUserEmail("David")); // user doesn't exist

        // --- getUserAge ---
        System.out.println(userService.getUserAge("Carol")); // 0
        System.out.println(userService.getUserAge("Alice")); // 25
        System.out.println(userService.getUserAge("David")); // 0

        // --- orElse vs orElseGet ---
        demonstrateOrElse();
        demonstrateOrElseGet();
    }

    // helper method
    private static String expensiveDefault() {
        System.out.println("⚠️ expensiveDefault() called");
        return "DEFAULT";
    }

    private static void demonstrateOrElse() {
        System.out.println("\n--- orElse demo ---");

        Optional<String> opt = Optional.of("VALUE");
        String result = opt.orElse(expensiveDefault());

        System.out.println("Result: " + result);
    }

    private static void demonstrateOrElseGet() {
        System.out.println("\n--- orElseGet demo ---");

        Optional<String> opt = Optional.of("VALUE");
        String result = opt.orElseGet(Main::expensiveDefault);

        System.out.println("Result: " + result);
    }
}