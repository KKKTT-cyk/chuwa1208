package P1_Exception;

public class OrderService {
    public void processOrder(String orderId, int quantity) {

        System.out.println("Processing new order: id = " + orderId + ", quantity = " + quantity );

        if(orderId.isEmpty()){
            System.out.println("Order id is empty");
            throw new OrderException(OrderErrorCode.ORDER_NOT_FOUND);
        }

        if(quantity == 0) {
            System.out.println("Order quantity equals to 0");
            throw new OrderException((OrderErrorCode.ORDER_CANCELLED));
        }

        if(quantity > 100) {
            System.out.println("Order quantity is greater than 100");
            throw new OrderException(OrderErrorCode.INSUFFICIENT_STOCK);
        }

        System.out.println("Order processed successfully");
    }
}
