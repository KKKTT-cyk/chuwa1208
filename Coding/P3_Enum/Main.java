package P3_Enum;

public class Main {
    public static void main(String[] args) {

        Package pkg = new Package("pkg-001", DeliveryStatus.PENDING);

        // Invalid transition
        try {
            // Valid transitions
            pkg.updateStatus(DeliveryStatus.PICKED_UP);
            pkg.updateStatus(DeliveryStatus.IN_TRANSIT);
            pkg.updateStatus(DeliveryStatus.DELIVERED);
            pkg.updateStatus(DeliveryStatus.IN_TRANSIT);
        } catch (IllegalStateException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        // fromCode()
        DeliveryStatus status = DeliveryStatus.fromCode(3);
        System.out.println("fromCode(3): " + status + " - " + status.getDescription());

        try {
            DeliveryStatus.fromCode(99);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
