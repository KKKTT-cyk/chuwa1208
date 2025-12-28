public class Main {
    public static void main(String[] args) {
        try (LogWriter log = new LogWriter("hw4.log");
             DataProcessor processor = new DataProcessor("orders.csv")) {

            log.writeLog("Start processing.");
            processor.processData();
            log.writeLog("End processing.");

        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        traditionalTryFinally();
    }

    private static void traditionalTryFinally() {
        LogWriter log = null;
        DataProcessor processor = null;

        try {
            log = new LogWriter("legacy.log");
            processor = new DataProcessor("legacy-orders.csv");

            log.writeLog("Start processing (legacy).");
            processor.processData();
            log.writeLog("End processing (legacy).");

        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        } finally {
            // Close in reverse order of creation, manually
            if (processor != null) {
                try {
                    processor.close();
                } catch (Exception e) {
                    System.out.println("Error closing processor: " + e.getMessage());
                }
            }
            if (log != null) {
                try {
                    log.close();
                } catch (Exception e) {
                    System.out.println("Error closing log writer: " + e.getMessage());
                }
            }
        }
    }
}
