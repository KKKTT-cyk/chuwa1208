package P2;
/*
private String filename
Constructor accepting filename
public void writeLog(String message) - simulates writing (just print)
@Override public void close() - print "Closing log file: [filename]"
 */
public class LogWriter implements AutoCloseable{
    // fields
    private String filename;
    public LogWriter(String filename){
        this.filename = filename;
        System.out.println("Opening file name: " + filename);
    }
    public void writeLog(String message){
        System.out.println("Writing to " + filename + ": " + message);
    }
    @Override
    public void close(){
        System.out.println("Closing log file: " + "[" + filename + "]" );
    }
}
