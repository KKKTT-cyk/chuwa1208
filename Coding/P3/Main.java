package P3;
/*
In Main:
    Call getInstance() multiple times and verify it's the same instance (use ==)
    Set 3 configuration items
    Display all configurations
Add comments explaining:
    Why must the constructor be private?
    Why must the getInstance() method be static?
    How does this implementation ensure thread safety?
Advanced: How would you design test code to verify Singleton's thread safety in a multi-threaded environment?
 */
public class Main {
    public static void main(String[] args) {
        ConfigManager cm1 = ConfigManager.getInstance();
        ConfigManager cm2 = ConfigManager.getInstance();
        ConfigManager cm3 = ConfigManager.getInstance();

        System.out.println("cm1 == cm2 ? " + (cm1 == cm2));
        System.out.println("cm2 == cm3 ? " + (cm2 == cm3));

        cm1.setConfigs("db.host", "localhost");
        cm2.setConfigs("db.connect", "success");
        cm3.setConfigs("env", "dev");

        // get one config
        System.out.println("env = " + cm2.getConfig("env"));

        // get all configs
        System.out.println("----All Configs----");
        cm3.displayAllConfigs();
    }
}
