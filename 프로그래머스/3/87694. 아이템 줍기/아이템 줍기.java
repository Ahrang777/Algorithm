import java.util.*;
class Solution {
    static class Rectangle {
        int lx, ly, rx, ry;
        
        public Rectangle (int lx, int ly, int rx, int ry) {
            this.lx = lx;
            this.ly = ly;
            this.rx = rx;
            this.ry = ry;
        }
        
        public boolean isInside(int x, int y) {
            return x > lx && x < rx && y > ly && y < ry;
        }
    }
    
    static int answer;
    static int[][] map;
    static Rectangle[] rectangles;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int len = rectangle.length;
        map = new int[51 * 2][51 * 2];
        rectangles = new Rectangle[len];
        
        for (int i = 0; i < len; i++) {
            // 좌측하단 x, 좌측하단 y, 우측상단 x, 우측상단 y
            int[] r = rectangle[i];
            
            // . .
            // . .
            // ㄷ모양이 단순히 1로 좌표를 표시하면 위와같이 되며 ㄷ인지 ㅁ인지 구분이 안간다. 
            // 2배로 늘려서 표시하면 아래처럼 구분이 가능하다. 따라서 2배로 늘리고 마지막에 /2하자
            // . . . . 
            // .
            // . 
            // . . . . 
            int lx = r[0] * 2;
            int ly = r[1] * 2;
            int rx = r[2] * 2;
            int ry = r[3] * 2;
            
            rectangles[i] = new Rectangle(lx, ly, rx, ry);
            
            for (int x = lx; x <= rx; x++) {
                for (int y = ly; y <= ry; y++) {
                    map[x][y] = 1;
                }
            }
        }
        
        bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
        
        return answer;
    }
    
    private void bfs(int sx, int sy, int ex, int ey) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy, 1});
        map[sx][sy] = -1;
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            int cnt = now[2];
            
            if (x == ex && y == ey) {
                // 2배로 늘리고 이동했으니 /2
                answer = cnt / 2;
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (!isRange(nx, ny) || map[nx][ny] != 1) {
                    continue;
                }
                
                // 테두리가 아닌 사각형 내부인 경우 확인
                if (isRectangleInside(nx, ny)) {
                    continue;
                }
                
                map[nx][ny] = -1;
                q.offer(new int[]{nx, ny, cnt + 1});
            }
        }
    }
    
    private boolean isRectangleInside(int x, int y) {
        for (Rectangle rectangle : rectangles) {
            if (rectangle.isInside(x, y)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isRange(int x, int y) {
        return x >= 1 && x <= 100 && y >= 1 && y <= 100;
    }
}