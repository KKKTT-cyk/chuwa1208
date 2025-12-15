package p2;

public class Car extends Vehicle {
    // adds a field numDoors
    private int numDoors;

    public Car(String brand, int speed, int numDoors) {
        super(brand, speed);
        this.numDoors = numDoors;
    }

    // Override displayInfo() in both derived classes to include their specific information
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Number of Doors: " + numDoors);
    }

}
