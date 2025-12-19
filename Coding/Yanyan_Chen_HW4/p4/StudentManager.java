import java.util.*;
import java.util.stream.Collectors;

public class StudentManager {
    private List<Student> students;      // For ordered operations and iteration
    private Map<String, Student> studentMap;  // For fast lookup by ID

    public StudentManager() {
        this.students = new ArrayList<>();
        this.studentMap = new HashMap<>();
    }

    public void addStudent(Student student) {
        // Check if student already exists
        if (studentMap.containsKey(student.getId())) {
            // Remove old student from list
            students.removeIf(s -> s.getId().equals(student.getId()));
            System.out.println("Warning: Student with ID " + student.getId() + " already exists. Replacing...");
        }

        // Add to both collections
        students.add(student);
        studentMap.put(student.getId(), student);
    }

    public List<Student> getTopStudents(int n) {
        // Create a copy to avoid modifying original list
        List<Student> sortedStudents = new ArrayList<>(students);

        // Sort by score in descending order
        sortedStudents.sort((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()));

        // Return top n students (or all if n > size)
        return sortedStudents.subList(0, Math.min(n, sortedStudents.size()));
    }

    public Set<String> getUniqueNames() {
        Set<String> uniqueNames = new HashSet<>();
        for (Student student : students) {
            uniqueNames.add(student.getName());
        }
        return uniqueNames;
    }

    public Map<Integer, List<Student>> groupByScore() {
        Map<Integer, List<Student>> groupedStudents = new HashMap<>();

        // Initialize the three groups
        groupedStudents.put(0, new ArrayList<>());   // 0-59
        groupedStudents.put(60, new ArrayList<>());  // 60-79
        groupedStudents.put(80, new ArrayList<>());  // 80-100

        // Group each student
        for (Student student : students) {
            int score = student.getScore();
            if (score >= 0 && score < 60) {
                groupedStudents.get(0).add(student);
            } else if (score >= 60 && score < 80) {
                groupedStudents.get(60).add(student);
            } else if (score >= 80 && score <= 100) {
                groupedStudents.get(80).add(student);
            }
        }

        return groupedStudents;
    }

    public void removeStudentById(String id) {
        // Remove from map
        Student removed = studentMap.remove(id);

        if (removed != null) {
            // Remove from list
            students.removeIf(s -> s.getId().equals(id));
            System.out.println("Removed: " + removed);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student getStudentById(String id) {
        return studentMap.get(id);
    }

    public int getStudentCount() {
        return students.size();
    }
}