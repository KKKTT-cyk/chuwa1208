import java.util.*;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        manager.addStudent(new Student("001", "Alice", 95));
        manager.addStudent(new Student("002", "Bob", 82));
        manager.addStudent(new Student("003", "Charlie", 78));
        manager.addStudent(new Student("004", "David", 60));
        manager.addStudent(new Student("005", "Eve", 55));
        manager.addStudent(new Student("006", "Frank", 88));
        manager.addStudent(new Student("007", "Grace", 72));
        manager.addStudent(new Student("008", "Heidi", 40));
        manager.addStudent(new Student("009", "Ivan", 99));
        manager.addStudent(new Student("010", "Judy", 65));

        manager.addStudent(new Student("005", "Eve-Updated", 75));

        manager.printAllStudents();
        manager.printMap();

        // Test getTopStudents()
        List<Student> top3 = manager.getTopStudents(3);
        for (Student s : top3) {
            System.out.println("  " + s);
        }

        // Test getUniqueNames()
        Set<String> uniqueNames = manager.getUniqueNames();
        for (String name : uniqueNames) {
            System.out.println("  " + name);
        }

        // Test groupByScore()
        Map<Integer, List<Student>> grouped = manager.groupByScore();
        for (Map.Entry<Integer, List<Student>> entry : grouped.entrySet()) {
            int rangeStart = entry.getKey();
            int rangeEnd = (rangeStart == 0) ? 59 : (rangeStart == 60 ? 79 : 100);
            System.out.println("Range " + rangeStart + "-" + rangeEnd + ":");
            for (Student s : entry.getValue()) {
                System.out.println("  " + s);
            }
        }

        // Test removeStudentById()
        manager.removeStudentById("003");
        manager.printAllStudents();
        manager.printMap();

        manager.removeStudentById("005");
        manager.printAllStudents();
        manager.printMap();
    }
}
