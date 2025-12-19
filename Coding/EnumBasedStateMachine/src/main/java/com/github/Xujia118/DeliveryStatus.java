package com.github.Xujia118;

public enum DeliveryStatus {
    PENDING(1, "Pending"),
    PICKED_UP(2, "Picked up"),
    IN_TRANSIT(3, "In transit"),
    DELIVERED(4, "Delivered"),
    CANCELLED(5, "Cancelled");

    private final int code;
    private final String description;

    private DeliveryStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() { return code; }
    public String getDescription() { return description; }

    public static DeliveryStatus fromCode(int code) {
        for (DeliveryStatus status : DeliveryStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + code);
    }

    public boolean canTransitionTo(DeliveryStatus newStatus) {
        return switch (this) {
            case PENDING -> newStatus == PICKED_UP || newStatus == CANCELLED;
            case PICKED_UP -> newStatus == IN_TRANSIT || newStatus == CANCELLED;
            case IN_TRANSIT -> newStatus == DELIVERED || newStatus == CANCELLED;
            case DELIVERED, CANCELLED -> false; // Terminal states
        };
    }
}