package p3;

/*
- Create enum `DeliveryStatus` with:
  - `PENDING(1, "Pending")`, `PICKED_UP(2, "Picked up")`, `IN_TRANSIT(3, "In transit")`, `DELIVERED(4, "Delivered")`, `CANCELLED(5, "Cancelled")`
  - Private fields: `int code`, `String description`
  - Private constructor
  - `public int getCode()`, `public String getDescription()`
  - `public static DeliveryStatus fromCode(int code)` - returns enum by code, throw `IllegalArgumentException` if invalid
  - `public boolean canTransitionTo(DeliveryStatus newStatus)` - business logic for valid transitions
 */

public enum DeliveryStatus {
    // Enum constants with parameters
    PENDING(1, "Pending"),
    PICKED_UP(2, "Picked up"),
    IN_TRANSIT(3, "In transit"),
    DELIVERED(4, "Delivered"),
    CANCELLED(5, "Cancelled");

    private final int code;
    private final String description;

    // Private constructor (Implicitly private for Enums)
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

    // Static lookup method: Returns Enum from integer code
    public static DeliveryStatus fromCode(int code) {
        for (DeliveryStatus status : DeliveryStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + code);
    }

    // Business Logic: Defines valid state transitions
    public boolean canTransitionTo(DeliveryStatus newStatus) {
        // Example Logic:
        // 1. Cannot change if already delivered or cancelled
        if (this == DELIVERED || this == CANCELLED) {
            return false;
        }
        // 2. Allow cancelling from any state (except delivered/cancelled)
        if (newStatus == CANCELLED) {
            return true;
        }
        // 3. Normal flow: Code must increase by exactly 1 (e.g., 1->2, 2->3)
        return newStatus.code == this.code + 1;
    }
}
