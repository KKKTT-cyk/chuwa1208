public class Motorcycle extends Vehicle{
    // field
    private boolean hasSidecar;

    // constructor
    public Motorcycle(String brand, int speed, boolean hasSidecar){
        super(brand, speed);
        this.hasSidecar = hasSidecar;
    }


    // displayinfo
    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("Has Sidecar" + hasSidecar);
    }
}