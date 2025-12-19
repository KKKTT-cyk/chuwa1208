package Coding.MultiLevelExceptionSystem;

public class Main {
    public static void main(String[] args) {
        OrderService service = new OrderService();

        try {
            service.processOrder(null, 2);
        } catch (OrderException e) {
            printError(e);
        }

        try {
            service.processOrder("CANCELLED", 1);
        } catch (OrderException e) {
            printError(e);
        }

        try {
            service.processOrder("ORD123", 20);
        } catch (OrderException e) {
            printError(e);
        }

        try {
            service.processOrder("ORD124", 2);
        } catch (OrderException e) {
            printError(e);
        }
    }

    private static void printError(OrderException e) {
        System.err.println("Error Code: " + e.getErrorCode().getCode());
        System.err.println("Error Message: " + e.getErrorCode().getMessage());
    }
}
