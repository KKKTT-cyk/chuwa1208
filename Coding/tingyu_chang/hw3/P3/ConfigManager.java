package p3;

import java.util.HashMap;
import java.util.Map;

/*
Implement a configuration manager that can only have one instance in the entire application.

- Create a `ConfigManager` class using static inner class approach for Singleton
- `ConfigManager` should include:
  - `private static class ConfigHolder`
  - `private ConfigManager()` - private constructor
  - `public static ConfigManager getInstance()`
  - `private Map<String, String> configs` - initialize in constructor
  - `public void setConfig(String key, String value)`
  - `public String getConfig(String key)`
  - `public void displayAllConfigs()`

- Add comments explaining:
  - Why must the constructor be private?
  - Why must the `getInstance()` method be static?
  - How does this implementation ensure thread safety?
- Advanced: How would you design test code to verify Singleton's thread safety in a multi-threaded environment?

 */

public class ConfigManager {

    // Requirement: Use 'configs' as the variable name
    private Map<String, String> configs;

    // Requirement: Private Constructor
    // Q: Why must the constructor be private?
    // A: To prevent other classes from instantiating this class directly (e.g., new ConfigManager()).
    //    This is the fundamental rule of Singleton: only the class itself controls its one instance.
    private ConfigManager() {
        configs = new HashMap<>();
        System.out.println(">> ConfigManager System Initialized");
    }

    // Requirement: Static Inner Class named 'ConfigHolder'
    // Q: How does this implementation ensure thread safety?
    // A: We utilize the JVM's native class loading mechanism. The 'ConfigHolder' class is not loaded
    //    into memory until it is actually referenced in 'getInstance()'. Class loading is strictly
    //    synchronized by the JVM, guaranteeing that the instance is created exactly once without
    //    needing manual 'synchronized' keywords.
    private static class ConfigHolder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }

    // Requirement: Static getInstance method
    // Q: Why must the 'getInstance()' method be static?
    // A: Since the constructor is private, we cannot create an object to call a method on it.
    //    A static method acts as a global entry point that can be called on the class itself
    //    (ConfigManager.getInstance()) to retrieve the existing instance.
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
        System.out.println("\n[System Configuration Dump]");
        if (configs.isEmpty()) {
            System.out.println("No active configurations found.");
        } else {
            // Using a loop to print distinctly
            for (String key : configs.keySet()) {
                System.out.println(" -> " + key + ": " + configs.get(key));
            }
        }
        System.out.println("---------------------------\n");
    }
}
