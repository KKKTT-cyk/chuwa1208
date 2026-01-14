package src.P3_singleton;

public class Main {
    public static void main(String[] args) {
        ConfigManager m1 = ConfigManager.getInstance();
        ConfigManager m2 = ConfigManager.getInstance();
        ConfigManager m3 = ConfigManager.getInstance();
        assert m1 == m2 && m1 == m3;
        m1.setConfigs("k1", "v1");
        m2.setConfigs("k2", "v2");
        m1.setConfigs("k3", "v3");
        m3.displayAllConfigs();
    }
}
