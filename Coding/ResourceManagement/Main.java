package Coding.ResourceManagement;

public class Main {
    public static void main(String[] args) {

        try (
                LogWriter logWriter = new LogWriter("app.log");
                DataProcessor processor = new DataProcessor("orders-db")) {
            logWriter.writeLog("Application started");
            processor.processData();
            logWriter.writeLog("Processing completed");
        }

        System.out.println("End of main method");
    }
}
