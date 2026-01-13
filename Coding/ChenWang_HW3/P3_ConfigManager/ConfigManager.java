import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    /*
       This implementation ensure thread safety since the inner static class
       makes sure that it won't be loaded when loading the ConfigManager, and
       it will be created only when the first time getInstance() is called. Also
       static class will only be loaded once and synchronized, to ensure thread
       safety as well.
     */
    private static class ConfigHolder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }

    private Map<String, String> configs;

    /*
       Constructor must be private to avoid outside create new instance
       of this Singleton Configuration Manager.
     */
    private ConfigManager() {
        configs = new HashMap<>();
    }

    /*
       getInstance() must be static since there is no instance before first calling
       the instance, so static make it to be called by class instead of instance.
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
        for (Map.Entry<String, String> entry : configs.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
