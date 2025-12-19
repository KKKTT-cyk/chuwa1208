package P3_Enum;

public class Package {
    private String trackingId;
    private DeliveryStatus status;

    public Package(DeliveryStatus status) {
        this.status = status;
    }

    public Package(String trackingId, DeliveryStatus status) {
        this.status = status;
        this.trackingId = trackingId;
    }


    public void updateStatus(DeliveryStatus newStatus) throws IllegalStateException {
        if(!status.canTransitionTo(newStatus)) {
            System.out.println("[ERROR]: Invalid status transition from " + status +
                    " to " + newStatus);
            throw new IllegalStateException("Invalid status transition from " + status +
                    " to " + newStatus);
        }

        System.out.println("Transit status from " + status +
                " to " + newStatus +
                ", tracking id: " + trackingId);

        status = newStatus;
    }
}
