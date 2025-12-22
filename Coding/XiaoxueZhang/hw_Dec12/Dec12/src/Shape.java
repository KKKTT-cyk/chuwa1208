abstract class Shape {
    abstract double getArea();
}

class Rectangle extends Shape{
    private double width;
    private double height;

    public Rectangle(double width, double height){
        this.width=width;
        this.height=height;
    }

    public double getArea(){
        return width*height;
    }
}

class Circle extends Shape{
    private double radius;

    public Circle (double radius){
        this.radius = radius;
    }
    public double getArea(){
        return Math.PI * radius * radius;
    }
}