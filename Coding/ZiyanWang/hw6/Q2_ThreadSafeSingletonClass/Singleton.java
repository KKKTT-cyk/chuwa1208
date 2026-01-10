public class Singleton {
    // 1, static volatile variable
    private static volatile Singleton instance;
    // 2, make constructor be private
    private Singleton() {
    }
    // 3. static synchronized getInstance method
    public static Singleton getInstance() {
        // 4, make sure thread safe
        if (instance == null) { // performance
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            } }
        return instance;
    }
}
