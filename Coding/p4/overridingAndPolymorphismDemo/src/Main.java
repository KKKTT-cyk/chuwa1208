public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[4];
        shapes[0] = new Rectangle(3, 4);
        shapes[1] = new Circle(5);
        shapes[2] = new Rectangle(6, 7);
        shapes[3] = new Circle(2.5);

        for (Shape shape : shapes) {
            System.out.println("Area: " + shape.getArea());
        }

    }
}