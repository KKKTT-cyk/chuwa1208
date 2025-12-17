import java.util.HashMap;
import java.util.Map;
public class ConfigManager {
    private static class ConfigHolder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }

    // Map to store configuration items
    private Map<String, String> configs;

    /**
     * Private constructor
     *
     * Why must the constructor be private?
     * Answer:
     * 1. Prevents external code from creating new instances using the 'new' keyword
     * 2. Ensures instances can only be obtained through the getInstance() method
     * 3. This is the core requirement of the Singleton pattern - controlling instance creation
     * 4. If the constructor were public, anyone could create multiple instances, violating the Singleton principle
     */
    private ConfigManager() {
        // Initialize the configuration Map
        configs = new HashMap<>();
        System.out.println("ConfigManager instance created");
    }

    /**
     * Method to get the singleton instance
     *
     * Why must getInstance() be static?
     * Answer:
     * 1. Static methods can be called directly through the class name without creating an object
     * 2. Since the constructor is private, external code cannot create objects, so a static method must be provided for access
     * 3. If getInstance() were not static, you would need an instance to call it, creating a paradox
     * 4. A static method ensures the singleton can be accessed from anywhere via ConfigManager.getInstance()
     *
     * How does this implementation ensure thread safety?
     * Answer:
     * 1. Leverages the JVM's class loading mechanism to guarantee thread safety
     * 2. JVM acquires an initialization lock during class initialization, ensuring only one thread can initialize the class
     * 3. The ConfigHolder class is only loaded when getInstance() is called for the first time
     * 4. The class loading process is guaranteed by JVM to be thread-safe, no additional synchronization needed
     * 5. This approach achieves both lazy loading and thread safety with no performance overhead
     * 6. Compared to synchronized or volatile + double-checked locking, this approach is more concise and efficient
     */
    public static ConfigManager getInstance() {
        // Accessing the static member of the static inner class triggers its initialization
        // JVM guarantees this process is thread-safe
        return ConfigHolder.INSTANCE;
    }

    public void setConfig(String key, String value) {
        configs.put(key, value);
        System.out.println("Set config: " + key + " = " + value);
    }

    public String getConfig(String key) {
        return configs.get(key);
    }

    /**
     * Display all configuration items
     */
    public void displayAllConfigs() {
        System.out.println("\n========== All Configurations ==========");
        if (configs.isEmpty()) {
            System.out.println("No configurations");
        } else {
            for (Map.Entry<String, String> entry : configs.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
        }
        System.out.println("========================================\n");
    }
}