import java.util.*;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private Map<String, Student> studentMap = new HashMap<>();

    public void addStudent(Student s) {
        if (studentMap.containsKey(s.getId())) {
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getId().equals(s.getId())) {
                    students.set(i, s);
                    break;
                }
            }
        } else {
            students.add(s);
        }
        studentMap.put(s.getId(), s);
    }

    public List<Student> getTopStudents(int n) {
        List<Student> copy = new ArrayList<>(students);
        copy.sort((a, b) -> b.getScore() - a.getScore());
        return copy.subList(0, Math.min(n, copy.size()));
    }

    public Set<String> getUniqueNames() {
        Set<String> names = new HashSet<>();
        for (Student s: students) names.add(s.getName());
        return names;
    }

public Map<Integer, List<Student>> groupByScore() {
        Map<Integer, List<Student>> groups = new HashMap<>();
        groups.put(0, new ArrayList<>());
        groups.put(60, new ArrayList<>());
        groups.put(80, new ArrayList<>());

        for (Student s : students) {
            int score = s.getScore();
            if (score <= 59) groups.get(0).add(s);
            else if (score <= 79) groups.get(60).add(s);
            else groups.get(80).add(s);
        }
        return groups;
    }

    public void removeStudentById(String id) {
        studentMap.remove(id);

        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            if (it.next().getId().equals(id)) {
                it.remove();
                break;
            }
        }
    }

    // helpful for printing
    public List<Student> getAllStudents() {
        return students;
    }
}
