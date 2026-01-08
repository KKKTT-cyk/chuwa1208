public class Main {
    public static void main(String[] args) {
        Package pkg = new Package("PKG-001");
        System.out.println("T0: " + pkg.getStatus());

        pkg.updateStatus(DeliveryStatus.PICKED_UP);
        System.out.println("T1: " + pkg.getStatus());

        pkg.updateStatus(DeliveryStatus.IN_TRANSIT);
        System.out.println("T2: " + pkg.getStatus());

        pkg.updateStatus(DeliveryStatus.DELIVERED);
        System.out.println("T3: " + pkg.getStatus());

        // test invalid transition
        try {
            pkg.updateStatus(DeliveryStatus.IN_TRANSIT);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        DeliveryStatus s1 = DeliveryStatus.fromCode(1);
        System.out.println("fromCode(1): " + s1.getDescription());
    }
}
