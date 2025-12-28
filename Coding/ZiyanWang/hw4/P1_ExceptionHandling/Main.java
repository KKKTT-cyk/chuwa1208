public class Main {
    public static void main(String[] args) {

        OrderService service = new OrderService();

        try {
            service.processOrder("123", 20);
        } catch (OrderException e) {
            System.out.println(
                    "Error Code: " + e.getErrorCode().getCode() +
                            ", Message: " + e.getErrorCode().getMessage()
            );
        }

        try {
            service.processOrder("CANCELLED", 1);
        } catch (OrderException e) {
            System.out.println(
                    "Error Code: " + e.getErrorCode().getCode() +
                            ", Message: " + e.getErrorCode().getMessage()
            );
        }
    }
}

// Why use an interface instead of directly using the enum in the exception class?
// Using an interface allows the exception to be decoupled from specific enum implementations, making the system more extensible and aligned with the open–closed principle.
