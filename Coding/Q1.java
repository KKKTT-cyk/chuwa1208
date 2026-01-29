// Problem 1:
// Lowest Starting Stair

// Description

// A grasshopper is jumping on a numbered staircase where the bottom stair is
// numbered 1, the next is 2, and so on. The grasshopper can jump up or down
// according to an array of jump values.

// For each jump in the array:
// • If jumps[i] > 0, the grasshopper jumps jumps[i] steps up.
// • If jumps[i] < 0, the grasshopper jumps |jumps[i]| steps down.

// Find the lowest possible stair number (startingStair) where the grasshopper
// can begin and remain on the staircase (stair number ≥ 1) throughout all
// jumps.

// Example: jumps = [1, -4, -2, 3], lowest possible stairs: 6

// Before writing any code, carefully dry-run the given example step by step.
// After the requirement is clearly stated, implement the solution in code.
// You must also provide test cases:
// Code requirements:

// Dry run the example:
/**
 * jumps = [1, -4, -2, 3]
 * 
 * 1. Start at stair 1
 * Jump 1 step up to stair 2
 * Jump 4 steps down to stair -2
 * Jump 2 steps down to stair -4
 * Jump 3 steps up to stair -1
 * Not ok
 * 
 * 2. Start at stair 2
 * Jump 1 step up to stair 3
 * Jump 4 steps down to stair -2
 * Jump 2 steps down to stair -4
 * Jump 3 steps up to stair 1
 * Not ok
 * 
 * 3. Start at stair 3
 * Jump 1 step up to stair 4
 * Jump 4 steps down to stair 0
 * Jump 2 steps down to stair -2
 * Jump 3 steps up to stair 1
 * Not ok
 * 
 * 4. Start at stair 4
 * Jump 1 step up to stair 5
 * Jump 4 steps down to stair 1
 * Jump 2 steps down to stair -1
 * Jump 3 steps up to stair 2
 * Not ok
 * 
 * 5. Start at stair 5
 * Jump 1 step up to stair 6
 * Jump 4 steps down to stair 2
 * Jump 2 steps down to stair 0
 * Jump 3 steps up to stair 3
 * Not ok
 * 
 * 6. Start at stair 6
 * Jump 1 step up to stair 7
 * Jump 4 steps down to stair 3
 * Jump 2 steps down to stair 1
 * Jump 3 steps up to stair 4
 * OK!
 * 
 */

public class Q1 {
    public static int lowestStartingStair(int[] jumps) {
        int prefixSum = 0;
        int min = 0;
        for (int jump : jumps) {
            prefixSum += jump;
            min = Math.min(min, prefixSum);
        }
        return -min + 1;
    }

    public static void main(String[] args) {
        int[] jumps1 = { 1, -4, -2, 3 };
        System.out.println(lowestStartingStair(jumps1)); // 6
        int[] jumps2 = { 1, 2, 3, 4, 5 };
        System.out.println(lowestStartingStair(jumps2)); // 1
        int[] jumps3 = { -1, -2, -3, -4, -5 };
        System.out.println(lowestStartingStair(jumps3)); // 16
        int[] jumps4 = { 0, 0, 0, 0, 0 };
        System.out.println(lowestStartingStair(jumps4)); // 1
        int[] jumps5 = { 1, 1, 1, 1, 1 };
        System.out.println(lowestStartingStair(jumps5)); // 1
    }
}