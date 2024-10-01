import java.util.*;
class Solution {
    static int N, answer;
    static List<Integer>[] graph;
    static boolean[][][] visited;
    static int[] nodes;
    public int solution(int[] info, int[][] edges) {
        N = info.length;
        nodes = info;
        
        // 노드번호, 양의 수, 늑대의 수
        visited = new boolean[N][N + 1][N + 1];
        graph = new List[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        visited[0][0][0] = true;
        dfs(0, 0, 0);
        
        return answer;
    }
    
    private void dfs(int now, int s, int w) {
        int origin = -1;
        if (nodes[now] == 0) {
            s++;
            origin = 0;
        } else if (nodes[now] == 1) {
            w++;
            origin = 1;
        }
        
        if (s <= w) {
            return;
        }
        
        answer = Math.max(answer, s);
        
        for (int next : graph[now]) {
            if (visited[next][s][w]) {
                continue;
            }
            
            nodes[now] = -1;
            visited[now][s][w] = true;
            dfs(next, s, w);
            
            nodes[now] = origin;
            visited[now][s][w] = false;
        }
    }
}