package src.P3_singleton;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private Map<String, String> configs;
    static class ConfigHolder {
        /*
            ensures thread safety because this is static variable, it will only be
            initialized once when this inner static class is loaded.
         */
        private static final ConfigManager INSTANCE = new ConfigManager();
    }

    /*
        Private constructor to prevent others creating multiple instances
        by calling constructor multiple times.
     */
    private ConfigManager(){
        this.configs = new HashMap<>();
    }
    /*
        static method since our constructor is private, we cannot get an instance
        first and then call this method.
     */
    public static ConfigManager getInstance(){
        return ConfigHolder.INSTANCE;
    }

    public void setConfigs(String key, String value) {
        this.configs.put(key, value);
    }

    public String getConfig(String key){
        return configs.getOrDefault(key, "");
    }

    public void displayAllConfigs(){
        System.out.println("Display all configs:");
        for(Map.Entry<String, String> item: configs.entrySet()) {
            System.out.println(" ** Key: " + item.getKey() + ", Value: " + item.getValue());
        }
    }
}
