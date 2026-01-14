package Coding.EnumBasedStateMachine;

/**
 * Why Enum Prevents Invalid States better than int constant?
 * A: Because Enum has a private constructor, so we can't create a new instance
 * of it.
 * And we can't change the value of the enum.
 * So we can't set the status to an invalid value.
 * And we can't get the invalid value from the enum.
 * And we can't transition to an invalid state.
 */
public class Main {
    public static void main(String[] args) {

        Package pkg = new Package("PKG-123");

        // Valid transitions
        pkg.updateStatus(DeliveryStatus.PICKED_UP);
        pkg.updateStatus(DeliveryStatus.IN_TRANSIT);
        pkg.updateStatus(DeliveryStatus.DELIVERED);

        // Invalid transition:
        try {
            pkg.updateStatus(DeliveryStatus.IN_TRANSIT);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Demonstrate fromCode()
        DeliveryStatus status = DeliveryStatus.fromCode(3);
        System.out.println("Status from code 3: " + status.getDescription());
    }
}
