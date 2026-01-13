public class Main {
    public static void main(String[] args) {
        ConfigManager config1 = ConfigManager.getInstance();
        ConfigManager config2 = ConfigManager.getInstance();
        ConfigManager config3 = ConfigManager.getInstance();

        System.out.println("config1 == config2 ? " + (config1 == config2));
        System.out.println("config2 == config3 ? " + (config2 == config3));

        config1.setConfig("username", "abcdefg");
        config1.setConfig("password", "gfedcba");
        config1.setConfig("securityquestion", "ture");

        config2.displayAllConfigs();
    }
}
