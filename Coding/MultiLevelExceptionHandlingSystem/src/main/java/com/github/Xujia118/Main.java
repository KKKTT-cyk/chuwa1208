package com.github.Xujia118;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        // Scenario 1: Valid Order
        System.out.println("--- Scenario 1: Valid Order ---");
        runTest(orderService, "ORD-001", 5);

        // Scenario 2: Null Order ID
        System.out.println("\n--- Scenario 2: Null Order ID ---");
        runTest(orderService, null, 5);

        // Scenario 3: Invalid Quantity
        System.out.println("\n--- Scenario 3: Zero or Negative Quantity ---");
        runTest(orderService, "ORD-002", 0);
    }

    private static void runTest(OrderService service, String id, int qty) {
        try {
            service.processOrder(id, qty);
            System.out.println("Success: Order processed for ID " + id);
        } catch (OrderException e) {
            // Accessing the error code and message from your custom exception
            IErrorCode code = e.getErrorCode();
            System.out.println("Caught Expected Exception!");
            System.out.println("Error Code: " + code);
            System.out.println("Message: " + e.getMessage());
        }
    }

    /**
     * We use an interface instead of directly using the enum to decouple the
     * exception from a specific error domain, allowing the same exception class to
     * work with multiple error-code enums, improving extensibility, reusability,
     * and adherence to the Open–Closed Principle.
     */
}