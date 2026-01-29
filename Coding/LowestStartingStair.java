public class LowestStartingStair {
    
    /**
     * Finds the lowest possible starting stair where the grasshopper
     * can complete all jumps without going below stair 1.
     * 
     * Mathematical Formula:
     * startingStair = max(1, 1 - min(prefix_sums))
     * 
     * Where prefix_sums[i] = sum of jumps[0] to jumps[i-1]
     * 
     * @param jumps Array of jump values (positive = up, negative = down)
     * @return Lowest valid starting stair number
     */
    public static int findLowestStartingStair(int[] jumps) {
        // Handle empty array
        if (jumps == null || jumps.length == 0) {
            return 1;
        }
        
        int currentSum = 0;
        int minSum = 0; // Track minimum cumulative sum (before any jump, sum = 0)
        
        // Calculate prefix sums and track the minimum
        for (int jump : jumps) {
            currentSum += jump;
            minSum = Math.min(minSum, currentSum);
        }
        
        // If minSum is negative, we need to start higher to compensate
        // Formula: startingStair + minSum >= 1
        // Therefore: startingStair >= 1 - minSum
        int startingStair = 1 - minSum;
        
        // Starting stair must be at least 1
        return Math.max(1, startingStair);
    }
    
    /**
     * Validates the solution by simulating the jumps
     */
    public static boolean validateSolution(int[] jumps, int startingStair) {
        int currentStair = startingStair;
        
        // Check initial position
        if (currentStair < 1) {
            return false;
        }
        
        // Simulate all jumps
        for (int jump : jumps) {
            currentStair += jump;
            if (currentStair < 1) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Helper method to trace the path for debugging
     */
    public static void tracePath(int[] jumps, int startingStair) {
        System.out.print("Path: " + startingStair);
        int currentStair = startingStair;
        
        for (int jump : jumps) {
            currentStair += jump;
            System.out.print(" -> " + currentStair);
        }
        System.out.println();
    }
    
    // Test Cases
    public static void main(String[] args) {
        runTestCase(1, new int[]{1, -4, -2, 3}, 6);
        runTestCase(2, new int[]{-1, -2, -3}, 7);
        runTestCase(3, new int[]{5, 10, 15}, 1);
        runTestCase(4, new int[]{}, 1);
        runTestCase(5, new int[]{-10, 5, -3, 8, -2}, 11);
        runTestCase(6, new int[]{0, 0, 0}, 1);
        runTestCase(7, new int[]{-5}, 6);
        runTestCase(8, new int[]{1, 2, 3, -6, -5, 10}, 6);
    }
    
    public static void runTestCase(int testNum, int[] jumps, int expected) {
        System.out.println("\n=== Test Case " + testNum + " ===");
        System.out.print("Jumps: [");
        for (int i = 0; i < jumps.length; i++) {
            System.out.print(jumps[i]);
            if (i < jumps.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        
        int result = findLowestStartingStair(jumps);
        boolean isValid = validateSolution(jumps, result);
        boolean testPassed = (result == expected && isValid);
        
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Valid: " + isValid);
        
        if (jumps.length > 0) {
            tracePath(jumps, result);
        }
        
        // Verify result-1 would NOT work (except when result = 1)
        if (result > 1) {
            boolean lowerFails = !validateSolution(jumps, result - 1);
            System.out.println("Starting at " + (result - 1) + " fails: " + lowerFails);
        }
        
        System.out.println("TEST " + (testPassed ? "PASSED ✓" : "FAILED ✗"));
    }
}
