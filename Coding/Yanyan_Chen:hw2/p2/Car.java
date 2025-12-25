public class Car extends Vehicle {
    private int numDoors;

    public Car(String brand, int speed, int numDoors) {
        super(brand, speed);
        this.numDoors = numDoors;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Car");
        System.out.println("Number of Doors: " + numDoors);
    }
}