public class Main {
    public static void main(String[] args) {
        OrderService service = new OrderService();

        try {
            service.processOrder("", 1);
        } catch (OrderException e) {
            System.out.println(e.getErrorCode().getCode() + ": " +e.getErrorCode().getMessage());
        }

        try {
            service.processOrder("001", -5);
        } catch (OrderException e) {
            System.out.println(e.getErrorCode().getCode() + ": " +e.getErrorCode().getMessage());
        }

        try {
            service.processOrder("001", 10000);
        } catch (OrderException e) {
            System.out.println(e.getErrorCode().getCode() + ": " +e.getErrorCode().getMessage());
        }

        try {
            service.processOrder("cancelled", 1);
        } catch (OrderException e) {
            System.out.println(e.getErrorCode().getCode() + ": " +e.getErrorCode().getMessage());
        }
    }
}
