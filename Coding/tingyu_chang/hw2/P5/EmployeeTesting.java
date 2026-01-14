// Demonstrate creating objects using all three constructors
public class EmployeeTesting {
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