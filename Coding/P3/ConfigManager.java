package P3;

import java.util.HashMap;
import java.util.Map;

/*
Implement a configuration manager that can only have one instance in the entire application.

Create a ConfigManager class using static inner class approach for Singleton
ConfigManager should include:
    private static class ConfigHolder
    private ConfigManager() - private constructor
    public static ConfigManager getInstance()
    private Map<String, String> configs - initialize in constructor
    public void setConfig(String key, String value)
    public String getConfig(String key)
    public void displayAllConfigs()

 */
public class ConfigManager {

    // field,  configs: key -> value
    private Map<String, String> configs;

    // private constructor make sure only one instance can ever be crated
    private  ConfigManager(){
        configs = new HashMap<>();
        System.out.println("ConfigManager System Initialization");
    }
    // create a static inner class Holde instance avoiding outclass get it
    // How does this ensure thread safety and efficiency?
    // - The JVM loads classes in a thread-safe way.
    // - ConfigHolder is NOT loaded until getInstance() is called.
    // - When ConfigHolder is loaded, INSTANCE is created exactly once.
    private static class ConfigHolder{
        private static final ConfigManager INSTANCE = new ConfigManager();
    }

    public static ConfigManager getInstance(){
        return ConfigHolder.INSTANCE;
    }
    public void setConfigs(String key, String value){
        configs.put(key, value);
    }
    public String getConfig(String key){
        return configs.get(key);
    }
    public void displayAllConfigs(){
        System.out.println("System Configs");
        if (configs.isEmpty()){
            System.out.println("No Config");
        }else {
            for (Map.Entry<String, String> entry : configs.entrySet()){
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
        }
    }
}
