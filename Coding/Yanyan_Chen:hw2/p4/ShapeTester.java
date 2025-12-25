public class ShapeTester {

    public static void main(String[] args) {
        Shape[] shapes = new Shape[4];

        shapes[0] = new Rectangle(4, 5);
        shapes[1] = new Circle(3);
        shapes[2] = new Rectangle(8, 10);
        shapes[3] = new Circle(10);

        for (Shape shape : shapes) {
            System.out.println("Area: " + shape.getArea());
        }
    }
}