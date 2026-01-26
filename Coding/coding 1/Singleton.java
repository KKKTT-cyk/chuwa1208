public class Singleton {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
        // private constructor prevents instantiation
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}