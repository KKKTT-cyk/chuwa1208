package p2;

public class Main {
    public static void main(String[] args) {

        try (LogWriter logWriter = new LogWriter("app.log");
             DataProcessor processor = new DataProcessor("database")) {

            logWriter.writeLog("Start processing");
            processor.processData();

        }
    }

    /*
    * Think: explain why try-with-resources is better
    *
    * Ans: because it ensures automatic and safe resource management.
    * The try-with-resources automatically closes resources in reverse
    * order of creation and prevents resource leaks.
    * */
}
