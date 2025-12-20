package p4;

import java.util.*;

/*
- Create `StudentManager` class with:
  - `private List<Student> students` - use ArrayList
  - `private Map<String, Student> studentMap` - use HashMap
  - `public void addStudent(Student student)` - add to both list and map
  - `public List<Student> getTopStudents(int n)` - return top n students by score (use sorting)
  - `public Set<String> getUniqueNames()` - return unique names using Set
  - `public Map<Integer, List<Student>> groupByScore()` - group students by score ranges (0-59, 60-79, 80-100)
  - `public void removeStudentById(String id)` - remove from both collections
 */

public class StudentManager {
    // We maintain two collections for the same data to optimize for different access patterns
    private List<Student> students;           // Good for iteration and sorting [cite: 487]
    private Map<String, Student> studentMap;  // Good for fast lookups by ID [cite: 510]

    public StudentManager() {
        this.students = new ArrayList<>();
        this.studentMap = new HashMap<>();
    }

    public void addStudent(Student student) {
        // If ID exists, update both
        if (studentMap.containsKey(student.getId())) {
            // Remove old entry from list to avoid duplicates
            Student old = studentMap.get(student.getId());
            students.remove(old);
            System.out.println("Updated student: " + student.getName());
        } else {
            System.out.println("Added student: " + student.getName());
        }

        students.add(student);
        studentMap.put(student.getId(), student);
    }

    public List<Student> getTopStudents(int n) {
        // Create a copy to sort so we don't mess up the original list order
        List<Student> sortedList = new ArrayList<>(students);

        // Sorting using a lambda comparator (Descending order of score)
        sortedList.sort((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()));

        // Return sublist (handle case where n > list size)
        return sortedList.subList(0, Math.min(n, sortedList.size()));
    }

    public Set<String> getUniqueNames() {
        // Set automatically filters duplicates [cite: 524]
        Set<String> names = new HashSet<>();
        for (Student s : students) {
            names.add(s.getName());
        }
        return names;
    }

    // Returns a Map where Key 1=Low, 2=Mid, 3=High ranges
    public Map<Integer, List<Student>> groupByScore() {
        Map<Integer, List<Student>> groups = new HashMap<>();
        // Initialize buckets
        groups.put(1, new ArrayList<>()); // 0-59
        groups.put(2, new ArrayList<>()); // 60-79
        groups.put(3, new ArrayList<>()); // 80-100

        for (Student s : students) {
            if (s.getScore() < 60) {
                groups.get(1).add(s);
            } else if (s.getScore() < 80) {
                groups.get(2).add(s);
            } else {
                groups.get(3).add(s);
            }
        }
        return groups;
    }

    public void removeStudentById(String id) {
        Student s = studentMap.remove(id); // Remove from Map
        if (s != null) {
            students.remove(s);            // Remove from List
            System.out.println("Removed student ID: " + id);
        } else {
            System.out.println("Student ID " + id + " not found.");
        }
    }
}
