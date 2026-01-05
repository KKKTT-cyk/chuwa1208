public class LogWriter implements AutoCloseable {
    private final String filename;

    public LogWriter(String filename) {
        this.filename = filename;
    }

    public void writeLog(String message) {
        System.out.println(filename + " LOG: " + message);
    }

    @Override
    public void close() {
        System.out.println("Closing log file: " + filename);
    }
}
