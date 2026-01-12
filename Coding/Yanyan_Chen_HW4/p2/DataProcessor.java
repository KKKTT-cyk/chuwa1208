public class DataProcessor implements AutoCloseable {
    private String dataSource;

    public DataProcessor(String dataSource) {
        this.dataSource = dataSource;
        System.out.println("Opening data processor: " + dataSource);
    }

    public void processData() {
        System.out.println("[PROCESSING - " + dataSource + "]: Reading and processing data...");
    }

    @Override
    public void close() {
        System.out.println("Closing data processor: " + dataSource);
    }
}
