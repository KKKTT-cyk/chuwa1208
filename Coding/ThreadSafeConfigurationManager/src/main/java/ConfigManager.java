import java.util.HashMap;
import java.util.Map;


public class ConfigManager {
    private final Map<String, String> configs;

    /*
     * Why must the constructor be private?
     * - To prevent external classes from creating new instances using `new`.
     * - This enforces the Singleton rule: only ONE instance exists.
     */
    private ConfigManager() {
        configs = new HashMap<>();
    }

    /**
     * This uses the Initialization-on-Demand Holder Idiom:
     * ConfigHolder is not loaded until getInstance() is called
     * JVM guarantees class initialization is thread-safe
     * No synchronization required
     * No performance overhead
     */
    private static class ConfigHolder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }

    /*
     * Why must getInstance() be static?
     * - Because we need a way to access the Singleton without creating an object.
     * - Static methods belong to the class, not an instance.
     */
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
        System.out.println("Current configurations:");
        for (Map.Entry<String, String> entry : configs.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

}
