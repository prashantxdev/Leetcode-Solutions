class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int maxDiff = 0;
        for (int i = 0; i < n; i++) {
            maxDiff = Math.max(maxDiff, Math.abs(nums1[i] - nums2[i]));
        }
        
        int[] count = new int[maxDiff + 1];
        for (int i = 0; i < n; i++) {
            count[Math.abs(nums1[i] - nums2[i])]++;
        }
        
        long totalK = (long) k1 + k2;
        
        for (int i = maxDiff; i > 0; i--) {
            if (count[i] > 0) {
                long take = Math.min(totalK, count[i]);
                count[i] -= take;
                count[i - 1] += take;
                totalK -= take;
                if (totalK == 0) {
                    break;
                }
            }
        }
        
        long ans = 0;
        for (int i = 1; i <= maxDiff; i++) {
            if (count[i] > 0) {
                ans += (long) count[i] * i * i;
            }
        }
        
        return ans;
    }
}