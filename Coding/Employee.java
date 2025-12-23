package Coding;

public class Employee {
    private int id;
    private String name;
    private double salary;

    // constructor
    public Employee() {
        this.id = 0;
        this.name = "";
        this.salary = 0;
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
        this.salary = 0;
    }

    public Employee(int id, String name, double salary) {
        this(id, name);
        this.salary = salary;
    }

    // Methods
    public String displayInfo() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}
