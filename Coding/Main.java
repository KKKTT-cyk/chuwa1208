import java.util.*;

public class Main {

    /* 
    Problem 1:
    Lowest Starting Stair

    Description

    A grasshopper is jumping on a numbered staircase where the bottom stair is numbered 1, the next is 2, and so on. The grasshopper can jump up or down according to an array of jump values.

    For each jump in the array:
            •  If jumps[i] > 0, the grasshopper jumps jumps[i] steps up.
            •  If jumps[i] < 0, the grasshopper jumps |jumps[i]| steps down.

    Find the lowest possible stair number (startingStair) where the grasshopper can begin and remain on the staircase (stair number ≥ 1) throughout all jumps.

    Example: jumps = [1, -4, -2, 3], lowest possible stairs: 6 
     */
    public static int LowestStartingStairs(int[] arr){
        // edge case: empty input
        if (arr == null || arr.length == 0) return 1;

        int prefix = 0;
        int minPrefix = 0;

        for(int i : arr){
            prefix += i;
            minPrefix = Math.min(prefix, minPrefix);
        }

        // because minPrefix + startStairNum > 0
        // therefore, thre return value is startStairNum = 1 - minPrefix
        // also make sure startting satir is at least 1
        return Math.max(1, (1-minPrefix));
    }
    public static void main(String[] args){
        // Sample case
        System.out.println(LowestStartingStairs(new int[]{1, -4, -2, 3})); // expected: 6

        // edge case: empty input
        System.out.println(LowestStartingStairs(new int[]{})); // expected: 1

        // edge case: no advancement
        System.out.println(LowestStartingStairs(new int[]{0, 0, 0, 0, 0}));// expected: 1

        // case: single positive
        System.out.println(LowestStartingStairs(new int[]{4}));// expected: 1

        // case: single negative
        System.out.println(LowestStartingStairs(new int[]{-3}));// expected: 4

        // case: mixed
        System.out.println(LowestStartingStairs(new int[]{0, -1, 0, -2, 3}));// expected: 4
        
    } 
}
