import java.util.*;

class Solution {
    private List<Integer>[] originalTree;
    private List<Integer>[] newTree;
    private int[] newParent;
    private int[] ans;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.originalTree = new ArrayList[n];
        this.newTree = new ArrayList[n];
        this.newParent = new int[n];
        this.ans = new int[n];

        for (int i = 0; i < n; i++) {
            originalTree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
            newParent[i] = parent[i];
        }

        for (int i = 1; i < n; i++) {
            originalTree[parent[i]].add(i);
        }

        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        buildNewParents(0, lastSeen);

        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        computeSubtreeSizes(0);
        return ans;
    }

    private void buildNewParents(int node, int[] lastSeen) {
        int ch = s.charAt(node) - 'a';
        int prevAncestor = lastSeen[ch];

        if (node != 0 && prevAncestor != -1) {
            newParent[node] = prevAncestor;
        }

        lastSeen[ch] = node;
        for (int child : originalTree[node]) {
            buildNewParents(child, lastSeen);
        }
        lastSeen[ch] = prevAncestor;
    }

    private int computeSubtreeSizes(int node) {
        int size = 1;
        for (int child : newTree[node]) {
            size += computeSubtreeSizes(child);
        }
        ans[node] = size;
        return size;
    }
}