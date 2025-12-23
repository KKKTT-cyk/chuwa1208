package p3;

/*
- In `Main`:
  - Create a package with PENDING status
  - Test valid transitions: PENDING → PICKED_UP → IN_TRANSIT → DELIVERED
  - Test invalid transition: DELIVERED → IN_TRANSIT (should throw exception)
  - Demonstrate `fromCode()` method
- Think: How does Enum help prevent invalid states compared to using int constants?
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("--- 1. Testing Creation (Pending Status) ---");
        // 1. Create a package (Constructor sets it to PENDING automatically)
        Package pkg = new Package("PKG-1001");

        // Verify the initial status explicitly in Main
        if (pkg.getStatus() == DeliveryStatus.PENDING) {
            System.out.println("Verified: New package started in PENDING state.");
        }

        System.out.println("\n--- 2. Testing Valid Transitions ---");
        try {
            // PENDING -> PICKED_UP
            pkg.updateStatus(DeliveryStatus.PICKED_UP);

            // PICKED_UP -> IN_TRANSIT
            pkg.updateStatus(DeliveryStatus.IN_TRANSIT);

            // IN_TRANSIT -> DELIVERED
            pkg.updateStatus(DeliveryStatus.DELIVERED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n--- 3. Testing Invalid Transition ---");
        try {
            // Business Logic: Cannot go from DELIVERED back to IN_TRANSIT
            pkg.updateStatus(DeliveryStatus.IN_TRANSIT);
        } catch (Exception e) {
            System.out.println("Error caught: " + e.getMessage());
        }

        System.out.println("\n--- 4. Testing Static Lookup ---");
        try {
            DeliveryStatus status = DeliveryStatus.fromCode(3);
            System.out.println("Code 3 is: " + status.getDescription());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

/* THINK: How does Enum help prevent invalid states compared to using int constants?
   Answer:
   1. Type Safety: You cannot pass an arbitrary integer (like 99) to a method expecting a DeliveryStatus.
      You must pass a valid enum constant.
   2. Centralized Logic: The transition rules (canTransitionTo) are encapsulated inside the Enum itself,
      rather than scattered across helper classes.
   3. Readability: Code uses meaningful names (IN_TRANSIT) instead of magic numbers (3).
*/
