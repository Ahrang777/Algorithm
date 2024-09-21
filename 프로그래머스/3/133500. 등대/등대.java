import java.util.*;
class Solution {
    static ArrayList<Integer> graphs[]; // 그래프
    static boolean visited[]; // 방문 체크
    static boolean rights[];  // 불이 켜져 있는지 체크
    static int parent[];  // 자신의 부모 노드를 메모리
    static int answer = 0;
    public int solution(int n, int[][] lighthouse) {
        graphs = new ArrayList[n+1];
        visited = new boolean[n+1];
        rights = new boolean[n+1];
        parent = new int[n+1];
        for(int i = 1; i <= n; ++i){
            graphs[i] = new ArrayList<>();
        }
        for(int i = 0; i < lighthouse.length; ++i){
            graphs[lighthouse[i][0]].add(lighthouse[i][1]);
            graphs[lighthouse[i][1]].add(lighthouse[i][0]);
        }
        
        // 부모 노드를 알기 위한 BFS 탐색
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(1);
        visited[1] = true;
        while(!dq.isEmpty()){
            int now = dq.removeFirst();
            for(int i = 0; i < graphs[now].size(); ++i){
                int next = graphs[now].get(i);
                if(visited[next]) continue;
                visited[next] = true;
                parent[next] = now;
                dq.add(next);
            }
        }
        
        // 리프 노드 부터 체크 하기위한 DFS 탐색
        visited = new boolean[n+1];
        visited[1] = true;
        dfs(1);
        return answer;
    }
    
    public void dfs(int now){
        boolean ch = true;
        
        // 자신의 자식들을 모두 탐색
        for(int i = 0; i < graphs[now].size(); ++i){
          int next = graphs[now].get(i);
          if(visited[next]) continue;
          visited[next] = true;
          dfs(next);
        }
   
        // 현재 자신의 자식이 다 불을 켜져 있으면 자신은 안 켜줘도 된다.
        for(int i = 0; i < graphs[now].size(); ++i){
          int next = graphs[now].get(i);
          if(!rights[next]) ch = false; 
        }
        if(ch) return;
        
        // 자신이 불이 켜져 있으면 넘어간다.
        if(rights[now] == true) return;
        
        // 자신이 불도 안켜져 있고 자신의 부모가 불이 안켜져 있으면 부모의 불을 킨다.
        if(rights[parent[now]] == false){
            ++answer;
            rights[parent[now]] = true;
        }   
    }
//     static int N, answer;
//     static List<Integer>[] graph;
//     static int[] parents;
//     static boolean[] visited;
//     static final int ROOT = 1;
//     public int solution(int n, int[][] lighthouse) {
//         N = n;
        
//         parents = new int[N + 1];
//         visited = new boolean[N + 1];
//         graph = new List[N + 1];
//         for (int i = 1; i <= N; i++) {
//             graph[i] = new ArrayList<>();
//         }
        
//         for (int[] light : lighthouse) {
//             graph[light[0]].add(light[1]);
//             graph[light[1]].add(light[0]);
//         }
        
//         findParent();
//         count();
        
//         return answer;
//     }
    
//     private void count() {
//         Queue<Integer> q = new ArrayDeque<>();
//         Arrays.fill(visited, false);
        
//         for (int i = 1; i <= N; i++) {
//             int parent = parents[i];
//             if (graph[i].size() == 1 && !visited[parent]) {
//                 visited[parent] = true;
//                 q.offer(parent);
//                 answer++;
//             }
//         }
        
//         while (!q.isEmpty()) {
//             int now = q.poll();
//             int parent = parents[now];
//             int grand = parents[parent];
            
//             if (now == ROOT || parent == ROOT)    continue;
//             if (visited[grand]) continue;
            
//             visited[grand] = true;
//             answer++;
//             q.offer(grand);
//         }
//     }
    
//     // 부모노드 정리
//     private void findParent() {
//         Queue<Integer> q = new ArrayDeque<>();
//         parents[ROOT] = ROOT;
//         visited[ROOT] = true;
        
//         for (int child : graph[ROOT]) {
//             visited[child] = true;
//             q.offer(child);
//             parents[child] = ROOT;
//         }
        
//         while (!q.isEmpty()) {
//             int now = q.poll();
            
//             for (int child : graph[now]) {
//                 // 이미 방문한 것 >> 부모
//                 if (visited[child]) continue;
                
//                 visited[child] = true;
//                 parents[child] = now;
//                 q.offer(child);
//             }
//         }
//     }
}