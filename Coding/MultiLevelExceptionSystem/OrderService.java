package Coding.MultiLevelExceptionSystem;

public class OrderService {
    public void processOrder(String orderId, int quantity) {
        if (orderId == null || orderId.isEmpty()) {
            throw new OrderException(OrderErrorCode.ORDER_NOT_FOUND);
        }

        if ("CANCELLED".equals(orderId)) {
            throw new OrderException(OrderErrorCode.ORDER_CANCELLED);
        }

        if (quantity > 10) {
            throw new OrderException(OrderErrorCode.INSUFFICIENT_STOCK);
        }

        System.out.println("Order processed successfully for orderId=" + orderId);
    }
}
