public class VehicleTester {
    public static void main(String[] args) {

        System.out.println("--- Testing Car Class ---");
        Car myCar = new Car("Lamborghini", 125, 4);
        myCar.displayInfo();

        System.out.println("\n--- Testing Motorcycle Class ---");
        Motorcycle myBike = new Motorcycle("Yamaha", 100, true);
        myBike.displayInfo();

        System.out.println("\n--- Testing Polymorphism ---");
        Vehicle genericVehicle1 = myCar;
        Vehicle genericVehicle2 = myBike;

        genericVehicle1.displayInfo();
        genericVehicle2.displayInfo();
    }
}