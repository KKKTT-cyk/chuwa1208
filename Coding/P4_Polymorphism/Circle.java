package P4_Polymorphism;
public class Circle extends Shape{
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        System.out.println("get Area for Circle with radius = " + radius);
        return Math.PI * radius * radius;
    }
}
