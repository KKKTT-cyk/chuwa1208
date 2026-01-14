public class Main {
    public static void main(String[] args) {
        try (LogWriter log = new LogWriter("test.txt");
                DataProcessor dp = new DataProcessor("test_src")) {
            log.writeLog("Starting processing...");
            dp.processData();
            log.writeLog("Done");
        }

        try (LogWriter log1 = new LogWriter("log1.txt");
                LogWriter log2 = new LogWriter("log2.txt");
                DataProcessor dp1 = new DataProcessor("src1");
                DataProcessor dp2 = new DataProcessor("src2")) {
            
                }
    }
}
