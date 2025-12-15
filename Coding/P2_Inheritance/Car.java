package P2_Inheritance;
class Car extends Vehicle {
    int numDoors;

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("   * numDoors: " + numDoors);
    }
}