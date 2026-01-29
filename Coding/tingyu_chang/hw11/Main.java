import java.util.Arrays;

/**
 * Assignment: Lowest Starting Stair
 * Date: Jan 26, 2026
 *
 * --- PROBLEM EXPLANATION ---
 *
 * 1. Dry Run (Example: jumps = [1, -4, -2, 3])
 * We track the grasshopper's position relative to a starting point of 0.
 *
 * - Start:      Relative Level = 0
 * - Jump 1 (+1): Relative Level = 1
 * - Jump 2 (-4): Relative Level = -3
 * - Jump 3 (-2): Relative Level = -5  <-- Lowest Point Reached
 * - Jump 4 (+3): Relative Level = -2
 *
 * Analysis: The grasshopper dipped down to a relative level of -5.
 * To ensure the actual stair is always >= 1, our starting stair S must satisfy:
 * S + (-5) >= 1
 * S >= 6
 *
 * 2. CORE REQUIREMENT (Mathematical Notation)
 * Let P_k be the prefix sum (cumulative position) after k jumps.
 * Let min_level be the minimum value among the start (0) and all prefix sums.
 *
 * min_level = min(0, P_0, P_1, ..., P_n)
 *
 * We need to find the smallest integer S (Starting Stair) such that:
 * S + min_level >= 1
 *
 * Therefore, the formula is:
 * S = 1 - min_level
 */
public class Main {

    /**
     * Finds the lowest starting stair such that the grasshopper never goes below stair 1
     *
     * Time Complexity: O(n) - We iterate through the jumps array once
     * Space Complexity: O(1) - We only use a few variables for tracking
     *
     * @param jumps An array of integers representing up/down jumps
     * @return The lowest valid starting stair number
     */
    public static int solve(int[] jumps) {
        int currentLevel = 0;
        int minLevel = 0; // Initialize with 0 because the start position counts as a step

        for (int jump : jumps) {
            currentLevel += jump;

            // Track the lowest point reached relative to the start
            if (currentLevel < minLevel) {
                minLevel = currentLevel;
            }
        }

        // Apply formula: Start >= 1 - LowestPoint
        return 1 - minLevel;
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        System.out.println("Running Lowest Starting Stair Tests...\n");

        // Test Case 1: The Example Case
        // Logic: Lowest point is -5. Result: 1 - (-5) = 6
        runTest("Example Case", new int[]{1, -4, -2, 3}, 6);

        // Test Case 2: All Positive Jumps
        // Logic: Lowest point is 0 (at start). Result: 1 - 0 = 1
        runTest("All Positive", new int[]{1, 2, 3}, 1);

        // Test Case 3: All Negative Jumps
        // Logic: Lowest point is -6 (at end). Result: 1 - (-6) = 7
        runTest("All Negative", new int[]{-2, -3, -1}, 7);

        // Test Case 4: Immediate Deep Drop
        // Logic: Lowest point is -10. Result: 1 - (-10) = 11
        runTest("Immediate Drop", new int[]{-10, 5}, 11);

        // Test Case 5: Net Zero Loop
        // Logic: Goes up to 5, down to -2, back to 0. Lowest is -2. Result: 1 - (-2) = 3
        runTest("Mixed Loop", new int[]{5, -7, 2}, 3);
    }

    /**
     * Helper method to print formatted test results.
     */
    private static void runTest(String testName, int[] input, int expected) {
        int result = solve(input);
        String status = (result == expected) ? "PASS" : "FAIL";

        System.out.printf("[%s] %-15s | Input: %-15s | Expected: %d | Got: %d%n",
                status,
                testName,
                Arrays.toString(input),
                expected,
                result);
    }
}
