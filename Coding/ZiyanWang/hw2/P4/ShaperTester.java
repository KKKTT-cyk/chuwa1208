public class ShapeTester {

    public static void main(String[] args) {

        Shape[] shapes = new Shape[] {
                new Rectangle(4, 5),
                new Circle(3)
        };

        for (Shape s : shapes) {
            System.out.println(s.getArea());
        }
    }
}