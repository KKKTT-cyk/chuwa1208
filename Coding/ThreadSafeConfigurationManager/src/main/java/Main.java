//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Call getInstance() multiple times
        ConfigManager config1 = ConfigManager.getInstance();
        ConfigManager config2 = ConfigManager.getInstance();

        // Verify both references point to the same instance
        System.out.println("Same instance? " + (config1 == config2));

        // Set configuration values
        config1.setConfig("db.host", "localhost");
        config1.setConfig("db.port", "5432");
        config1.setConfig("app.env", "production");

        // Display all configurations
        config2.displayAllConfigs();
    }
}