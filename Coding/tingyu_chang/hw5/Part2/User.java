/*
Create a User class with:
private String name
private String email (can be null)
private Integer age (can be null)
Constructor and getters
 */

public class User {
    private String name;
    private String email; // can be null
    private Integer age;  // can be null

    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public Integer getAge() { return age; }

    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email + "', age=" + age + "}";
    }
}
