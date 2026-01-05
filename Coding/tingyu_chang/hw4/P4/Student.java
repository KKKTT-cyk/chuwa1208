package p4;

import java.util.Objects;

/*
- Create `Student` class:
  - `private String id`, `private String name`, `private int score`
  - Constructor, getters, and `@Override toString()`
  - Override `equals()` and `hashCode()` based on id only
 */
public class Student {
    private String id;
    private String name;
    private int score;

    public Student(String id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getScore() { return score; }

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "', score=" + score + "}";
    }

    // Implementing equals() and hashCode() ensures correct behavior in HashMaps and Sets
    // We base identity ONLY on ID, as requested.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
