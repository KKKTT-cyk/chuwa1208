package Coding;

public class main {
    public static void main(String[] args) {
        displayVehicleInfo();
        useCalculator();
        useShape();
        useEmployee();
    }

    private static void displayVehicleInfo() {
        Vehicle car = new Car("Toyota", "100 km/h", 4);
        car.displayInfo();
        Vehicle motorcycle = new Motorcycle("Honda", "120 km/h", true);
        motorcycle.displayInfo();
    }

    private static void useCalculator() {
        Calculator calculator = new Calculator();
        System.out.println(calculator.add(1, 2));
        System.out.println(calculator.add(1, 2, 3));
        System.out.println(calculator.add(1.5, 2.5));
    }

    private static void useShape() {
        Shape rectangle = new Rectangle(10, 20);
        System.out.println(rectangle.getArea());
        Shape circle = new Circle(10);
        System.out.println(circle.getArea());
    }

    private static void useEmployee() {
        Employee employee1 = new Employee(1, "John", 1000);
        System.out.println(employee1.displayInfo());
        Employee employee2 = new Employee(2, "Jane");
        System.out.println(employee2.displayInfo());
        Employee employee3 = new Employee();
        System.out.println(employee3.displayInfo());
    }
}