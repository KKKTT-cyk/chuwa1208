package p4;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        // Add at least 10 students with various scores
        manager.addStudent(new Student("101", "Alice", 95));
        manager.addStudent(new Student("102", "Bob", 82));
        manager.addStudent(new Student("103", "Cathy", 76));
        manager.addStudent(new Student("104", "David", 63));
        manager.addStudent(new Student("105", "Elly", 59));
        manager.addStudent(new Student("106", "Frank", 42));
        manager.addStudent(new Student("107", "Gorge", 38));
        manager.addStudent(new Student("108", "Hannah", 26));
        manager.addStudent(new Student("109", "Ivy", 11));
        manager.addStudent(new Student("110", "Jace", 7));

        // Demonstrate that duplicate student IDs are handled correctly
        manager.addStudent(new Student("101", "Duplicate", 100));

        // Test all methods and display results

        // getTopStudents
        System.out.println("Get top 5 students:");
        List<Student> topStudents = manager.getTopStudents(5);
        topStudents.forEach(System.out::println);

        // getUniqueNames
        System.out.println("\nGet unique names:");
        Set<String> names = manager.getUniqueNames();
        System.out.println(names);

        // groupByScore
        System.out.println("\nGrouped by score:");
        Map<Integer, List<Student>> groups = manager.groupByScore();
        for (Integer key : groups.keySet()) {
            String range = "N/A";
            if (key == 0) range = "0-59";
            if (key == 60) range = "60-79";
            if (key == 80) range = "80+";
            System.out.println(range + " : " + groups.get(key));
        }

        // removeStudentById
        manager.removeStudentById("105");
        System.out.println("\nRemoved Student with id=105:");
        manager.getTopStudents(10).forEach(System.out::println);
    }

    /**
     * Think: Why do we maintain both a List and a Map?
     *
     * Ans: Because List preserves insertion order and supports sorting and
     * iteration which we could use for ranking students. Map on the other hand
     * provides fast lookup by student ID with constant-time complexity
     *
     * Think: What are the trade-offs?
     * Ans: The trade-offs here is increasing memory usage for performance and flexibility
     */
}
