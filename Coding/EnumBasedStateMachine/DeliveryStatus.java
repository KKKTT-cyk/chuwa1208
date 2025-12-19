package Coding.EnumBasedStateMachine;

public enum DeliveryStatus {

    PENDING(1, "Pending"),
    PICKED_UP(2, "Picked up"),
    IN_TRANSIT(3, "In transit"),
    DELIVERED(4, "Delivered"),
    CANCELLED(5, "Cancelled");

    private final int code;
    private final String description;

    // private constructor
    DeliveryStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // Lookup by code
    public static DeliveryStatus fromCode(int code) {
        for (DeliveryStatus status : DeliveryStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid DeliveryStatus code: " + code);
    }

    // State transition rules
    public boolean canTransitionTo(DeliveryStatus newStatus) {

        if (this == CANCELLED || this == DELIVERED) {
            return false;
        }

        switch (this) {
            case PENDING:
                return newStatus == PICKED_UP || newStatus == CANCELLED;
            case PICKED_UP:
                return newStatus == IN_TRANSIT || newStatus == CANCELLED;
            case IN_TRANSIT:
                return newStatus == DELIVERED || newStatus == CANCELLED;
            default:
                return false;
        }
    }
}
