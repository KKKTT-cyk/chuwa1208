package P1_Optional;

public class User {
    private String name;
    private String email;
    private Integer age;
    public User(){
        this.name = "SampleUser";
        this.email = "SampleEmail";
        this.age = 3;
    }

    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }
}
