package p2;

public class DataProcessor implements AutoCloseable {
    private String dataSource;

    // Constructor accepting data source name
    public DataProcessor(String dataSource){
        this.dataSource = dataSource;
    }

    public void processData() { // simulates processing
        System.out.println("Processing data from: [" + dataSource + "]");
    }

    @Override
    public void close() { // print "Closing data processor: [dataSource]"
        System.out.println("Closing data processor: [" + dataSource + "]");
    }

}
