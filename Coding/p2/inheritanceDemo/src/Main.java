package inheritanceDemo.src;
public class Main {
    public static void main(String[] args) {
       Car myCar = new Car("Toyota", 120, 4);
       Motorcycle myMotorcycle = new Motorcycle("Harley-Davidson", 200, true);

       myCar.displayInfo();
       myMotorcycle.displayInfo();
    }
}