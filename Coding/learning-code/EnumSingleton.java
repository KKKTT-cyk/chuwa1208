// public class ThreadSafeSingletonClass {

//     private static volatile ThreadSafeSingletonClass instance;

//     private ThreadSafeSingletonClass() {}
    
//     public static ThreadSafeSingletonClass getInstance() {
//         if (instance == null) {
//             synchronized (ThreadSafeSingletonClass.class) {
//                 if (instance == null) {
//                     instance = new ThreadSafeSingletonClass();
//                 }
//             }
//         }

//         return instance;
//     }
// }


// public class Singleton {

//     private Singleton() {}

//     private static class Holder {
//         private static final Singleton INSTANCE = new Singleton();
//     }

//     public static Singleton getInstance() {
//         return Holder.INSTANCE;
//     }
// }



public enum EnumSingleton {
    INSTANCE;

    public void someMethod() {
        // Perform actions here
    }
}