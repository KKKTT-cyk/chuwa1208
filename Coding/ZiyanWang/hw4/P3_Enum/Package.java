public class Package {

    private String trackingId;
    private DeliveryStatus status;

    public Package(String trackingId) {
        this.trackingId = trackingId;
        this.status = DeliveryStatus.PENDING;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void updateStatus(DeliveryStatus newStatus) {
        if (!status.canTransitionTo(newStatus)) {
            throw new IllegalStateException(
                    "Invalid status transition from " + status + " to " + newStatus
            );
        }
        this.status = newStatus;
        System.out.println("Status updated to: " + newStatus.getDescription());
    }
}
