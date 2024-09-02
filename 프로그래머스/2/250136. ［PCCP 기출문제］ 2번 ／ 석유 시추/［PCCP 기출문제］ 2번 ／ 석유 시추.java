import java.util.*;
class Solution {
    static class Node {
        int x, y;
        
        public Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M;
    static Map<Integer, Integer> map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public int solution(int[][] land) {
        int answer = 0;
        N = land.length;
        M = land[0].length;
        
        map = new HashMap<>();
        int index = 1;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (land[i][j] == 1) {
                    index++;
                    bfs(i, j, index, land);
                }
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for (int y = 0; y < M; y++) {
            set.clear();
            for (int x = 0; x < N; x++) {
                set.add(land[x][y]);
            }
            
            int total = 0;
            for (int i : set) {
                total += map.getOrDefault(i, 0);
            }
            
            answer = Math.max(answer, total);
        }
        
        // 단순 구현 + bfs 시간초과 >> 각 열 확인(최적화 불가), 석유 크기 확인(최적화)
        // 1. 석유 위치 파악해서 1, 2, 3 ... 숫자 붙이기 
        // 2. 석유 번호마다 몇개인지 Map 에 보관 or 배열 최대 범위로?
        // 3. 각 열마다 접근하는 석유 위치 번호 파악 후 합계 
        // 4. 최대값 갱신
        
        return answer;
    }
    
    private void bfs(int x, int y, int index, int[][] land) {
        Queue<Node> q = new ArrayDeque<>();
        land[x][y] = index;
        q.offer(new Node(x, y));
        int total = 0;
        
        while (!q.isEmpty()) {
            Node now = q.poll();
            
            total++;
            
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                        
                if (!isRange(nx, ny) || land[nx][ny] == 0 || land[nx][ny] > 1) {
                    continue;
                }
                        
                land[nx][ny] = index;
                q.offer(new Node(nx, ny));
            }
        }
        
        map.put(index, total);
    }
    
    private boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}