package ThreadSafeSingleton;

public final class TSsingleton {
    private static volatile TSsingleton instance;

    private TSsingleton() {}

    public static TSsingleton getInstance() {
        TSsingleton local = instance;
        if (local == null) {
            synchronized (TSsingleton.class) {
                local = instance;
                if (local == null) {
                    local = new TSsingleton();
                    instance = local;
                }
            }
        }
        return local;
    }
}
