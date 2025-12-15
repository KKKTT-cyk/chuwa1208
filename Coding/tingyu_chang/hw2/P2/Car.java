// Derived Class: Car
public class Car extends Vehicle {
    private int numDoors;

    public Car(String brand, double speed, int numDoors) {
        super(brand, speed); // Call the constructor of the parent (Vehicle)
        this.numDoors = numDoors;
    }

    @Override
    public void displayInfo() {
        super.displayInfo(); // Reuse the logic from the parent class
        System.out.println("Type: Car");
        System.out.println("Number of Doors: " + numDoors);
    }
}