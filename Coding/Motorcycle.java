package Coding;

public class Motorcycle extends Vehicle {
    private boolean hasSidecar;

    // Constructor
    public Motorcycle(String brand, String speed, boolean hasSidecar) {
        super(brand, speed);
        this.hasSidecar = hasSidecar;
    }

    // Methods
    @Override
    public void displayInfo() {
        System.out.println("Brand: " + getBrand() + ", Speed: " + getSpeed() + ", Has Sidecar: " + hasSidecar);
    }
}
