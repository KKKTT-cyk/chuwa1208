public class Employee {
    private String name;
    private int id;
    private double salary;

    // default constructor
    public Employee() {
        this.name = "Unknown";
        this.id = 0;
        this.salary = 0.0;
    }
    // second constructor
    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
        this.salary = 0.0;
    }
    // third constructor
    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }
    public void printInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("ID: " + this.id);
        System.out.println("Salary: " + this.salary);
    }
}
