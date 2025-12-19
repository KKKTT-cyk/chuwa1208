public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1: Basic try-with-resources ===\n");
        demonstrateBasicTryWithResources();

        System.out.println("\n=== 2: Multiple resources and execution order ===\n");
        demonstrateMultipleResources();

        System.out.println("\n=== 3: Exception handling with try-with-resources ===\n");
        demonstrateExceptionHandling();

        System.out.println("\n=== 4: Traditional try-finally (OLD WAY) ===\n");
        demonstrateTraditionalTryFinally();
    }

    private static void demonstrateBasicTryWithResources() {
        // Resource is automatically closed after the try block
        try (LogWriter logger = new LogWriter("application.log")) {
            logger.writeLog("Application started");
            logger.writeLog("Processing user request");
            logger.writeLog("Application finished");
        } // close() is called automatically here
        System.out.println("After try-with-resources block\n");
    }

    private static void demonstrateMultipleResources() {
        try (
            LogWriter logger = new LogWriter("system.log");
            DataProcessor processor = new DataProcessor("database-connection");
            LogWriter errorLogger = new LogWriter("error.log")
        ) {
            logger.writeLog("Starting data processing");
            processor.processData();
            errorLogger.writeLog("No errors detected");
            logger.writeLog("Data processing complete");
        } // Resources closed in REVERSE order: errorLogger, processor, logger
        System.out.println("All resources have been automatically closed\n");
    }

    private static void demonstrateExceptionHandling() {
        try (
            LogWriter logger = new LogWriter("transaction.log");
            DataProcessor processor = new DataProcessor("payment-gateway")
        ) {
            logger.writeLog("Starting transaction");
            processor.processData();

            // Simulate an exception
            System.out.println("Simulating an error...");
            throw new RuntimeException("Payment processing failed!");

        } catch (RuntimeException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        // Resources are STILL closed even though exception was thrown
        System.out.println("Resources closed despite exception\n");
    }

    private static void demonstrateTraditionalTryFinally() {
        LogWriter logger = null;
        DataProcessor processor = null;

        try {
            logger = new LogWriter("old-style.log");
            processor = new DataProcessor("legacy-system");

            logger.writeLog("Using traditional try-finally");
            processor.processData();

        } finally {
            // Must manually close resources in finally block
            // Must also handle potential exceptions during close()
            if (processor != null) {
                try {
                    processor.close();
                } catch (Exception e) {
                    System.err.println("Error closing processor: " + e.getMessage());
                }
            }

            if (logger != null) {
                try {
                    logger.close();
                } catch (Exception e) {
                    System.err.println("Error closing logger: " + e.getMessage());
                }
            }
        }
        System.out.println("Traditional approach completed\n");
    }
}
