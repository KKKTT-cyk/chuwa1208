package P4_Polymorphism;
public class Rectangle extends Shape{
    double width, height;

     public Rectangle(double width, double height) {
         this.height = height;
         this.width = width;
     }

    @Override
    public double getArea() {
        System.out.println("get Area for Rectangle with width = " + width + ", height = " + height);
        return width * height;
    }
}
