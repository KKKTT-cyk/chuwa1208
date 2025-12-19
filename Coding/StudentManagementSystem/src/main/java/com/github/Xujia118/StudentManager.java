package com.github.Xujia118;

import java.util.*;
import java.util.stream.Collectors;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private Map<String, Student> studentMap = new HashMap<>();

    public void addStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            System.out.println("Skipping duplicate ID: " + student.getId());
            return;
        }
        students.add(student);
        studentMap.put(student.getId(), student);
    }

    public List<Student> getTopStudents(int n) {
        return students.stream()
                .sorted(Comparator.comparingInt(Student::getScore).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    public Set<String> getUniqueNames() {
        return students.stream()
                .map(Student::getName)
                .collect(Collectors.toSet());
    }

    public Map<Integer, List<Student>> groupByScore() {
        Map<Integer, List<Student>> groups = new HashMap<>();
        // Keys: 0 (Fail), 1 (Average), 2 (Excellent)
        groups.put(0, new ArrayList<>());
        groups.put(1, new ArrayList<>());
        groups.put(2, new ArrayList<>());

        for (Student s : students) {
            if (s.getScore() >= 80) groups.get(2).add(s);
            else if (s.getScore() >= 60) groups.get(1).add(s);
            else groups.get(0).add(s);
        }
        return groups;
    }

    public void removeStudentById(String id) {
        Student removed = studentMap.remove(id);
        if (removed != null) {
            students.remove(removed);
        }
    }

    public void displayAll() {
        students.forEach(System.out::println);
    }
}
