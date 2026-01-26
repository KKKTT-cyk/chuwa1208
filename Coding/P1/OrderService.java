package P1;
/*
Create OrderService class with method:
    public void processOrder(String orderId, int quantity)
    Throw appropriate OrderException based on conditions
 */
public class OrderService {

    // defines OrderException conditions
    public void processOrder(String orderId, int quantity){
        if (orderId == null || orderId.isEmpty()){
            throw new OrderException(OrderErrorCode.ORDER_NOT_FOUND);
        }
        if (quantity > 100){
            throw new OrderException(OrderErrorCode.INSUFFICIENT_STOCK);
        }
        if ("CANCELLED".equals(orderId)){
            throw  new OrderException(OrderErrorCode.ORDER_CANCELLED);
        }
        System.out.println("Order Process successfully Oder ID" + orderId);
    }
}
