package p3;

public class Package {
    private String trackingId;
    private DeliveryStatus status;

    public Package(String trackingId, DeliveryStatus status) {
        this.trackingId = trackingId;
        this.status = status;
    }

    public void updateStatus(DeliveryStatus newStatus) {
        // validate transition before updating
        if (!status.canTransitionTo(newStatus)) {
            throw new IllegalStateException(
                    "Status transition failed:\n" +
                            "[" + status + "] -> [" + newStatus + "]");
        }
        status = newStatus;
        System.out.println("Status updated to: " + newStatus.getDescription());

    }
}
