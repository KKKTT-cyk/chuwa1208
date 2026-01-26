public class OrderService {
    public void processOrder(String orderId, int quantity) {
        if (orderId == null || orderId.isBlank()) {
            throw new OrderException(OrderErrorCode.ORDER_NOT_FOUND);
        }

        if (quantity <= 0) {
            throw new OrderException(OrderErrorCode.ILLEGALARGUMENT);
        }

        if (quantity > 100) {
            throw new OrderException(OrderErrorCode.INSUFFICIENT_STOCK);
        }

        if (orderId.equalsIgnoreCase("cancelled")) {
            throw new OrderException(OrderErrorCode.ORDER_CANCELLED);
        }
    }
}
