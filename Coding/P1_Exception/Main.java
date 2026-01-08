package P1_Exception;

public class Main {
    public static void main(String[] args) {
        OrderService svc = new OrderService();
        try{
            svc.processOrder("", 1);
        } catch (OrderException e) {
            System.out.println("Error code: " + e.getErrorCode().getCode() + ", Error msg: " + e.getErrorCode().getMessgae());
        }

        try{
            svc.processOrder("cancelled order", 0);
        } catch (OrderException e) {
            System.out.println("Error code: " + e.getErrorCode().getCode() + ", Error msg: " + e.getErrorCode().getMessgae());
        }

        try{
            svc.processOrder("insufficient stock order", 200);
        } catch (OrderException e) {
            System.out.println("Error code: " + e.getErrorCode().getCode() + ", Error msg: " + e.getErrorCode().getMessgae());
        }
    }
}
