public class Employee {
    private String name;
    private int id;
    private int salary;

    public Employee() {
        this("Foo", 0, 0);
    }

    public Employee(String name, int id) {
        this(name, id, 0.0);
    }

    public Employee(String name, int id, int salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }
}


