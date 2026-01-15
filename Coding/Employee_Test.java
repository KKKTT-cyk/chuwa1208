public class Employee_Test {
    public static void main(String[] args) {
        Employee e1 = new Employee();
        Employee e2 = new Employee("John", 1);
        Employee e3 = new Employee("John", 1, 1000);


        e1.printInfo();
        e2.printInfo();
        e3.printInfo();
    }
}
