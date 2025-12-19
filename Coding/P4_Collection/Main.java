package P4_Collection;

public class Main {
    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        // Add students
        manager.addStudent(new Student("S01", "Alice", 95));
        manager.addStudent(new Student("S02", "Bob", 85));
        manager.addStudent(new Student("S03", "Charlie", 72));
        manager.addStudent(new Student("S04", "Alice", 66));
        manager.addStudent(new Student("S05", "David", 58));
        manager.addStudent(new Student("S06", "Eva", 91));
        manager.addStudent(new Student("S07", "Frank", 77));
        manager.addStudent(new Student("S08", "Grace", 83));
        manager.addStudent(new Student("S09", "Helen", 60));
        manager.addStudent(new Student("S10", "Ivan", 45));

        // Duplicate ID
        manager.addStudent(new Student("S01", "Duplicate", 100));

        System.out.println("\nTop 3 students:");
        manager.getTopStudents(3).forEach((student -> {
            System.out.println("id: " + student.getId() +
                    ", name:  " + student.getName() +
                    ", score: " + student.getScore());
        }));

        System.out.println("\nUnique names:");
        System.out.println(manager.getUniqueNames());

        System.out.println("\nGroup by score ranges:");
        manager.groupByScore().forEach((group, students) -> {
            String range = switch (group) {
                case 1 -> "0–59";
                case 2 -> "60–79";
                case 3 -> "80–100";
                default -> "Unknown";
            };
            System.out.println(range + ": ");
            students.forEach(student -> {
                System.out.println("id: " + student.getId() +
                        ", name:  " + student.getName() +
                        ", score: " + student.getScore());
            });
        });

        System.out.println("\nRemoving student S05...");
        manager.removeStudentById("S05");

        System.out.println("\nUnique names after removal:");
        System.out.println(manager.getUniqueNames());
    }
}
