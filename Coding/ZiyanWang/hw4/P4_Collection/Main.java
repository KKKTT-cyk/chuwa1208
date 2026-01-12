import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        // add srudent
        manager.addStudent(new Student("1", "Alice", 85));
        manager.addStudent(new Student("2", "Bob", 72));
        manager.addStudent(new Student("3", "Charlie", 90));
        manager.addStudent(new Student("4", "Alice", 60));
        manager.addStudent(new Student("5", "David", 55));
        manager.addStudent(new Student("6", "Eve", 78));
        manager.addStudent(new Student("7", "Frank", 88));
        manager.addStudent(new Student("8", "Grace", 95));
        manager.addStudent(new Student("9", "Heidi", 67));
        manager.addStudent(new Student("10", "Ivan", 82));

        // Top students
        System.out.println("Top 3 students:");
        List<Student> top = manager.getTopStudents(3);
        top.forEach(System.out::println);

        // Unique names
        System.out.println("\nUnique names:");
        Set<String> names = manager.getUniqueNames();
        System.out.println(names);

        // Group by score
        System.out.println("\nGroup by score:");
        Map<Integer, List<Student>> groups = manager.groupByScore();
        groups.forEach((k, v) -> System.out.println(k + " -> " + v));

        // Remove student
        manager.removeStudentById("3");
        System.out.println("\nAfter removing student with id=3:");
        manager.getTopStudents(5).forEach(System.out::println);
    }
}

// Why do we maintain both a List and a Map? What are the trade-offs?
// We maintain both a List and a Map to optimize for different access patterns: the List preserves order and supports sorting and aggregation, while the Map provides O(1) lookup and deletion by ID.
// The trade-off is additional memory usage and the need to keep both collections in sync.
