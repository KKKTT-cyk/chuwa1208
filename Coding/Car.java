package Coding;

public class Car extends Vehicle {
    private int numDoors;

    // Constructor
    public Car(String brand, String speed, int numDoors) {
        super(brand, speed);
        this.numDoors = numDoors;
    }

    // Methods
    @Override
    public void displayInfo() {
        System.out.println("Brand: " + getBrand() + ", Speed: " + getSpeed() + ", Number of Doors: " + numDoors);
    }

}
