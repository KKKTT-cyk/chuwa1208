package com.github.Xujia118;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserService {
    private final Map<String, User> userDatabase;

    public UserService() {
        this.userDatabase = new HashMap<>();

        userDatabase.put("Alice", new User("Alice", "alice@gmail.com", 25));
        userDatabase.put("Bob", new User("Bob", null, 30));      // no email
        userDatabase.put("Carol", new User("Carol", "carol@yahoo.com", null)); // no age
    }

    public Optional<User> findUserByName(String name) {
        return Optional.ofNullable(userDatabase.get(name));
    }

    public String getUserEmail(String name) {
        return findUserByName(name)
                .map(User::getEmail)
                .orElse("No email provided");
    }

    public Integer getUserAge(String name) {
        return findUserByName(name)
                .map(User::getAge)
                .orElse(0);
    }
}
