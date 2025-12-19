public class Motorcycle extends Vehicle {
    private boolean hasSideCar;
    public Motorcycle(String brand, int speed, boolean hasSideCar) {
        super(brand, speed)
        this.hasSideCar = hasSideCar;
    }

    @Override
    public void displayInfo() {
        System.out.println(
                "Motorcycle - Brand: " + brand +
                        ", Speed: " + speed +
                        ", Has Sidecar: " + hasSidecar
        );
    }
}