public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        
        System.out.println("=== Order Processing Exception Handling Demo ===\n");
        
        // Scenario 1: Successful order processing
        System.out.println("Scenario 1: Valid order");
        try {
            orderService.processOrder("ORD-12345", 50);
        } catch (OrderException e) {
            printError(e);
        }
        System.out.println();
        
        // Scenario 2: Order not found
        System.out.println("Scenario 2: Order not found");
        try {
            orderService.processOrder("INVALID-123", 50);
        } catch (OrderException e) {
            printError(e);
        }
        System.out.println();
        
        // Scenario 3: Order already cancelled
        System.out.println("Scenario 3: Order already cancelled");
        try {
            orderService.processOrder("ORD-CANCEL-789", 30);
        } catch (OrderException e) {
            printError(e);
        }
        System.out.println();
        
        // Scenario 4: Insufficient stock
        System.out.println("Scenario 4: Insufficient stock");
        try {
            orderService.processOrder("ORD-99999", 150);
        } catch (OrderException e) {
            printError(e);
        }
        System.out.println();
        
        // Scenario 5: Null order ID
        System.out.println("Scenario 5: Null order ID");
        try {
            orderService.processOrder(null, 20);
        } catch (OrderException e) {
            printError(e);
        }
        System.out.println();
    }

    private static void printError(OrderException e) {
        System.out.println("[Error occurred!]");
        System.out.println("   Error Code: " + e.getErrorCode().getCode());
        System.out.println("   Error Message: " + e.getErrorCode().getMessage());
    }
}
