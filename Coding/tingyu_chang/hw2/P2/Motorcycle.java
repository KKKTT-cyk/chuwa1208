// Derived Class: Motorcycle
public class Motorcycle extends Vehicle {
    private boolean hasSidecar;

    public Motorcycle(String brand, double speed, boolean hasSidecar) {
        super(brand, speed); // Call the constructor of the parent (Vehicle)
        this.hasSidecar = hasSidecar;
    }

    @Override
    public void displayInfo() {
        super.displayInfo(); // Reuse the logic from the parent class
        System.out.println("Type: Motorcycle");
        System.out.println("Has Sidecar: " + (hasSidecar ? "Yes" : "No"));
    }
}
