package P3;
/*
Create enum DeliveryStatus with:
    PENDING(1, "Pending"), PICKED_UP(2, "Picked up"), IN_TRANSIT(3, "In transit"), DELIVERED(4, "Delivered"), CANCELLED(5, "Cancelled")
    Private fields: int code, String description
    Private constructor
    public int getCode(), public String getDescription()
    public static DeliveryStatus fromCode(int code) - returns enum by code, throw IllegalArgumentException if invalid
    public boolean canTransitionTo(DeliveryStatus newStatus) - business logic for valid transitions
 */
public enum DeliverStatus {
    PENDING(1, "Pending"),
    PICKED_UP(2, "Picked up"),
    IN_TRANSIT(3, "In transit"),
    DELIVERED(4, "Delivered"),
    CANCELLED(5, "Cancelled");

    private  int code;
    private String description;

    // constructor
    private DeliverStatus(int code, String description){
        this.code = code;
        this.description = description;
    }

    // Getter
    public int getCode(){
        return code;
    }
    public String getDescription(){
        return description;
    }
    // Check status method
    public static DeliverStatus fromCode(int code){
        for (DeliverStatus status : DeliverStatus.values()){
            if (status.code == code){
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code" + code);
    }
    public boolean canTransitionTo(DeliverStatus newStatus){
        if (newStatus == null){
            return false;
        }
        if (this == DELIVERED || this == CANCELLED){
            return false;
        }
        return switch (this){
            case PENDING -> (newStatus == PICKED_UP || newStatus == CANCELLED);
            case PICKED_UP -> (newStatus == IN_TRANSIT  || newStatus == CANCELLED);
            case IN_TRANSIT -> (newStatus == DELIVERED || newStatus == CANCELLED);
            default -> false;
        };
    }
}
