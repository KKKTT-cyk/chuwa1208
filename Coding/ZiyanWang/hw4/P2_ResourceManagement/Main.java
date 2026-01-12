public class Main {

    public static void main(String[] args) {

        System.out.println("=== Try-with-resources Example ===");

        try (LogWriter logWriter = new LogWriter("app.log");
             DataProcessor processor = new DataProcessor("OrderDB")) {

            logWriter.writeLog("Application started");
            processor.processData();

        }
    }
}

class MainTraditional {

    public static void main(String[] args) {

        LogWriter logWriter = null;
        DataProcessor processor = null;

        try {
            logWriter = new LogWriter("app.log");
            processor = new DataProcessor("OrderDB");

            logWriter.writeLog("Application started");
            processor.processData();

        } finally {
            if (processor != null) {
                processor.close();
            }
            if (logWriter != null) {
                logWriter.close();
            }
        }
    }
}

// Why try-with-resources is better?
// Try-with-resources automatically closes all resources that implement AutoCloseable, ensures correct closing order, reduces boilerplate code, and handles suppressed exceptions more safely than traditional try–finally blocks.
