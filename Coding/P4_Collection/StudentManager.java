package P4_Collection;

import java.util.*;

/*
    Q: Why do we maintain both a List and a Map? What are the trade-offs?
    A: List is efficient for iterate and sort, but it cost O(n) when we need to check duplicate
    student/remove student by id; while map is efficient for these operations, it is difficult to sort.
       trade-offs: 1. duplicate data; 2. have to keep data consistent;
 */
public class StudentManager {
    private final List<Student> students = new ArrayList<>();
    private final Map<String, Student> studentMap = new HashMap<>();

    // Add student to both collections
    public void addStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            System.out.println("Duplicate student ID ignored: " + student.getId());
            return;
        }
        students.add(student);
        studentMap.put(student.getId(), student);
    }

    // Return top N students by score
    public List<Student> getTopStudents(int n) {
        List<Student> topN = new ArrayList<>();
        students.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));
        for(int i = 0; i < n; i++) {
            topN.add(students.get(i));
        }
        return topN;
    }

    // Unique student names
    public Set<String> getUniqueNames() {
        Set<String> names = new HashSet<>();
        for (Student student : students) {
            names.add(student.getName());
        }
        return names;
    }

    // Group students by score ranges
    public Map<Integer, List<Student>> groupByScore() {
        Map<Integer, List<Student>> groups = new HashMap<>();

        groups.put(1, new ArrayList<>()); // 0–59
        groups.put(2, new ArrayList<>()); // 60–79
        groups.put(3, new ArrayList<>()); // 80–100

        for (Student student : students) {
            int score = student.getScore();
            if (score < 60) {
                groups.get(1).add(student);
            }
            if (score >= 60 && score < 80) {
                groups.get(2).add(student);
            }
            if (score >=80 && score <= 100){
                groups.get(3).add(student);
            }
        }
        return groups;
    }

    // Remove student by ID
    public void removeStudentById(String id) {
        Student removed = studentMap.remove(id);
        if (removed != null) {
            students.remove(removed);
        }
    }

}
