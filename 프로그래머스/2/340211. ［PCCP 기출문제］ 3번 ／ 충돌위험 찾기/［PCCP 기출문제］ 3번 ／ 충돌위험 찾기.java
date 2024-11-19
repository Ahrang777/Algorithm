import java.util.*;
class Solution {    
    static class Robot {
        // 번호, 현재 목표 지점이 경로 중 몇번쨰인지, 현재 위치, 목표 지점 위치
        int index, cnt, x, y, ex, ey;
        
        public Robot(int index, int cnt, int x, int y, int ex, int ey) {
            this.index = index;
            this.cnt = cnt;
            this.x = x;
            this.y = y;
            this.ex = ex;
            this.ey = ey;
        }
        
        public void move() {
//             if (x < ex) {
//                 x++;
//                 return;
//             }
            
//             if (x > ex) {
//                 x--;
//                 return;
//             }
            
//             if (y < ey) {
//                 y++;
//                 return;
//             }
            
//             if (y > ey) {
//                 y--;
//                 return;
//             }
            
            if (x < ex) {   // 아래로 이동
                x++;
            } else if (x > ex) {    // 위로 이동
                x--;
            } else if (y < ey) {    // 우로 이동
                y++;
            } else {    // 좌로 이동
                y--;
            }
            
        }
        
        public boolean isEnd() {
            return this.x == this.ex && this.y == this.ey;
        }
    }
    
    static int M, X;
    
    // r, c / i로봇 j번째 방문
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        X = routes.length;  // 로봇 개수
        M = routes[0].length;   // 경로 포인트 수
        
        Queue<Robot> q = new ArrayDeque<>();
        List<Robot> list = new ArrayList<>();      
        
        int[][] visited = new int[101][101];
        
        for (int i = 0; i < X; i++) {
            int now = routes[i][0] - 1;
            int next = routes[i][1] - 1;
            
            int x = points[now][0], y = points[now][1], ex = points[next][0], ey = points[next][1];
            
            q.offer(new Robot(i, 1, x, y, ex, ey));
            visited[x][y]++;
        }
        
        
        while (!q.isEmpty()) {
            list.clear();
            
            // 방문 내용 초기화
            for (int i = 0; i < 101; i++) {
                for (int j = 0; j < 101; j++) {
                    if (visited[i][j] > 1) {
                        answer++;     
                    }
                    visited[i][j] = 0;
                }
            }
            
            // q안에 있는 로봇 이동, 위치 갱신, 방문처리
            while (!q.isEmpty()) {
                Robot r = q.poll();
                
                r.move();
                
                visited[r.x][r.y]++;
                
                // 도착지점 도착하면 도착지점 갱신 or 완전 도착
                if (r.isEnd()) {
                    // 완전 종료
                    if (r.cnt + 1 == M) {
                        continue;
                    }
                    
                    // 다음 도착지점으로 갱신
                    r.cnt++;
                    int next = routes[r.index][r.cnt] - 1;
                    r.ex = points[next][0];
                    r.ey = points[next][1];
                }                
                
                list.add(r);
            }
            
            
            
            
            q.addAll(list);
        }
        
        // 모든 로봇이 종료한 마지막 순간에 마지막 도착지점 충돌확인
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (visited[i][j] > 1) {
                    answer++;     
                }
            }
        }
        
        return answer;
    }
}