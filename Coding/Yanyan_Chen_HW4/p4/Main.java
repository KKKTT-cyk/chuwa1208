import java.util.*;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        System.out.println("=== STUDENT MANAGEMENT SYSTEM ===\n");

        // Step 1: Add students with various scores
        System.out.println("1. ADDING STUDENTS:");
        System.out.println("-".repeat(50));

        manager.addStudent(new Student("S001", "Alice", 95));
        manager.addStudent(new Student("S002", "Bob", 72));
        manager.addStudent(new Student("S003", "Charlie", 88));
        manager.addStudent(new Student("S004", "Diana", 45));
        manager.addStudent(new Student("S005", "Eve", 91));
        manager.addStudent(new Student("S006", "Frank", 67));
        manager.addStudent(new Student("S007", "Grace", 55));
        manager.addStudent(new Student("S008", "Henry", 82));
        manager.addStudent(new Student("S009", "Alice", 78));  // Duplicate name
        manager.addStudent(new Student("S010", "Jack", 93));

        System.out.println("Added 10 students successfully.\n");

        // Step 2: Display all students
        System.out.println("All Students:");
        for (Student student : manager.getAllStudents()) {
            System.out.println("  " + student);
        }
        System.out.println("Total students: " + manager.getStudentCount() + "\n");

        // Step 2: Test duplicate ID handling
        System.out.println("2. TESTING DUPLICATE ID HANDLING:");
        System.out.println("-".repeat(50));
        System.out.println("Attempting to add student with existing ID S003...");
        manager.addStudent(new Student("S003", "Charlie Brown", 99));
        System.out.println("After adding duplicate ID:");
        Student s003 = manager.getStudentById("S003");
        System.out.println("  " + s003);
        System.out.println("Total students: " + manager.getStudentCount() + "\n");

        // Step 3: Get top students
        System.out.println("3. TOP 5 STUDENTS BY SCORE:");
        System.out.println("-".repeat(50));
        List<Student> topStudents = manager.getTopStudents(5);
        for (int i = 0; i < topStudents.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + topStudents.get(i));
        }
        System.out.println();

        // Step 4: Get unique names
        System.out.println("4. UNIQUE STUDENT NAMES:");
        System.out.println("-".repeat(50));
        Set<String> uniqueNames = manager.getUniqueNames();
        System.out.println("  Total unique names: " + uniqueNames.size());
        System.out.println("  Names: " + uniqueNames);
        System.out.println();

        // Step 5: Group by score ranges
        System.out.println("5. STUDENTS GROUPED BY SCORE RANGES:");
        System.out.println("-".repeat(50));
        Map<Integer, List<Student>> groupedStudents = manager.groupByScore();

        System.out.println("  Fail (0-59):");
        for (Student student : groupedStudents.get(0)) {
            System.out.println("    " + student);
        }

        System.out.println("\n  Pass (60-79):");
        for (Student student : groupedStudents.get(60)) {
            System.out.println("    " + student);
        }

        System.out.println("\n  Excellent (80-100):");
        for (Student student : groupedStudents.get(80)) {
            System.out.println("    " + student);
        }
        System.out.println();

        // Step 6: Remove a student
        System.out.println("6. REMOVING STUDENT:");
        System.out.println("-".repeat(50));
        System.out.println("Removing student with ID S005...");
        manager.removeStudentById("S005");
        System.out.println("Total students after removal: " + manager.getStudentCount());
        System.out.println("\nAttempting to remove non-existent student S999...");
        manager.removeStudentById("S999");
        System.out.println();
    }
}