public class Employee {
    private String name;
    private String id;
    private double salary;

    public Employee() {
        this("N/A", "000", 0);
    }

    public Employee(String name, String id) {
        this(name, id, 0);
    }

    public Employee(String name, String id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public void displayEmployee() {
        System.out.println("Employee name: " + name + ", id: " + id + ", salary: " + salary);
        return;
    }
}
