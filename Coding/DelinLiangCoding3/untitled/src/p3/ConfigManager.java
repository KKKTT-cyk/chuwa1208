package p3;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private Map<String, String> configs; // initialize in constructor

    private static class ConfigHolder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }


    private ConfigManager() { // private constructor
        configs = new HashMap<>();
    }

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
        System.out.println("=====Configuration Settings=====");
        if (configs.isEmpty()) {
            System.out.println("N/A");
        }
        else {
            for (Map.Entry<String, String> entry : configs.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }
}
