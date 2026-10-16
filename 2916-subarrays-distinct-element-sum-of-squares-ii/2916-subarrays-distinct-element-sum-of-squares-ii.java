import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1000000007;
    private long[] tree;
    private long[] lazy;

    private void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = l + (r - l) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void apply(int node, int l, int r, long val) {
        tree[node] = (tree[node] + val * (r - l + 1) % MOD) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        push(node, l, r);
        int mid = l + (r - l) / 2;
        update(2 * node, l, mid, ql, qr, val);
        update(2 * node + 1, mid + 1, r, ql, qr, val);
        tree[node] = (tree[2 * node] + tree[2 * node + 1]) % MOD;
    }

    private long query(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return tree[node];
        push(node, l, r);
        int mid = l + (r - l) / 2;
        long leftSum = query(2 * node, l, mid, ql, qr);
        long rightSum = query(2 * node + 1, mid + 1, r, ql, qr);
        return (leftSum + rightSum) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        tree = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> lastPos = new HashMap<>();
        long totalSum = 0;
        long currentSumSquares = 0;

        for (int i = 0; i < n; i++) {
            int prev = lastPos.getOrDefault(nums[i], -1);
            
            long rangeSum = query(1, 0, n - 1, prev + 1, i);
            long addedVal = (2 * rangeSum + (i - prev)) % MOD;
            currentSumSquares = (currentSumSquares + addedVal) % MOD;
            
            update(1, 0, n - 1, prev + 1, i, 1);
            lastPos.put(nums[i], i);
            
            totalSum = (totalSum + currentSumSquares) % MOD;
        }

        return (int) totalSum;
    }
}