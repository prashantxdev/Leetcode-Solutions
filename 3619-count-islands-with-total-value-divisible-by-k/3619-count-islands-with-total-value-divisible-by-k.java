class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && !visited[i][j]) {
                    long totalValue = bfs(grid, visited, i, j);
                    if (totalValue % k == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    
    private long bfs(int[][] grid, boolean[][] visited, int startX, int startY) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        java.util.Queue<int[]> queue = new java.util.LinkedList<>();
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;
        long sum = 0;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            sum += grid[r][c];
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] > 0 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return sum;
    }
}