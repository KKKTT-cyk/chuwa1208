package com.github.Xujia118;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Package myPkg = new Package("PKG-123");

        // Test valid transitions
        try {
            myPkg.updateStatus(DeliveryStatus.PICKED_UP);
            myPkg.updateStatus(DeliveryStatus.IN_TRANSIT);
            myPkg.updateStatus(DeliveryStatus.DELIVERED);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test invalid transition: DELIVERED -> IN_TRANSIT
        try {
            System.out.print("Attempting invalid transition... ");
            myPkg.updateStatus(DeliveryStatus.IN_TRANSIT);
        } catch (IllegalStateException e) {
            System.out.println("Caught Expected Error: " + e.getMessage());
        }

        // Demonstrate fromCode()
        DeliveryStatus statusFromCode = DeliveryStatus.fromCode(5);
        System.out.println("Status for code 5: " + statusFromCode.getDescription());
    }
}