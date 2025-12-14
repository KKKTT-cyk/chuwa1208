public class Vehicle {
    String brand;
    double speed;

    public void displayInfo() {
        System.out.println("Vehicle brand: " + brand + ", speed: " + speed);
    }
}

class Car extends Vehicle {
    int numDoors;

    @Override
    public void displayInfo() {
        System.out.println("Vehicle brand: " + brand + ", speed: " + speed + ", number of doors: " + numDoors);
    }
}

class Motorcycle extends Vehicle {
    boolean hasSidecar;

    @Override
    public void displayInfo() {
        if (hasSidecar) {
            System.out.println("Vehicle brand: " + brand + ", speed: " + speed + ", with side car.");
        } else {
            System.out.println("Vehicle brand: " + brand + ", speed: " + speed + ", without side car.");
        }
    }
}