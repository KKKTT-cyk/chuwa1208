public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        try {
            orderService.processOrder("", 5);
        } catch (OrderException e) {
            printError(e);
        }

        try {
            orderService.processOrder("CANCELLED", 3);
        } catch (OrderException e) {
            printError(e);
        }

        try {
            orderService.processOrder("ORD-123", 200);
        } catch (OrderException e) {
            printError(e);
        }

        try {
            orderService.processOrder("ORD-456", 3);
        } catch (OrderException e) {
            printError(e);
        }
    }

    private static void printError(OrderException e) {
        IErrorCode code = e.getErrorCode();
        System.out.println("Error Code: " + code.getCode() + ", Message: " + code.getMessage());
    }
}
