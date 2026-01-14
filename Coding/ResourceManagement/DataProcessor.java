package Coding.ResourceManagement;

public class DataProcessor implements AutoCloseable {
    private String dataSource;

    public DataProcessor(String dataSource) {
        this.dataSource = dataSource;
        System.out.println("Opening data processor: " + dataSource);
    }

    public void processData() {
        System.out.println("Processing data from: " + dataSource);
    }

    @Override
    public void close() {
        System.out.println("Closing data processor: " + dataSource);
    }
}
