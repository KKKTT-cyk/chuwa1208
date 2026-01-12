package com.github.Xujia118;

public class User {
    private final String name;
    private final String email; //can be null
    private final Integer age; //can be null

    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }
}
