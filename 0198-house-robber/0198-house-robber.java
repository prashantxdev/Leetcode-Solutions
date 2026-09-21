class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        int prev2 = 0;       // dp[i-2]
        int prev1 = nums[0]; // dp[i-1]

        for (int i = 1; i < n; i++) {

            // Rob current house
            int rob = nums[i] + prev2;

            // Don't rob current house
            int skip = prev1;

            int current = Math.max(rob, skip);

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}