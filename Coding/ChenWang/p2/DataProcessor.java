public class DataProcessor implements AutoCloseable{
    private String dataSource;

    public DataProcessor(String dataSource) {
        this.dataSource = dataSource;
    }

    public void processData() {
        System.out.println("Processing data");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing data processor: " + dataSource);
    }
}
