/**
 * Problem 1:
 * Lowest Starting Stair
 *
 *         Description
 *
 * A grasshopper is jumping on a numbered staircase where the bottom stair is numbered 1, the next is 2, and so on. The grasshopper can jump up or down according to an array of jump values.
 *
 * For each jump in the array:
 *         •  If jumps[i] > 0, the grasshopper jumps jumps[i] steps up.
 *         •  If jumps[i] < 0, the grasshopper jumps |jumps[i]| steps down.
 *
 * Find the lowest possible stair number (startingStair) where the grasshopper can begin and remain on the staircase (stair number ≥ 1) throughout all jumps.
 *
 *         Example: jumps = [1, -4, -2, 3], lowest possible stairs: 6
 */

/**
 * Dry run by steps:
 * [1, -4, -2, 3]
 * step1: 1, lowest: 1
 * step2: -3, lowest: 4
 * step3: -5, lowest: 6
 * setp4: -1, lowest: 2
 * So for steps, counting prefixSum, lowest needs to be updated for every step
 */

public class LowestStartingStair {

    public static int lowestStartingStair(int[] jumps) {
        int prefix = 0;
        int minPrefix = 0;
        for (int jump : jumps) {
            prefix += jump; // cur step after jumping
            if (prefix < minPrefix) {
                minPrefix = prefix;
            }
        }
        return 1 - minPrefix; // lowest >= 1
    }

    private static void runTest(int[] jumps, int expected) {
        int actual = lowestStartingStair(jumps);
        if (actual != expected) {
            throw new AssertionError(
                    "FAILED: expected=" + expected + " actual=" + actual
            );
        }
        System.out.println("PASSED: expected=" + expected + " actual=" + actual);
    }

    public static void main(String[] args) {
        runTest(new int[] {1, -4, -2, 3}, 6);
        runTest(new int[] {2, 3, 1}, 1);
        runTest(new int[] {-1, -1, -1}, 4);
        runTest(new int[] {3, -2, -2, 5}, 2);
        runTest(new int[] {}, 1);

        System.out.println("All tests passed.");
    }
}

