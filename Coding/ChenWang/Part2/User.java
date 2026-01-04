public class User {
    private final String name;
    private final String email;
    private final Integer age;

    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public Integer getAge() { return age; }
}
