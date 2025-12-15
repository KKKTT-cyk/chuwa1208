// Base Class
public class Vehicle {
    protected String brand; // 'protected' allows subclasses to access this field directly
    protected double speed;

    public Vehicle(String brand, double speed) {
        this.brand = brand;
        this.speed = speed;
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Speed: " + speed + " mph");
    }
}