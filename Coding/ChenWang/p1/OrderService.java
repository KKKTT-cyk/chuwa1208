public class OrderService {
    public void processOrder(String orderId, int quantity) {
        if (orderId == null || orderId.trim().isEmpty() || quantity <= 0) {
            throw new OrderException(OrderErrorCode.ORDER_NOT_FOUND);
        }

        if ("CANCELLED".equalsIgnoreCase(orderId)) {
            throw new OrderException(OrderErrorCode.ORDER_CANCELLED);
        }

        if (quantity > 199) {
            throw new OrderException(OrderErrorCode.INSUFFICIENT_STOCK);
        }

        System.out.println("Order processed successfully");
    }
}
