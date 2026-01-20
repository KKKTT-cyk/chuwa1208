package P4;
/*
Create Student class:
    private String id, private String name, private int score
    Constructor, getters, and @Override toString()
    Override equals() and hashCode() based on id only
 */
import java.lang.Object;
import java.util.Objects;

public class Student {
    private String id;
    private String name;
    private int score;

    // Constructor
    public Student (String id, String name, int score){
        this.id = id;
        this.name = name;
        this.score = score;
    }

    // Getter
    public String getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public int getScore(){
        return  score;
    }
    // Override method
    @Override
    public String toString(){
        return "Student{id = " + id + name + score + "}";
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals (id, student.id);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
