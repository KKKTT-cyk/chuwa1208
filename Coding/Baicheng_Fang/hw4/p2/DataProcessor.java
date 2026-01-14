public class DataProcessor implements AutoCloseable {
    private final String dataSource;

    public DataProcessor(String dataSource) {
        this.dataSource = dataSource;
    }

    public void processData() {
        System.out.println("Processing: " + dataSource);
    }

    @Override
    public void close() {
        System.out.println("Closing data processor: " + dataSource);
    }
}
