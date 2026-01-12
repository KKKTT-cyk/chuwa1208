package P3_Enum;

import java.util.HashMap;
import java.util.Map;

/*
    Q: How does Enum help prevent invalid states compared to using int constants?
    A: Compiler will help to check invalid states in compile-time; Enum ensures a finite set; More readable
 */
public enum DeliveryStatus {
    PENDING(1,"Pending"),
    PICKED_UP(2,"Picked up"),
    IN_TRANSIT(3,"In_transit"),
    DELIVERED(4,"Delivered"),
    CANCELLED(5,"Cancelled");

    private final int code;
    private final String description;

    private static final Map<Integer, DeliveryStatus> CODE_MAP = new HashMap<>();

    static {
        for (DeliveryStatus status : values()) {
            CODE_MAP.put(status.code, status);
        }
    }

    private DeliveryStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static DeliveryStatus fromCode(int code) {
        DeliveryStatus status = CODE_MAP.get(code);
        if (status == null) {
            throw new IllegalArgumentException("Invalid DeliveryStatus code: " + code);
        }
        return status;
    }

    public boolean canTransitionTo(DeliveryStatus newStatus) {
        return switch (this) {
            case PENDING -> newStatus == PICKED_UP || newStatus == CANCELLED;
            case PICKED_UP -> newStatus == IN_TRANSIT || newStatus == CANCELLED;
            case IN_TRANSIT -> newStatus == DELIVERED || newStatus == CANCELLED;
            case DELIVERED, CANCELLED -> false;

            default -> false;
        };
    }
}
