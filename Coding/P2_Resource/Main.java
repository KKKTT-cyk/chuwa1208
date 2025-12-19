package P2_Resource;

/*
    Q: Why try-with-resources is Better
    A: 1. release resources automatically, avoid manually closing resources.
       2. cleaner and more readable code, no need do the null check for resources
       3. better Exception Handling
            If: An exception occurs in try
                Another exception occurs in close()
            ➡ try-with-resources keeps the original exception
            ➡ Close exception is added as a suppressed exception
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("=== try-with-resources ===");

        /*
            try-with-resource: resources closed in reverse order of creation
         */
        try (
                LogWriter logWriter1 = new LogWriter("log1.txt");
                DataProcessor processor1 = new DataProcessor("Database1");
                DataProcessor processor2 = new DataProcessor("Database2");
                LogWriter logWriter2 = new LogWriter("log2.txt")
        ) {
            logWriter1.writeLog("Starting processing");
            processor1.processData();
            logWriter1.writeLog("Processing completed");
        } catch (Exception e) {
            /*
                can handle both original exception and suppressed exceptions thrown from close()
             */
            System.out.println(e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                System.out.println("Suppressed: " + t.getMessage());
            }
        }

        System.out.println("=== End ===");

        System.out.println("=== try-finally ===");

        LogWriter logWriter = null;
        DataProcessor processor = null;

        try {
            logWriter = new LogWriter("log.txt");
            processor = new DataProcessor("Database");

            logWriter.writeLog("Starting processing");
            processor.processData();
            logWriter.writeLog("Processing completed");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (processor != null) {
                processor.close();
            }
            if (logWriter != null) {
                logWriter.close();
            }
        }

        System.out.println("=== End ===");
    }
}
