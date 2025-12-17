public class Main {

    public static void main(String[] args) {

        // 1. Call getInstance() multiple times
        ConfigManager config1 = ConfigManager.getInstance();
        ConfigManager config2 = ConfigManager.getInstance();
        ConfigManager config3 = ConfigManager.getInstance();

        // Verify if they are the same instance (using == operator)
        System.out.println("\n========== Instance Comparison ==========");
        System.out.println("config1 == config2: " + (config1 == config2));
        System.out.println("config2 == config3: " + (config2 == config3));
        System.out.println("config1 == config3: " + (config1 == config3));


        // 2. Set 3 configuration items
        System.out.println("\n========== Setting Configurations ==========");
        config1.setConfig("app.name", "MyApplication");
        config2.setConfig("app.version", "1.0.0");
        config3.setConfig("app.language", "English");

        // 3. Display all configuration items
        config1.displayAllConfigs();
    }
}