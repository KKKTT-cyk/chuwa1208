public class Main {
    public static void main(String[] args) {
        ConfigManager cm1 = ConfigManager.getInstance();
        ConfigManager cm2 = ConfigManager.getInstance();
        ConfigManager cm3 = ConfigManager.getInstance();

        System.out.println("cm1 == cm2? " + (cm1 == cm2));
        System.out.println("cm1 == cm3? " + (cm1 == cm3));

        cm1.setConfig("ver", "1.1");
        cm1.setConfig("attr", "r");
        cm1.setConfig("type", "cfg");

        cm1.displayAllConfigs();
    }
}
