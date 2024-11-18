import java.util.*;
class Solution {
    static Queue<int[]>[] memo; // 경로 기록용 
    static int size;
    static int answer;
    public int solution(int[][] points, int[][] routes) {
        size = routes.length;
        memo = new LinkedList[size];
        for(int i = 0; i < size; i++){
            memo[i] = new LinkedList<>();
        }
        function(points, routes); // 경로 계산 
        function2(); // 충돌 계산 
        return answer;      
    }
    public static void function2(){
        int count = 0;
        while(count != size){
            int [][] map = new int [101][101];
            count = 0;
            for(int i = 0; i < size; i++){
                if(memo[i].isEmpty()){
                    count++;
                    continue;
                }
                int [] temp = memo[i].poll();
                map[temp[0]][temp[1]]++;
            }
            for(int i = 0; i < 101; i++){
                for(int j = 0; j < 101; j++){
                    if(map[i][j] > 1) answer++; // 충돌!
                }
            }
        }
    }
    // 규칙4: 이동 우선순위 x좌표 > y좌표 
    public static void function(int [][] points, int [][] routes){
        for(int i = 0; i < size; i++){
            int [] point = points[routes[i][0] - 1];
            int x = point[0];
            int y = point[1];
            memo[i].add(new int[]{x, y});
            for(int j = 1; j < routes[0].length; j++){
                int [] next_point = points[routes[i][j] - 1];
                int nx = next_point[0];
                int ny = next_point[1];
                
                int xp = nx - x; // 다음 포인트로 이동해야하는 x좌표 
                int yp = ny - y; // 다음 포인트로 이동해야하는 y좌표
                while(xp != 0){
                    if(xp > 0){
                        xp--;
                        x++;
                        memo[i].add(new int[]{x, y});
                    }
                    else{
                        xp++;
                        x--;
                        memo[i].add(new int[]{x, y});
                    }
                }
                while(yp != 0){
                    if(yp > 0){
                        yp--;
                        y++;
                        memo[i].add(new int[]{x, y});
                    }
                    else{
                        yp++;
                        y--;
                        memo[i].add(new int[]{x, y});
                    }
                }
            }
        }
    }
}

// import java.util.*;
// class Solution {    
//     static class Robot {
//         // 번호, 현재 목표 지점이 경로 중 몇번쨰인지, 현재 위치, 목표 지점 위치
//         int index, cnt, x, y, ex, ey;
        
//         public Robot(int index, int cnt, int x, int y, int ex, int ey) {
//             this.index = index;
//             this.cnt = cnt;
//             this.x = x;
//             this.y = y;
//             this.ex = ex;
//             this.ey = ey;
//         }
        
//         public void move() {
//             if (x < ex) {   // 아래로 이동
//                 x++;
//             } else if (x > ex) {    // 위로 이동
//                 x--;
//             } else if (y < ey) {    // 우로 이동
//                 y++;
//             } else {    // 좌로 이동
//                 y--;
//             }
//         }
        
//         public boolean isEnd() {
//             return x == ex && y == ey;
//         }
//     }
    
//     static int M, X;
    
//     // r, c / i로봇 j번째 방문
//     public int solution(int[][] points, int[][] routes) {
//         int answer = 0;
        
//         X = routes.length;
//         M = routes[0].length;
        
//         Queue<Robot> q = new ArrayDeque<>();
//         List<Robot> list = new ArrayList<>();        
        
//         for (int i = 0; i < X; i++) {
//             int now = routes[i][0];
//             int next = routes[i][1];
//             Robot r = new Robot(i, 1, points[now][0], points[now][1], points[next][0], points[next][1]);
//             q.offer(r);
//             list.add(r);
//         }
        
//         int[][] visited = new int[101][101];
        
//         while (!q.isEmpty()) {
//             list.clear();
            
//             // q안에 있는 로봇 이동, 위치 갱신, 방문처리
//             while (!q.isEmpty()) {
//                 Robot r = q.poll();
                
//                 r.move();
                
//                 visited[r.x][r.y]++;
                
//                 // 도착지점 도착하면 도착지점 갱신 or 완전 도착
//                 if (r.isEnd()) {
//                     // 완전 종료
//                     if (r.cnt + 1 == M) {
//                         continue;
//                     }
                    
//                     // 다음 도착지점으로 갱신
//                     r.cnt++;
//                     int next = routes[r.index][r.cnt];
//                     r.ex = points[next][0];
//                     r.ey = points[next][1];
//                 }                
                
//                 list.add(r);
//             }
            
            
//             // 방문 내용 초기화
//             for (int i = 0; i < 101; i++) {
//                 for (int j = 0; j < 101; j++) {
//                     if (visited[i][j] > 1) {
//                         answer++;                        
//                     }
//                     visited[i][j] = 0;
//                 }
//             }
//         }
        
//         return answer;
//     }
// }