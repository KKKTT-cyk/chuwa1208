public class OrderService {
    public void processOrder(String orderId, int quantity) {
        // Check if order exists (orders starting with "ORD-" are valid)
        if (orderId == null || !orderId.startsWith("ORD-")) {
            throw new OrderException(OrderErrorCode.ORDER_NOT_FOUND);
        }
        
        // Check if order is cancelled (orders containing "CANCEL" are cancelled)
        if (orderId.contains("CANCEL")) {
            throw new OrderException(OrderErrorCode.ORDER_CANCELLED);
        }
        
        // Check stock availability (quantity > 100 means insufficient stock)
        if (quantity > 100) {
            throw new OrderException(OrderErrorCode.INSUFFICIENT_STOCK);
        }
        
        // If all validations pass, process the order
        System.out.println("Order processed successfully: " + orderId + " with quantity: " + quantity);
    }
}
