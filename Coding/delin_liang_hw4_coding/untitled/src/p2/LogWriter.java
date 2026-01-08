package p2;

public class LogWriter implements AutoCloseable {
    private String filename;

    // Constructor accepting filename
    public LogWriter(String filename){
        this.filename = filename;
    }

    public void writeLog(String message) { // simulates writing (just print)
        System.out.println("Writing log to file: [" + filename +
                "]\nmessage: " + message);
    }

    @Override
    public void close() { // print "Closing log file: [filename]"
        System.out.println("Closing log file: [" + filename + "]");
    }
}
