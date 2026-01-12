package P2_Resource;

public class LogWriter implements AutoCloseable{
    private final String filename;

    public LogWriter(String filename){
        this.filename = filename;
    }

    public void writeLog(String message) throws Exception{
        System.out.println("Writing to log file [" + filename + "]," +
                " message: " + message);
//        throw new Exception("0");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing log file: [" + filename + "]");
//        throw new Exception("1");
    }
}
