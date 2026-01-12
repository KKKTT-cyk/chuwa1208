package P2_Resource;

public class DataProcessor implements AutoCloseable {
    private final String dataSource;

    public DataProcessor(String dataSource){
        this.dataSource = dataSource;
    }

    public void processData() {
        System.out.println("Processing data from: [" + dataSource + "]");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing data processor: [" + dataSource + "]");
    }
}
