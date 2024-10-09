import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            
            answer++;
            bfs(i, visited, computers);
        }
        
        return answer;
    }
    
    private void bfs(int s, boolean[] visited, int[][] computers) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(s);
        visited[s] = true;
        
        while (!q.isEmpty()) {
            int now = q.poll();
            
            for (int i = 0; i < computers.length; i++) {
                if (!visited[i] && computers[now][i] == 1) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}