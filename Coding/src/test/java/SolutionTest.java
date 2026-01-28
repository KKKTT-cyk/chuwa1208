import org.junit.jupiter.api.Test;

public class SolutionTest {

    /*
        for any subarray starts from index 0, its sum + beginningStair >= 1
        which means, for any 0 <= i < jumps.length, beginningStairs >= 1 - sum(jumps[0...i])
        Therefore, LowestStartStairs = 1 - min(sum(jumps[0...i] for i in range(0, jumps.length)))
     */
    public static int findLowestStairs(int[] jumps) {
        if(jumps.length == 0) return 1;
        int min_val = Integer.MAX_VALUE;
        int cur_sum = 0;
        for (int jump : jumps) {
            cur_sum = cur_sum + jump;
            min_val = Math.min(min_val, cur_sum);
        }
        return min_val >= 0 ? 1 : 1 - min_val;
    }

    @Test
    void testAllStepsDown() {
        int[] jumps = new int[]{-1,-2,-3,-4};
        int expected = 11;
        assert expected == findLowestStairs(jumps);
    }

    @Test
    void testAllStepsUp() {
        int[] jumps = new int[]{1,2,3,4};
        int expected = 1;
        assert expected == findLowestStairs(jumps);
    }

    @Test
    void testStepsUpAndDown() {
        int[] jumps = new int[]{1,-5,3,-2};
        int expected = 5;
        assert expected == findLowestStairs(jumps);
    }

    @Test
    void testStepsDownAndUp() {
        int[] jumps = new int[]{-1,-3,4,1, -6};
        int expected = 6;
        assert expected == findLowestStairs(jumps);
    }


    public static void main(String[] args) {
        System.out.println("hello");
    }
}
