public class Vehicle {
    // field
    private String brand;
    private int speed;

    //constructor
    public Vehicle (String brand, int speed){
        this.brand = brand;
        this.speed = speed;
    }

    // displayInfo
    public void displayInfo(){
        System.out.println("Brand" + brand + "Maxspeed" + speed);
    }
}