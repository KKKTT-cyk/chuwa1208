package p1;

/*
- In `Main`:
  - Call `processOrder` with different scenarios
  - Catch exceptions and print error code and message
- Think: Why use an interface instead of directly using the enum in the exception class?

 */
public class Main {
    public static void main(String[] args) {
        OrderService service = new OrderService();

        runTest(service, "000", 10);   // Test Not Found
        runTest(service, "999", 10);   // Test Cancelled
        runTest(service, "123", 500);  // Test Insufficient Stock
        runTest(service, "123", 50);   // Test Success
    }

    public static void runTest(OrderService service, String id, int qty) {
        try {
            service.processOrder(id, qty);
        } catch (OrderException e) {
            // Retrieve specific error code info from the exception
            System.out.println("Error [" + e.getErrorCode().getCode() + "]: " + e.getErrorCode().getMessage());
        }
    }
}

/* THINK: Why use an interface instead of directly using the enum?
   Answer: Decoupling and Extensibility.
   If 'OrderException' depended directly on 'OrderErrorCode', it could only throw order-related errors.
   By using 'IErrorCode', the same exception class can accept any enum that implements the interface
   (e.g., UserErrorCode, SystemErrorCode, PaymentErrorCode). This allows us to reuse one exception
   class across the entire application.
*/

