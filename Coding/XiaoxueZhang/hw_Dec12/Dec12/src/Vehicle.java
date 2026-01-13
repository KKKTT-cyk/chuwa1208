public class Vehicle {
    private String brand;
    private int speed;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public String getBrand() {
        return brand;
    }

    public void displayInfo(){
        System.out.println("this car's brand is "+brand);
        System.out.println("this car's speed is "+speed);
    }
}
class Car extends Vehicle{
    private int numDoors;

    @Override
    public void displayInfo(){
        System.out.println("this car's brand is "+getBrand());
        System.out.println("this car's speed is " +getSpeed());
        System.out.println("this car's number of doors are "+numDoors);
    }

}
class Motorcycle extends Vehicle{
    private boolean hasSidecar;

    @Override
    public void displayInfo(){
        System.out.println("this car's brand is "+getBrand());
        System.out.println("this car's speed is "+getSpeed());
        System.out.println("Does this motor have side car: "+hasSidecar);
    }
}
