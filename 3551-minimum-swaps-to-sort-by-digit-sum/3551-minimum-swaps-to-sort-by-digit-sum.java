import java.util.Arrays;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(nums[i], getDigitSum(nums[i]), i);
        }

        Arrays.sort(items, (a, b) -> {
            if (a.sum != b.sum) {
                return Integer.compare(a.sum, b.sum);
            }
            return Integer.compare(a.val, b.val);
        });

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] || items[i].index == i) {
                continue;
            }

            int cycleSize = 0;
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                curr = items[curr].index;
                cycleSize++;
            }

            if (cycleSize > 1) {
                swaps += (cycleSize - 1);
            }
        }

        return swaps;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    private static class Item {
        int val;
        int sum;
        int index;

        Item(int val, int sum, int index) {
            this.val = val;
            this.sum = sum;
            this.index = index;
        }
    }
}