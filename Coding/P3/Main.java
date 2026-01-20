package P3;
/*
In Main:
    Create a package with PENDING status
    Test valid transitions: PENDING → PICKED_UP → IN_TRANSIT → DELIVERED
    Test invalid transition: DELIVERED → IN_TRANSIT (should throw exception)
    Demonstrate fromCode() method
 */
public class Main {
    public static void main(String[] args) {
        Package pkg = new Package("PKg-101");
        System.out.println("Initial: " + pkg.getStatus());
        pkg.updateStatus(DeliverStatus.PICKED_UP);
        pkg.updateStatus(DeliverStatus.IN_TRANSIT);
        pkg.updateStatus(DeliverStatus.DELIVERED);

        // try invalid transition case
        try{
            pkg.updateStatus(DeliverStatus.IN_TRANSIT);
        }catch (IllegalStateException e){
            System.out.println("Catch error" + e.getMessage());
        }

        // try fromCode
        DeliverStatus s = DeliverStatus.fromCode(3);
        System.out.println("formCode (3) => " + s + s.getDescription());

        // invalid code
        try{
            DeliverStatus.fromCode(99);
        }catch (IllegalArgumentException e){
            System.out.println("Catch Error: " + e.getMessage());
        }

    }
}
