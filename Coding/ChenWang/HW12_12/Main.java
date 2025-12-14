public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.add(5, 10));
        System.out.println(calculator.add(5, 10, 15));
        System.out.println(calculator.add(5.5, 10.8));

        Shape[] shapes = {
                new Rectangle(3, 4),
                new Circle(5),
                new Rectangle(6, 7),
                new Circle(8)
        };
        for (Shape shape: shapes) {
            System.out.println("Area: " + shape.getArea());
        }

        Employee[] employees = {
                new Employee(),
                new Employee("David", "001"),
                new Employee("Tom", "002", 100000)
        };
        for (Employee employee: employees) {
            employee.displayEmployee();
        }
    }
}
