package p3;

public class Main {
    public static void main(String[] args) {
        // Create a package with PENDING status
        Package pkg = new Package("TEST_PKG01", DeliveryStatus.PENDING);

        // Test valid transitions: PENDING → PICKED_UP → IN_TRANSIT → DELIVERED
        pkg.updateStatus(DeliveryStatus.PICKED_UP);
        pkg.updateStatus(DeliveryStatus.IN_TRANSIT);
        pkg.updateStatus(DeliveryStatus.DELIVERED);

        // Test invalid transition: DELIVERED → IN_TRANSIT (should throw exception)
        try {
            pkg.updateStatus(DeliveryStatus.IN_TRANSIT);
        } catch (IllegalStateException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Demonstrate fromCode() method
        DeliveryStatus status = DeliveryStatus.fromCode(5);
        System.out.println("fromCode() method test:\n" +
                "5: " + status + " - " + status.getDescription());
        try {
            DeliveryStatus.fromCode(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    /* Think: How does Enum help prevent invalid states compared to using int
    * constants?
    *
    * Ans: Because enum could restrict the possible values to a fixed, predefined
    * set, unlike int constants. This provide type safety, meaning that only valid
    * states can be assigned. Hence mking the code more readable, and maintainable
    * */
}
