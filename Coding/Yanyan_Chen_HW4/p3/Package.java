public class Package {
    private String trackingId;
    private DeliveryStatus status;

    public Package(String trackingId, DeliveryStatus status) {
        this.trackingId = trackingId;
        this.status = status;
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
                String.format("Invalid status transition: Cannot change from %s to %s for package %s",
                    status.getDescription(),
                    newStatus.getDescription(),
                    trackingId)
            );
        }
        
        DeliveryStatus oldStatus = status;
        status = newStatus;
        
        System.out.println(String.format(
            "[%s] Status updated: %s -> %s",
            trackingId,
            oldStatus.getDescription(),
            newStatus.getDescription()
        ));
    }

    @Override
    public String toString() {
        return String.format("Package[trackingId=%s, status=%s]", 
            trackingId, 
            status.getDescription());
    }
}
