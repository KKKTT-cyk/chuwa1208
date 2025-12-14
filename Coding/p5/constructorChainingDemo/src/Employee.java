public class Employee {
    private String name;
    private int id;
    private double salary;

    // Default constructor explicitly made
    public Employee() {
        this("Unknown", 0, 0.0);
    }

    // Constructor with name and id
    public Employee(String name, int id) {
        this(name, id, 0.0);
    }

    // Constructor with all three parameters
    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public void displayEmployeeInfo() {
        System.out.println("Name: " + name +
                ", ID: " + id +
                ", Salary: " + salary);
    }
}
