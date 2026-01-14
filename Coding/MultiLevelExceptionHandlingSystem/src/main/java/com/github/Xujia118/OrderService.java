package com.github.Xujia118;

public class OrderService {
    public void processOrder(String orderId, int quantity) {
        if (orderId == null) {
            throw new OrderException(OrderErrorCode.ORDER_NOT_FOUND);
        }

        if (quantity <= 0) {
            throw new OrderException(OrderErrorCode.INSUFFICIENT_STOCK);
        }
    }
}
