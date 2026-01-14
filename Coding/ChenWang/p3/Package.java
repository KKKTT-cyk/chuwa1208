public class Package {
    private String trackingId;
    private DeliveryStatus status;

    public Package(String trackingId) {
        this.trackingId = trackingId;
        this.status = DeliveryStatus.PENDING;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void updateStatus(DeliveryStatus newStatus) {
        if (!status.canTransitionTo(newStatus)) {
            throw new IllegalStateException(
                    "Invalid status transition: " + status);
        }
        System.out.println("Updating " + trackingId + ": " + status + " → " + newStatus);
        this.status = newStatus;
    }
}
