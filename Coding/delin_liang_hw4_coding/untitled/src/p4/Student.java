package p4;

public class Student {
    private String id;
    private String name;
    private int score;

    // Constructor, getters, and @Override toString()
    public Student(String id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student: " +
                "id=" + id + ", name=" + name + ", score=" + score;
    }

    // Override equals() and hashCode() based on id only
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student other = (Student) o;

        if (id == null) {
            return other.id == null;
        }
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return 0;
        }
        return id.hashCode();
    }
}
