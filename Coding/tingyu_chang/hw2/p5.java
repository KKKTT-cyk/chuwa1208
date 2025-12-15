class Employee {

    private String name;
    private int id;
    private double salary;

    // 1. Default constructor (sets default values)
    public Employee() {
        // Calls the 3-parameter constructor with default values
        // Use this() to call one constructor from another
        this("Unknown", 0, 0.0);
        System.out.println("Default Constructor called");
    }
    // 2. Constructor with name and id parameters (sets salary to 0)
    public Employee(String name, int id) {
        // Calls the 3-parameter constructor
        // Use this() to call one constructor from another
        this(name, id, 0.0);
        System.out.println("2-Argument Constructor called");
    }

    // 3. Constructor with all three parameters (the main initial logic)
    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        System.out.println("3-Argument Constructor called");
    }

    // Display method to verify values
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: $" + salary);
    }

   // Demonstrate creating objects using all three constructors
    public class MainP5 {
        public static void main(String[] args) {
            System.out.println("--- Creating Employee 1 (Default) ---");
            Employee e1 = new Employee();
            e1.display();

            System.out.println("\n--- Creating Employee 2 (Name, ID) ---");
            Employee e2 = new Employee("Alice", 101);
            e2.display();

            System.out.println("\n--- Creating Employee 3 (All Details) ---");
            Employee e3 = new Employee("Bob", 102, 75000.00);
            e3.display();
        }
    }

}