public class Main {

    public static void main(String[] args) {

        Package pkg = new Package("PKG123");

        pkg.updateStatus(DeliveryStatus.PICKED_UP);
        pkg.updateStatus(DeliveryStatus.IN_TRANSIT);
        pkg.updateStatus(DeliveryStatus.DELIVERED);

        try {
            pkg.updateStatus(DeliveryStatus.IN_TRANSIT);
        } catch (IllegalStateException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        DeliveryStatus status = DeliveryStatus.fromCode(3);
        System.out.println("Status from code 3: " + status.getDescription());
    }
}

// How does Enum help prevent invalid states compared to using int constants?
// Enums provide type safety and encapsulate both state and behavior, preventing invalid states and transitions that are common when using primitive constants like int.
