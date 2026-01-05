package p4;

import java.util.List;
import java.util.Map;

/*
- In `Main`:
  - Add at least 10 students with various scores
  - Test all methods and display results
  - Demonstrate that duplicate student IDs are handled correctly
- Think: Why do we maintain both a List and a Map? What are the trade-offs?
 */

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        // 1. Add Students (including a duplicate ID to test update logic)
        manager.addStudent(new Student("S01", "Alice", 85));
        manager.addStudent(new Student("S02", "Bob", 45));
        manager.addStudent(new Student("S03", "Charlie", 92));
        manager.addStudent(new Student("S04", "David", 65));
        manager.addStudent(new Student("S05", "Eve", 78));
        manager.addStudent(new Student("S06", "Alice", 88)); // Duplicate name, diff ID
        manager.addStudent(new Student("S07", "Frank", 55));
        manager.addStudent(new Student("S08", "Grace", 99));
        manager.addStudent(new Student("S09", "Heidi", 72));
        manager.addStudent(new Student("S02", "Bobby", 50)); // Duplicate ID (Update)

        System.out.println("\n--- Top 3 Students ---");
        for (Student s : manager.getTopStudents(3)) {
            System.out.println(s);
        }

        System.out.println("\n--- Unique Names ---");
        System.out.println(manager.getUniqueNames());

        System.out.println("\n--- Grouped By Score ---");
        Map<Integer, List<Student>> groups = manager.groupByScore();
        System.out.println("Failing (0-59): " + groups.get(1).size());
        System.out.println("Average (60-79): " + groups.get(2).size());
        System.out.println("Top Tier (80-100): " + groups.get(3).size());

        System.out.println("\n--- Removal Test ---");
        manager.removeStudentById("S03");
    }
}

/* THINK: Why maintain both List and Map?
   - Trade-off: Memory vs. Speed.
   - We use extra memory to store the references twice.
   - Benefit: We get O(1) fast lookup by ID via Map (instead of looping through List O(n))
     and we keep the ability to sort/iterate easily via the List.
*/
