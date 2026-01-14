package com.github.Xujia118;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        // 1. Add 10+ students (including a duplicate ID)
        manager.addStudent(new Student("S01", "Alice", 85));
        manager.addStudent(new Student("S02", "Bob", 45));
        manager.addStudent(new Student("S03", "Charlie", 92));
        manager.addStudent(new Student("S04", "Alice", 70)); // Same name, different ID
        manager.addStudent(new Student("S05", "David", 58));
        manager.addStudent(new Student("S06", "Eve", 88));
        manager.addStudent(new Student("S07", "Frank", 72));
        manager.addStudent(new Student("S08", "Grace", 95));
        manager.addStudent(new Student("S09", "Heidi", 65));
        manager.addStudent(new Student("S10", "Ivan", 30));
        manager.addStudent(new Student("S01", "Duplicate", 100)); // Duplicate ID

        System.out.println("\n--- All Students ---");
        manager.displayAll();

        System.out.println("\n--- Top 3 Students ---");
        manager.getTopStudents(3).forEach(System.out::println);

        System.out.println("\n--- Unique Names ---");
        System.out.println(manager.getUniqueNames());

        System.out.println("\n--- Grouped by Score (80-100 Range) ---");
        manager.groupByScore().get(2).forEach(System.out::println);

        System.out.println("\n--- Removing ID S02 (Bob) ---");
        manager.removeStudentById("S02");
        manager.displayAll();
    }

    /**
     * We maintain both a List and a Map because:
     * List is O(N) lookup, but Map is O(1);
     * List keeps insertion order, Map doesn't guarantee order;
     * List allows easy sort by score, Map requires conversion;
     * List has low memory overhead, Map has higher overhead.
     *
     * The Trade-off: We trade memory for speed.
     * By maintaining both, we get the "best of both worlds":
     * instant lookups and removals via the Map, while retaining the ability to sort and iterate in order via the List.
     */

}