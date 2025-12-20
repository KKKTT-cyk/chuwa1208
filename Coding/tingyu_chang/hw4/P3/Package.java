package p3;

/*
- Create `Package` class:
  - `private String trackingId`
  - `private DeliveryStatus status`
  - `public void updateStatus(DeliveryStatus newStatus)` - validate transition before updating
 */

public class Package {
    private String trackingId;
    private DeliveryStatus status;

    public Package(String trackingId) {
        this.trackingId = trackingId;
        this.status = DeliveryStatus.PENDING; // Default start state
        System.out.println("Package created [" + trackingId + "]: " + status);
    }

    public void updateStatus(DeliveryStatus newStatus) {
        if (status.canTransitionTo(newStatus)) {
            System.out.println("Transition allowed: " + status + " -> " + newStatus);
            this.status = newStatus;
        } else {
            throw new IllegalStateException("Invalid transition: Cannot go from " + status + " to " + newStatus);
        }
    }

    public DeliveryStatus getStatus() {
        return status;
    }
}

