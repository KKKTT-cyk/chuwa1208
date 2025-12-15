package P2_Inheritance;
public class Motorcycle extends Vehicle{
    boolean hasSidecar;

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("   * hasSidecar: " + hasSidecar);
    }
}
