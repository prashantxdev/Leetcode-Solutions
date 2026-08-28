class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int[] count = new int[100];
        int[] ans = new int[2];

        int index = 0;

        for (int num : nums) {
            count[num]++;

            // Number appeared for the second time
            if (count[num] == 2) {
                ans[index] = num;
                index++;
            }
        }

        return ans;
    }
}