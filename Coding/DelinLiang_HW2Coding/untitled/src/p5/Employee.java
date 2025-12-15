package p5;

public class Employee {
    // Fields: name , id , salary
    private String name;
    private int id;
    private double salary;

    // Default constructor (sets default values)
    public Employee() {
        this("Default", 0, 0.0); // Use this() to call one constructor from another
    }

    // Constructor with name and id parameters (sets salary to 0)
    public Employee(String name, int id) {
        this(name, id, 0.0); // Use this() to call one constructor from another
    }

    // Constructor with all three parameters
    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public void displayInfo() {
        System.out.println("Name: " + name + "\nID: " + id + "\nSalary: " + salary + "\n");
    }

    // Demonstrate creating objects using all three constructors
    public static void main(String[] args) {
        Employee e1 = new Employee();                 // default constructor
        Employee e2 = new Employee("Alice", 101);     // two-argument constructor
        Employee e3 = new Employee("Bob", 202, 330000.0); // full constructor

        e1.displayInfo();
        e2.displayInfo();
        e3.displayInfo();
    }
}
