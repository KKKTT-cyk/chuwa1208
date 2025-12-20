package p2;

/*
- In `Main`:
  - Use try-with-resources to create both LogWriter and DataProcessor
  - Demonstrate that close() methods are called automatically
  - Test the execution order by creating multiple resources
- Compare: Write the same functionality using traditional try-finally approach and explain why try-with-resources is better

 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- 1. Modern Try-With-Resources ---");

        // Resources are declared in the try(...) header
        try (LogWriter logger = new LogWriter("app.log");
             DataProcessor processor = new DataProcessor("DB_Main")) {

            processor.processData();
            logger.writeLog("Data processed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Notice: Resources are closed automatically here in REVERSE order of creation.


        System.out.println("\n--- 2. Traditional Try-Finally (Comparison) ---");
        testTraditionalWay();
    }

    public static void testTraditionalWay() {
        LogWriter logger = null;
        DataProcessor processor = null;
        try {
            logger = new LogWriter("legacy.log");
            processor = new DataProcessor("DB_Legacy");

            processor.processData();
            logger.writeLog("Legacy processing done.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Manual closing is verbose and error-prone
            if (processor != null) {
                processor.close();
            }
            if (logger != null) {
                logger.close();
            }
        }
    }
}

/*
   COMPARE: Why is try-with-resources better?
   1. Conciseness: It drastically reduces boilerplate code (no nested 'finally' blocks needed).
   2. Safety: It handles null checks automatically. In the traditional way, if 'logger' is null,
      calling 'logger.close()' throws a NullPointerException in the finally block, masking the real error.
   3. Correct Order: It automatically closes resources in the reverse order of creation.
*/


