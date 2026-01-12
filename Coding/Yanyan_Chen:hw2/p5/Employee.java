public class Employee {

    private String name;
    private int id;
    private double salary;

    public Employee() {
        this("Default", 0, 0.0);
    }

    public Employee(String name, int id) {
        this(name, id, 0.0);
    }

    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public void display() {
        System.out.println("Employee name: " + name + ", id: " + id + ", salary: " + salary);
        return;
    }
}
