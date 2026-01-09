package inheritanceDemo.src;
public class Car extends Vehicle {
    private final int numDoors;

    public Car(String brand, int speed, int numDoors) {
        super(brand, speed);
        this.numDoors = numDoors;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Number of doors: " + numDoors);
    }
}
