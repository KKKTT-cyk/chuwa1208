package p2;

public class Vehicle {
    // fields brand and speed
    private String brand;
    private int speed;

    public Vehicle(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    //  method displayInfo()
    public void displayInfo() {
        System.out.println("Brand: " + brand + "\nSpeed: " + speed + " mph");
    }
}
