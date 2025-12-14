public class App {
    public static void main(String[] args) throws Exception {

        Employee e1 = new Employee();                                        // default constructor
        Employee e2 = new Employee("Alice", 101);                   // name + id
        Employee e3 = new Employee("Bob", 102, 75000);      // all parameters

        e1.displayEmployeeInfo();
        e2.displayEmployeeInfo();
        e3.displayEmployeeInfo();
    
    }
}
