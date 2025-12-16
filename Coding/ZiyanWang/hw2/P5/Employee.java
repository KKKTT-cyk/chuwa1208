public class Employee {
    private String name;
    private int id;
    private double salary;

    // Default constructor
    public Employee() {
        this("Unknown", 0, 0.0);
    }

    // Constructor with name and id
    public Employee(String name, int id) {
        this(name, id, 0.0);
    }

    // Constructor with all fields
    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    // demonstrate all constructors
    public static void main(String[] args) {
        Employee e1 = new Employee();
        Employee e2 = new Employee("A", 101);
        Employee e3 = new Employee("B", 102, 50000);

        System.out.println("e1: " + e1.name + ", " + e1.id + ", " + e1.salary);
        System.out.println("e2: " + e2.name + ", " + e2.id + ", " + e2.salary);
        System.out.println("e3: " + e3.name + ", " + e3.id + ", " + e3.salary);
    }
}