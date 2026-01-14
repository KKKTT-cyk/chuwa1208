public class Main {
    public static void main(String[] args) {
        Package pkg = new Package("PKG-123");
        System.out.println("Initial status: " + pkg.getStatus());

        pkg.updateStatus(DeliveryStatus.PICKED_UP);
        pkg.updateStatus(DeliveryStatus.IN_TRANSIT);
        pkg.updateStatus(DeliveryStatus.DELIVERED);

        try {
            pkg.updateStatus(DeliveryStatus.IN_TRANSIT);
        } catch (IllegalStateException e) {
            System.out.println("Caught invalid transition: " + e.getMessage());
        }

        DeliveryStatus statusFromCode = DeliveryStatus.fromCode(3);
        System.out.println("Status from code 3: " + statusFromCode + " (" + statusFromCode.getDescription() + ")");

        try {
            DeliveryStatus.fromCode(999);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught invalid code: " + e.getMessage());
        }
    }
}
