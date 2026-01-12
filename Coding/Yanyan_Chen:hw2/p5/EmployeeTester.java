public class EmployeeTester {
    public static void main(String[] args) {
        Employee e1 = new Employee();
        Employee e2 = new Employee("Natalie Portman", 10001);
        Employee e3 = new Employee("Owen Cooper", 10002, 500000);

        System.out.println("\n--- Display Employees Info Using Default Constructor---");
        e1.display();
        System.out.println("\n--- Display Employees Info Using Default Constructor---");
        e2.display();
        System.out.println("\n--- Display Employees Info Using Default Constructor---");
        e3.display();
        System.out.println("\nEmployees created successfully");
    }
}
