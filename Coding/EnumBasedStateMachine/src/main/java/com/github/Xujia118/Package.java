package com.github.Xujia118;

public class Package {
    private final String trackingId;
    private DeliveryStatus status;

    public Package(String trackingId) {
        this.trackingId = trackingId;
        this.status = DeliveryStatus.PENDING; // Initial state
    }

    public void updateStatus(DeliveryStatus newStatus) {
        if (status.canTransitionTo(newStatus)) {
            System.out.println("Updating " + trackingId + ": " + status + " -> " + newStatus);
            this.status = newStatus;
        } else {
            throw new IllegalStateException("Invalid transition: Cannot move from "
                    + status + " to " + newStatus);
        }
    }

    public DeliveryStatus getStatus() { return status; }
}
