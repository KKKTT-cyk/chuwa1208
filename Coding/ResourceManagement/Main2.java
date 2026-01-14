package Coding.ResourceManagement;

/**
 * Try-with-resource is better because:
 * No risk of forgetting close()
 */
public class Main2 {
    public static void main(String[] args) {

        LogWriter logWriter = null;
        DataProcessor processor = null;

        try {
            logWriter = new LogWriter("app.log");
            processor = new DataProcessor("orders-db");

            logWriter.writeLog("Application started");
            processor.processData();
            logWriter.writeLog("Processing completed");

        } finally {
            if (processor != null) {
                processor.close();
            }
            if (logWriter != null) {
                logWriter.close();
            }
        }

        System.out.println("End of main method");
    }
}
