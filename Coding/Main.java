public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[2];
        shapes[0] = new Circle(5);
        shapes[1] = new Rectangle(5, 10);

        for (Shape s : shapes){
            System.out.println("Area: " + s.getArea());
        }
    }
}
