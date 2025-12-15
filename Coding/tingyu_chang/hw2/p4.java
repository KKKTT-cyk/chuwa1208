// 1. Abstract Base Class
abstract class Shape {
    // Abstract method: Children must implement this
    public abstract double getArea();
}

// 2. Concrete Class: Rectangle
class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }
}

// 3. Concrete Class: Circle
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

// 4. Main Class
public class MainP4 {
    public static void main(String[] args) {
        // Create an array of Shape objects containing both Rectangles and Circles
        Shape[] shapes = new Shape[3];
        shapes[0] = new Rectangle(4, 5); // Area should be 20
        shapes[1] = new Circle(3);       // Area should be approx 28.27
        shapes[2] = new Rectangle(10, 2); // Area should be 20

        // Iterate and print area for each
        System.out.println("--- Calculating Areas ---");
        for (Shape s : shapes) {
            // Polymorphism: The JVM decides at runtime which getArea() to call
            System.out.printf("Area: %.2f\n", s.getArea());
        }
    }
}
