package Part2;

public class User {
    private String name;
    private String email; // can be null
    private Integer age; // can be null

    // Constructor and getters
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
