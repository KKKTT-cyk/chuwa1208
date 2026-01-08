public class Package {
    private final String trackingId;
    private DeliveryStatus status;

    public Package(String trackingId) {
        if (trackingId == null || trackingId.isBlank()) {
            throw new IllegalArgumentException("null trackingId");
        }
        this.trackingId = trackingId;
        this.status = DeliveryStatus.PENDING;
    }

    public String getTrakcingId() {
        return trackingId;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void updateStatus(DeliveryStatus newStatus) {
        if (!status.canTransitionTo(newStatus)) {
            throw new IllegalArgumentException(
                        "Invalid transition"
                    );
        }
        this.status = newStatus;
    }
}
