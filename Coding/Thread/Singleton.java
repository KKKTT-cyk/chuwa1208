package Coding.Thread;

/**
 * Thread-safe Singleton implementation
 */
public final class Singleton {
    private Singleton() {
        if (Holder.INSTANCE != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of the class");
        }
    }

    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}