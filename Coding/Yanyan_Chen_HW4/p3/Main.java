public class Main {
    public static void main(String[] args) {
        System.out.println("=== Package Delivery State Machine Demo ===\n");
        
        // Test 1: Valid transition sequence
        System.out.println("TEST 1: Valid Transition Sequence");
        System.out.println("-----------------------------------");
        Package pkg1 = new Package("PKG-2024-001", DeliveryStatus.PENDING);
        System.out.println("Created: " + pkg1);
        System.out.println();
        
        try {
            // PENDING → PICKED_UP
            pkg1.updateStatus(DeliveryStatus.PICKED_UP);
            
            // PICKED_UP → IN_TRANSIT
            pkg1.updateStatus(DeliveryStatus.IN_TRANSIT);
            
            // IN_TRANSIT → DELIVERED
            pkg1.updateStatus(DeliveryStatus.DELIVERED);
            
            System.out.println("\nFinal state: " + pkg1);
            System.out.println("All transitions successful!\n");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
        
        // Test 2: Invalid transition - DELIVERED → IN_TRANSIT
        System.out.println("\nTEST 2: Invalid Transition (DELIVERED → IN_TRANSIT)");
        System.out.println("-----------------------------------------------------");
        Package pkg2 = new Package("PKG-2024-002", DeliveryStatus.PENDING);
        System.out.println("Created: " + pkg2);
        System.out.println();
        
        try {
            pkg2.updateStatus(DeliveryStatus.PICKED_UP);
            pkg2.updateStatus(DeliveryStatus.IN_TRANSIT);
            pkg2.updateStatus(DeliveryStatus.DELIVERED);
            System.out.println("\nAttempting invalid transition: DELIVERED → IN_TRANSIT");
            pkg2.updateStatus(DeliveryStatus.IN_TRANSIT); // This should fail
            System.out.println("Transition succeeded!\n");
        } catch (IllegalStateException e) {
            System.out.println("Transition blocked as expected!");
            System.out.println("Error: " + e.getMessage() + "\n");
        }
        
        // Test 3: Demonstrate fromCode() method
        System.out.println("\nTEST 4: Using fromCode() Method");
        System.out.println("---------------------------------");
        try {
            System.out.println("Looking up status codes:");
            for (int i = 1; i <= 5; i++) {
                DeliveryStatus status = DeliveryStatus.fromCode(i);
                System.out.println("  Code " + i + " = " + status.getDescription());
            }
            
            System.out.println("\nAttempting to look up invalid code 99:");
            DeliveryStatus invalidStatus = DeliveryStatus.fromCode(99);
            System.out.println("Invalid code accepted");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
