import java.util.*;

public class Main {
    public static void main(String[] args) {
        StudentManager m = new StudentManager();

        m.addStudent(new Student("001", "Aaron", 100));
        m.addStudent(new Student("002", "Baily", 72));
        m.addStudent(new Student("003", "Chloe", 58));
        m.addStudent(new Student("004", "David", 83));
        m.addStudent(new Student("005", "Eric", 67));
        m.addStudent(new Student("006", "Frank", 100));
        m.addStudent(new Student("007", "Grigori", 79));
        m.addStudent(new Student("008", "Helen", 60));
        m.addStudent(new Student("009", "Jason", 45));
        m.addStudent(new Student("010", "Russell", 88));

        System.out.println("All: " + m.getAllStudents());

        m.addStudent(new Student("002", "Bailey", 100));
        System.out.println("duplicate add: " + m.getAllStudents());

        System.out.println("Top 5: " + m.getTopStudents(5));
        System.out.println("Unique names: " + m.getUniqueNames());

        Map<Integer, List<Student>> g = m.groupByScore();
        System.out.println("0-59: " + g.get(0));
        System.out.println("60-79: " + g.get(60));
        System.out.println("80-100: " + g.get(80));

        m.removeStudentById("005");
        System.out.println("After remove: " + m.getAllStudents());
    }
}
