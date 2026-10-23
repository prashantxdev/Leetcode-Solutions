class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int k0 = (1 << (32 - Integer.numberOfLeadingZeros(n - 1))) - 1;
        int k = k0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x != i) {
                k &= x;
            }
        }
        return k == k0 ? 0 : k;
    }
}