package p3;

public class Main {
    public static void main(String[] args) {

        // Call getInstance() multiple times
        ConfigManager cm1 = ConfigManager.getInstance();
        ConfigManager cm2 = ConfigManager.getInstance();
        ConfigManager cm3 = ConfigManager.getInstance();

        // verify it's the same instance (use ==)
        System.out.println("Same instance?: cm1 == cm2: " + (cm1 == cm2));
        System.out.println("Same instance?: cm1 == cm3: " + (cm1 == cm3));
        System.out.println("Same instance?: cm2 == cm3: " + (cm2 == cm3));

        // Set 3 configuration items
        cm1.setConfig("db.url", "localhost");
        cm1.setConfig("db.user", "admin");
        cm1.setConfig("db.timeout", "30");

        // Display all configurations
        cm1.displayAllConfigs();
    }

    /* Follow-up Questions:
    * 1- Why must the constructor be private?
    * Ans: Because a Singleton must prevent external instantiation.
    * If we made the constructor public, then anyone could have access to
    * create an instance. In this case multiple instances would be created,
    * which is a violation to the Singleton pattern : "A private constructor
    * ensures that the class controls its own instantiation."
    *
    * 2- Why must the getInstance() method be static?
    * Ans: Because there might be no instance available before calling
    * getInstance()
    * Since static methods belong to the class, they can be called directly without
    * creating an instance of the object through new. A static method ensures a global
    * access point to the Singleton instance without requiring an existing object
    *
    * 3- How does this implementation ensure thread safety?
    * Ans: The Static Inner Class implementation ensures thread safety by relying on
    * Java’s class loading mechanism.
    * The JVM guarantees that a class is loaded and initialized only once, even in
    * a multi-threaded environment. As a result, the Singleton instance held by the
    * static inner class is created only once, making this approach naturally
    * thread-safe without explicit synchronization
    *
    * Advanced: How would you design test code to verify Singleton's thread safety
    * in a multi-threaded environment?
    * Ans:
    * Step 1 - create multiple threads that simultaneously call getInstance()
    * Step 2 - collect the returned references
    * Step 3 - verify if the references from threads receive the same instance
    * (using ==)
    * */
}
