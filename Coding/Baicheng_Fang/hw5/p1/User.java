public class User {
    
    private final String name;
    private final String email;
    private final Integer age;

    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    Integer getAge() {
        return age;
    }
}
