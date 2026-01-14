/*
- In `Main`:
  - Call `getInstance()` multiple times and verify it's the same instance (use `==`)
  - Set 3 configuration items
  - Display all configurations
 */

package p3;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Singleton Consistency Check ---");

        // 1. Call getInstance() multiple times
        ConfigManager ref1 = ConfigManager.getInstance();
        ConfigManager ref2 = ConfigManager.getInstance();

        // 2. Verify it's the same instance using '=='
        if (ref1 == ref2) {
            System.out.println("Test Passed: ref1 and ref2 point to the exact same memory address.");
        } else {
            System.out.println("Test Failed: Duplicate instances exist.");
        }

        // 3. Set 3 configuration items (Using unique test data)
        ref1.setConfig("app.theme", "Dark Mode");
        ref1.setConfig("max.connections", "500");

        // Setting a config on ref2 to prove it affects the shared instance
        ref2.setConfig("cache.ttl", "600s");

        // 4. Display all configurations
        ref1.displayAllConfigs();
    }
}
