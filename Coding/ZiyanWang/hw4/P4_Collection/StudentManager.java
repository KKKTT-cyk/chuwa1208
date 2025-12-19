import java.util.*;

public class StudentManager {

    private List<Student> students = new ArrayList<>();
    private Map<String, Student> studentMap = new HashMap<>();

    // add student
    public void addStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            throw new IllegalArgumentException("Duplicate student id: " + student.getId());
        }
        students.add(student);
        studentMap.put(student.getId(), student);
    }

    // return hightest score top N students
    public List<Student> getTopStudents(int n) {
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> b.getScore() - a.getScore());
        return sorted.subList(0, Math.min(n, sorted.size()));
    }

    // return unique student name
    public Set<String> getUniqueNames() {
        Set<String> names = new HashSet<>();
        for (Student s : students) {
            names.add(s.getName());
        }
        return names;
    }

    // group by score
    public Map<Integer, List<Student>> groupByScore() {
        Map<Integer, List<Student>> result = new HashMap<>();

        for (Student s : students) {
            int key;
            int score = s.getScore();

            if (score <= 59) {
                key = 0;
            } else if (score <= 79) {
                key = 60;
            } else {
                key = 80;
            }

            result.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return result;
    }

    // delete student id
    public void removeStudentById(String id) {
        Student removed = studentMap.remove(id);
        if (removed != null) {
            students.remove(removed);
        }
    }
}
