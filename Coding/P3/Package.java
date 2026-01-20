package P3;
/*
Create Package class:
    private String trackingId
    private DeliveryStatus status
    public void updateStatus(DeliveryStatus newStatus) - validate transition before updating
 */
public class Package {
    private String trackingId;
    private DeliverStatus status;

    // constructor
    public Package(String trackingId){
        this.trackingId = trackingId;
        this.status = DeliverStatus.PENDING;
    }
    // getter

    public String getTrackingId(){
        return trackingId;
    }
    public DeliverStatus getStatus(){
        return status;
    }
    public void updateStatus(DeliverStatus newStatus){
        if (!this.status.canTransitionTo(newStatus)){
            throw new IllegalStateException("Invalid transition " + this.status + " => " + newStatus);
        }
        this.status = newStatus;
        System.out.println("Updated: " + trackingId + " => " + status.getDescription());
    }
}
