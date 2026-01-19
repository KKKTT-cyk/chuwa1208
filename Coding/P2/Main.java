package P2;
/*
Use try-with-resources to create both LogWriter and DataProcessor
Demonstrate that close() methods are called automatically
Test the execution order by creating multiple resources
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("====== try with resources ======");
        try(
                LogWriter logWriter = new LogWriter("app.log");
                DataProcessor processor = new DataProcessor("database")
                ){
            logWriter.writeLog("Start");
            processor.processData();
            logWriter.writeLog("Write log finished");
        }
        System.out.println("====== End ======");
    }
}
