package Coding.Singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * This singleton class is thread safety because it is created in a static inner
 * class and the class is loaded only when it is first requested.
 */
public class ConfigManager {
    private static class ConfigHolder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }

    /**
     * This must be private to prevent instantiation from outside the class.
     */
    private ConfigManager() {
        configs = new HashMap<>();
    }

    /**
     * This is static because it is used to create the singleton instance.
     */
    public static ConfigManager getInstance() {
        return ConfigHolder.INSTANCE;
    }

    private Map<String, String> configs;

    public void setConfig(String key, String value) {
        configs.put(key, value);
    }

    public String getConfig(String key) {
        return configs.get(key);
    }

    public void displayConfigs() {
        for (Map.Entry<String, String> entry : configs.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
