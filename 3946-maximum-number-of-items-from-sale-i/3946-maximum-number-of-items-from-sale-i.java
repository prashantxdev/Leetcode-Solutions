import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        for (int[] item : items) {
            if (item[0] > maxFactor) {
                maxFactor = item[0];
            }
        }

        int[] freeCount = new int[n];
        for (int i = 0; i < n; i++) {
            int fi = items[i][0];
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % fi == 0) {
                    cnt++;
                }
            }
            freeCount[i] = cnt;
        }

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, Integer.MIN_VALUE / 4);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int firstGain = 1 + freeCount[i];
            int[] nextDp = Arrays.copyOf(dp, budget + 1);

            for (int b = price; b <= budget; b++) {
                if (dp[b - price] + firstGain > nextDp[b]) {
                    nextDp[b] = dp[b - price] + firstGain;
                }
            }

            for (int b = price; b <= budget; b++) {
                if (nextDp[b - price] + 1 > nextDp[b]) {
                    nextDp[b] = nextDp[b - price] + 1;
                }
            }

            dp = nextDp;
        }

        int ans = 0;
        for (int val : dp) {
            if (val > ans) {
                ans = val;
            }
        }
        return ans;
    }
}