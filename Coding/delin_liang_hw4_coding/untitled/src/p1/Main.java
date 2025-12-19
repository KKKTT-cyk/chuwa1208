package p1;

public class Main {
    public static void main(String[] args) {
        // Call processOrder with different scenarios
        OrderService service = new OrderService();

        test(service, null, 1); // not found
        test(service, "CANCELLED", 0); // cancelled
        test(service, "OUT OF STOCK", 200); // out of stock
        test(service, "ORDER112358", 2); // normal case
    }

    private static void test(OrderService service, String orderId, int quantity) {
        try {
            service.processOrder(orderId, quantity);
        }
        catch (OrderException e) {
            System.out.println("Failed to place order!\n" +
                    "Error Code: " + e.getErrorCode().getCode() +
                    ", Message: " + e.getErrorCode().getMessage());
        }
    }

    /*
    * Think: Why use an interface instead of directly using the enum in
    * the exception class?
    *
    * Ans: Using an interface decouples the exception from concrete error
    * code implementations and improves extensibility
    * */
}
