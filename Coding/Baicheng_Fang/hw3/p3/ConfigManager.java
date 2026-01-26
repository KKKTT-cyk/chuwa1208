import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * ConfigManager instance is created when ConfigHolder class is loaded and initialized.
 * This only occurs when the getInstance() method is called for the first time for lazy load.
 * Since class loading and initialization are thread-safe in Java, this approach guarantees that the ConfigManager instance is created
 * only once even in a multi-threaded environment
 */
 
public class ConfigManager {
   
    private final Map<String, String> configs;

    // private constructor for controlled creation
    private ConfigManager() {
        this.configs = new ConcurrentHashMap<>();
    }

    private static class ConfigHolder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }

    /*
     * static method belongs to class rather instance
     * provides global public access
     */
    public static ConfigManager getInstance() {
        return ConfigHolder.INSTANCE;
    }

    public void setConfig(String key, String value) {
        if (key == null) return;
        if (value == null) value = "";
        configs.put(key, value);
    }

    public String getConfig(String key) {
        if (key == null) return null;
        return configs.get(key);
    }

    public void displayAllConfigs() {
        if(!configs.isEmpty()) {
            configs.forEach((k, v) -> 
                System.out.println(k + ": " + v)
            );
        }
    }
}
