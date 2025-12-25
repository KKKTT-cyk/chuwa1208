public class Vehicle {
    private String brand;
    private int speed;

    public Vehicle(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    public void displayInfo() {
        System.out.println("--- Vehicle Information ---");
        System.out.println("Brand: " + brand);
        System.out.println("Speed: " + speed + " mph");
    }
}