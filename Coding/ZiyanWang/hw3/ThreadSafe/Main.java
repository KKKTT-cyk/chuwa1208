public class Main {
    public static void main(String[] args) {

        ConfigManager cm1 = ConfigManager.getInstance();
        ConfigManager cm2 = ConfigManager.getInstance();

        // Verify same instance
        System.out.println(cm1 == cm2); // should print true

        // Set configuration values
        cm1.setConfig("env", "production");
        cm1.setConfig("region", "us-east-1");
        cm1.setConfig("version", "1.0.0");

        // Display configurations
        cm2.displayAllConfigs();
    }
}
