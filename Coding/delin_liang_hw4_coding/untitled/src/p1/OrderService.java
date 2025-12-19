package p1;

public class OrderService {
    private final int currentStock = 50;

    public void processOrder(String orderId, int quantity) {

        System.out.println("Order received: Id:" + orderId + " quantity:" + quantity
        + "\nProcessing...");

        // Throw appropriate OrderException based on conditions
        if (orderId == null || orderId.isEmpty()) {
            throw new OrderException(OrderErrorCode.ORDER_NOT_FOUND);
        }

        if (quantity == 0) {
            throw new OrderException(OrderErrorCode.ORDER_CANCELLED);
        }

        if (quantity > currentStock) {
            throw new OrderException(OrderErrorCode.INSUFFICIENT_STOCK);
        }

        System.out.println("Order processed successfully!");
    }
}
