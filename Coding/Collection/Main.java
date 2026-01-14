package Coding.Collection;

/**
 * Why do we maintain both a List and a Map? What are the trade-offs?
 * List: For storing students.
 * Map: For fast lookup by ID.
 */
public class Main {
    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        // Add students
        manager.addStudent(new Student("S1", "Alice", 85));
        manager.addStudent(new Student("S2", "Bob", 72));
        manager.addStudent(new Student("S3", "Charlie", 90));
        manager.addStudent(new Student("S4", "Alice", 55));
        manager.addStudent(new Student("S5", "David", 65));
        manager.addStudent(new Student("S6", "Eve", 40));
        manager.addStudent(new Student("S7", "Frank", 78));
        manager.addStudent(new Student("S8", "Grace", 88));
        manager.addStudent(new Student("S9", "Hank", 92));
        manager.addStudent(new Student("S10", "Ivy", 60));

        // Duplicate ID test
        manager.addStudent(new Student("S1", "Duplicate", 100));

        // Top students
        System.out.println("\nTop 3 Students:");
        manager.getTopStudents(3).forEach(System.out::println);

        // Unique names
        System.out.println("\nUnique Names:");
        manager.getUniqueNames().forEach(System.out::println);

        // Group by score
        System.out.println("\nGrouped by Score Range:");
        manager.groupByScore().forEach((k, v) -> {
            String range = "";
            if (k == 1) {
                range = "0-59";
            } else if (k == 2) {
                range = "60-79";
            } else {
                range = "80-100";
            }
            System.out.println(range + " -> " + v);
        });

        // Remove student
        manager.removeStudentById("S5");
        System.out.println("\nAfter removing S5, Top Students:");
        manager.getTopStudents(5).forEach(System.out::println);
    }
}
