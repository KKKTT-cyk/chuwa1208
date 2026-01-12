package p4;

import java.util.*;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private Map<String, Student> studentMap = new HashMap<>();

    public void addStudent(Student student) {
        // add to both list and map
        if (student == null || student.getId() == null) {
            return;
        }
        if (studentMap.containsKey(student.getId())) {
            System.out.println("[Ignored] Duplicated id: " + student.getId());
            return;
        }
        students.add(student);
        studentMap.put(student.getId(), student);
    }

    public List<Student> getTopStudents(int n) {
        // return top n students by score (use sorting)
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> b.getScore() - a.getScore());
        return sorted.subList(0, Math.min(n, sorted.size()));
    }

    public Set<String> getUniqueNames() {
        // return unique names using Set
        Set<String> names = new HashSet<>();
        for (Student s : students) {
            names.add(s.getName());
        }
        return names;
    }

    public Map<Integer, List<Student>> groupByScore() {
        // group students by score ranges (0-59, 60-79, 80-100)
        Map<Integer, List<Student>> groups = new LinkedHashMap<>();

        groups.put(0, new ArrayList<>());
        groups.put(60, new ArrayList<>());
        groups.put(80, new ArrayList<>());

        for (Student s : students) {
            int score = s.getScore();
            if (score < 60) {
                groups.get(0).add(s);
            } else if (score < 80) {
                groups.get(60).add(s);
            } else {
                groups.get(80).add(s);
            }
        }
        return groups;
    }

    public void removeStudentById(String id) {
        // remove from both collections
        if (id == null) return;
        Student removed = studentMap.remove(id);
        if (removed != null) {
            students.remove(removed);
        }
    }
}
