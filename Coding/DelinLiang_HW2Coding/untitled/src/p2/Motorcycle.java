package p2;

public class Motorcycle extends Vehicle{
    // adds a field hasSidecar
    private boolean hasSidecar;

    public Motorcycle(String brand, int speed, boolean hasSidecar) {
        super(brand, speed);
        this.hasSidecar = hasSidecar;
    }

    // Override displayInfo() in both derived classes to include their specific information
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Sidecar: " + (hasSidecar ? "Y" : "N"));
    }
}
