// 1. Base Class
class Vehicle {
    protected String brand; // 'protected' allows subclasses to access this field directly
    protected double speed;

    public Vehicle(String brand, double speed) {
        this.brand = brand;
        this.speed = speed;
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Speed: " + speed + " mph");
    }
}

// 2. Derived Class: Car
class Car extends Vehicle {
    private int numDoors;

    public Car(String brand, double speed, int numDoors) {
        super(brand, speed); // Call the constructor of the parent (Vehicle)
        this.numDoors = numDoors;
    }

    @Override
    public void displayInfo() {
        super.displayInfo(); // Reuse the logic from the parent class
        System.out.println("Number of Doors: " + numDoors);
    }
}

// 3. Derived Class: Motorcycle
class Motorcycle extends Vehicle {
    private boolean hasSidecar;

    public Motorcycle(String brand, double speed, boolean hasSidecar) {
        super(brand, speed); // Call the constructor of the parent (Vehicle)
        this.hasSidecar = hasSidecar;
    }

    @Override
    public void displayInfo() {
        super.displayInfo(); // Reuse the logic from the parent class
        System.out.println("Has Sidecar: " + (hasSidecar ? "Yes" : "No"));
    }
}

// 4. Main Class to Test P2
public class Main {
    public static void main(String[] args) {
        // Polymorphism: Reference is Vehicle, Object is Car
        Vehicle myCar = new Car("Toyota", 120, 4);
        myCar.displayInfo();

        System.out.println("----------------");

        // Polymorphism: Reference is Vehicle, Object is Motorcycle
        Vehicle myMoto = new Motorcycle("Harley", 100, false);
        myMoto.displayInfo();
    }
}
