package Coding.Collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private Map<String, Student> studentMap = new HashMap<>();

    // Add student to both List and Map
    public void addStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            System.out.println("Duplicate ID ignored: " + student.getId());
            return;
        }
        students.add(student);
        studentMap.put(student.getId(), student);
    }

    // Return top N students by score
    public List<Student> getTopStudents(int n) {
        return students.stream()
                .sorted(Comparator.comparingInt(Student::getScore).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    // Return unique student names
    public Set<String> getUniqueNames() {
        Set<String> names = new HashSet<>();
        for (Student s : students) {
            names.add(s.getName());
        }
        return names;
    }

    // Group students by score ranges
    public Map<Integer, List<Student>> groupByScore() {

        Map<Integer, List<Student>> result = new HashMap<>();
        result.put(1, new ArrayList<>()); // 0–59
        result.put(2, new ArrayList<>()); // 60–79
        result.put(3, new ArrayList<>()); // 80–100

        for (Student s : students) {
            int score = s.getScore();
            if (score <= 59) {
                result.get(1).add(s);
            } else if (score <= 79) {
                result.get(2).add(s);
            } else {
                result.get(3).add(s);
            }
        }
        return result;
    }

    // Remove student from both List and Map
    public void removeStudentById(String id) {
        Student removed = studentMap.remove(id);
        if (removed != null) {
            students.remove(removed);
        }
    }
}
