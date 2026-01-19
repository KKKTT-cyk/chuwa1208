package P1;
/*
Call processOrder with different scenarios
Catch exceptions and print error code and message
 */
public class Main {
    public static void main(String[] args) {
        OrderService service = new OrderService();
        try{
            service.processOrder(null, 2);
        }catch (OrderException e){
            printError(e);
        }
        try {
            service.processOrder("CANCELLED", 1);
        }catch (OrderException e){
            printError(e);
        }
        try {
            service.processOrder("123-456-789", 101);
        }catch (OrderException e){
            printError(e);
        }
    }
    private static void printError(OrderException e){
        System.out.println("Error code" + e.getErrorCode().getCode() +
                " Message " + e.getErrorCode().getMessage()
        );
    }
}
