import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    // Store configuration key-value pairs
    private Map<String, String> configs;

    /*
     * The constructor is private to prevent creating multiple instances
     * using the 'new' keyword. This ensures that only one instance of
     * ConfigManager exists in the application.
     */

    // Private constructor prevents external instantiation
    private ConfigManager() {
        configs = new HashMap<>();
    }

    /*
     * Static inner class responsible for holding the Singleton instance.
     * This class is not loaded until getInstance() is called.
     */

    // Static inner class holds the singleton instance
    private static class ConfigHolder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }

    /*
     * The getInstance() method is static so it can be accessed without
     * creating an object. It provides a global access point to the
     * single ConfigManager instance.
     *
     * This implementation is thread-safe because class loading in Java
     * is guaranteed to be thread-safe by the JVM.
     */

    // Global access point to the singleton instance
    public static ConfigManager getInstance() {
        return ConfigHolder.INSTANCE;
    }

    public void setConfig(String key, String value) {
        configs.put(key, value);
    }

    public String getConfig(String key) {
        return configs.get(key);
    }

    public void displayAllConfigs() {
        System.out.println("Configurations:");
        for (Map.Entry<String, String> entry : configs.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}

/* Advanced: How would you design test code to verify Singleton's thread safety in a multi-threaded environment?
 * To test thread safety in a multi-threaded environment,
 * multiple threads can be created to call getInstance()
 * concurrently. All returned instances should be compared
 * to verify that they reference the same object.
 */

