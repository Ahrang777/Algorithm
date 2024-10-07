import java.util.*;
class Solution {
    static int N, M, answer;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static final int WALL = 0;
    static final int BLANK = 1;
    public int solution(int[][] maps) {
        answer = -1;
        
        N = maps.length;
        M = maps[0].length;
        
        bfs(0, 0, maps);
        
        return answer;
    }
    
    private void bfs(int x, int y, int[][] maps) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        
        q.offer(new int[]{x, y, 1});
        visited[x][y] = true;
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int nowX = now[0];
            int nowY = now[1];
            int cnt = now[2];
            
            if (nowX == N - 1 && nowY == M - 1) {
                answer = cnt;
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];
                
                if (!isRange(nx, ny) || visited[nx][ny] || maps[nx][ny] == WALL) {
                    continue;
                }
                
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny, cnt + 1});
            }
        }
    }
    
    private boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}