import java.util.*;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private Map<String, Student> studentMap = new HashMap<>();

    public void addStudent(Student student) {
        if (student == null || student.getId() == null) {
            return;
        }

        Student existing = studentMap.get(student.getId());
        if (existing != null) {
            students.remove(existing);
        }

        students.add(student);
        studentMap.put(student.getId(), student);
    }

    public List<Student> getTopStudents(int n) {
        List<Student> copy = new ArrayList<>(students);
        copy.sort(Comparator.comparingInt(Student::getScore).reversed());
        if (n > copy.size()) {
            n = copy.size();
        }
        return copy.subList(0, n);
    }

    public Set<String> getUniqueNames() {
        Set<String> names = new HashSet<>();
        for (Student s : students) {
            names.add(s.getName());
        }
        return names;
    }

    public Map<Integer, List<Student>> groupByScore() {
        Map<Integer, List<Student>> grouped = new HashMap<>();
        grouped.put(0, new ArrayList<>());
        grouped.put(60, new ArrayList<>());
        grouped.put(80, new ArrayList<>());

        for (Student s : students) {
            int score = s.getScore();
            int key;
            if (score < 60) {
                key = 0;
            } else if (score < 80) {
                key = 60;
            } else {
                key = 80;
            }
            grouped.get(key).add(s);
        }

        return grouped;
    }

    public void removeStudentById(String id) {
        if (id == null) return;
        Student removed = studentMap.remove(id);
        if (removed != null) {
            students.remove(removed);
        }
    }

    public void printAllStudents() {
        System.out.println("All students (List):");
        for (Student s : students) {
            System.out.println("  " + s);
        }
    }

    public void printMap() {
        System.out.println("Student map (by ID):");
        for (Map.Entry<String, Student> entry : studentMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
    }
}
