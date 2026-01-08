public class MainTryFinally {
    public static void main(String[] args) {
        LogWriter log = null;
        DataProcessor dp = null;

        try {
            log = new LogWriter("test.txt");
            dp = new DataProcessor("test_src");

            log.writeLog("Starting processing...");
            dp.processData();
            log.writeLog("Done");
        } finally {
            if(dp != null) {
                dp.close();
            }
            if (log != null) {
                log.close();
            }
        }
    }
}
