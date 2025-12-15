package P5_Constructor;
public class Main {
    public static void main(String[] args) {
        Employee[] employees = {
                new Employee(),
                new Employee("Tom Dwan", "001"),
                new Employee("Tan Xuan", "002", 100000)
        };
        for (Employee employee: employees) {
            employee.displayInfo();
        }
    }
}
