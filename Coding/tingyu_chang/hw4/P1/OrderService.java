package p1;

/*
- Create `OrderService` class with method:
  - `public void processOrder(String orderId, int quantity)`
  - Throw appropriate `OrderException` based on conditions
 */
public class OrderService {
    public void processOrder(String orderId, int quantity) {
        // Simulating error conditions
        if ("000".equals(orderId)) {
            throw new OrderException(OrderErrorCode.ORDER_NOT_FOUND);
        }

        if ("999".equals(orderId)) {
            throw new OrderException(OrderErrorCode.ORDER_CANCELLED);
        }

        if (quantity > 100) {
            throw new OrderException(OrderErrorCode.INSUFFICIENT_STOCK);
        }

        System.out.println("Order " + orderId + " processed successfully.");
    }
}
