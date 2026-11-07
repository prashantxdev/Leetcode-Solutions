class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        java.util.PriorityQueue<int[]> freeServers = new java.util.PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        java.util.PriorityQueue<int[]> busyServers = new java.util.PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }

        long currentTime = 0;
        int taskIndex = 0;

        while (taskIndex < m) {
            currentTime = Math.max(currentTime, (long) taskIndex);

            if (freeServers.isEmpty() && busyServers.peek()[0] > currentTime) {
                currentTime = busyServers.peek()[0];
            }

            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                int[] server = busyServers.poll();
                freeServers.offer(new int[]{server[1], server[2]});
            }

            while (!freeServers.isEmpty() && taskIndex <= currentTime && taskIndex < m) {
                int[] server = freeServers.poll();
                ans[taskIndex] = server[1];
                busyServers.offer(new int[]{(int) currentTime + tasks[taskIndex], server[0], server[1]});
                taskIndex++;
            }
        }

        return ans;
    }
}