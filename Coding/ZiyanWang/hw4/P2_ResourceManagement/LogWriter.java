public class LogWriter implements AutoCloseable {

    private String filename;

    public LogWriter(String filename) {
        this.filename = filename;
        System.out.println("Opening log file: " + filename);
    }

    public void writeLog(String message) {
        System.out.println("Writing log: " + message);
    }

    @Override
    public void close() {
        System.out.println("Closing log file: " + filename);
    }
}
