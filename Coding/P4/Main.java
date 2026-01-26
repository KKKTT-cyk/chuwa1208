package P4;

import java.util.List;
import java.util.Map;
import java.util.Set;

/*
In Main:
    Add at least 10 students with various scores
    Test all methods and display results
    Demonstrate that duplicate student IDs are handled correctly
 */
public class Main {
    public static void main(String[] args) {
        StudentManger manager = new StudentManger();

        // add 10 students
        manager.addStudent(new Student("S001", "Alice", 95));
        manager.addStudent(new Student("S002", "Bob", 72));
        manager.addStudent(new Student("S003", "Cathy", 88));
        manager.addStudent(new Student("S004", "David", 55));
        manager.addStudent(new Student("S005", "Eva", 61));
        manager.addStudent(new Student("S006", "Frank", 79));
        manager.addStudent(new Student("S007", "Grace", 83));
        manager.addStudent(new Student("S008", "Helen", 40));
        manager.addStudent(new Student("S009", "Ivy", 100));
        manager.addStudent(new Student("S010", "Bob", 67)); // duplicate name

        // demonstrate duplicate student IDs
        System.out.println("===Duplicate name demo===");
        manager.addStudent(new Student("S003", "JL", 96));
        System.out.println("Student S003 after update: " + manager.getStudentById("S003"));
        System.out.println();


        // Display all
        System.out.println("=== All Students ===");
        for (Student s : manager.getAllStudents()) {
            System.out.println(s);
        }
        System.out.println();

        // Top N
        System.out.println("=== Top 3 Students ===");
        List<Student> top3 = manager.getTopStudents(3);
        for (Student s : top3) {
            System.out.println(s);
        }
        System.out.println();

        // Unique names
        System.out.println("=== Unique Names ===");
        Set<String> uniqueNames = manager.getUniqueNames();
        System.out.println(uniqueNames);
        System.out.println();

        // Group by score ranges
        System.out.println("=== Group By Score Range (0, 60, 80) ===");
        Map<Integer, List<Student>> groups = manager.groupByScore();
        System.out.println("0-59: " + groups.get(0));
        System.out.println("60-79: " + groups.get(60));
        System.out.println("80-100: " + groups.get(80));
        System.out.println();

        // Remove
        System.out.println("=== Remove Student By ID (S004) ===");
        manager.removeStudentById("S004");
        System.out.println("Student S004 after remove: " + manager.getStudentById("S004"));
        System.out.println("All students count: " + manager.getAllStudents().size());
    }
}
