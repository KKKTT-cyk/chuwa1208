package p4;

public class ShapeTester {
    public static void main (String[] args) {
        //  create an array of Shape objects containing both rectangles and circles
        Shape[] shapes = new Shape[] {
                new Rectangle(3.6, 4.8),
                new Circle(2.5),
                new Rectangle(5, 6),
                new Circle(1)
        };

        // calculate and print the area of each
        for (Shape shape : shapes) {
            System.out.println("Area: " + shape.getArea());
        }
    }
}
