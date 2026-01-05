package p2;

/*
- Create `DataProcessor` class implementing `AutoCloseable`:
  - `private String dataSource`
  - Constructor accepting data source name
  - `public void processData()` - simulates processing
  - `@Override public void close()` - print "Closing data processor: [dataSource]"
 */

public class DataProcessor implements AutoCloseable {
    private String dataSource;

    public DataProcessor(String dataSource) {
        this.dataSource = dataSource;
        System.out.println("-> DataProcessor connected to: " + dataSource);
    }

    public void processData() {
        System.out.println("   [Processing data...]");
    }

    @Override
    public void close() {
        // This method is called automatically
        System.out.println("-> Closing data processor: " + dataSource);
    }
}
