package P4_Polymorphism;
public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Rectangle(3.0, 4),
                new Circle(2.0),
        };
        for (Shape shape: shapes) {
            System.out.println(" -- Area = " + shape.getArea());
        }
    }
}
