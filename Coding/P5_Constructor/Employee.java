package P5_Constructor;
public class Employee {
    String name, id;
    int salary;
    public Employee(){}
    public Employee(String name, String id) {
        this();
        this.name = name;
        this.id = id;
    }
    public Employee(String name, String id, int salary) {
        this(name, id);
        this.salary = salary;
    }
    public void displayInfo() {
        System.out.println("Employee Info:");
        System.out.println(" ** name: " + name);
        System.out.println(" ** id: " + id);
        System.out.println(" ** salary: " + salary);
    }
}
