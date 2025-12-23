package Coding;

public class Vehicle {
    private String brand;
    private String speed;

    // Constructor
    public Vehicle(String brand, String speed) {
        this.brand = brand;
        this.speed = speed;
    }

    // Getter and Setter
    public String getBrand() {
        return brand;
    }

    public String getSpeed() {
        return speed;
    }

    // Methods
    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Speed: " + speed);
    }
}