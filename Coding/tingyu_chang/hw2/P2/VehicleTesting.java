//  Test P2
public class VehicleTesting {
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