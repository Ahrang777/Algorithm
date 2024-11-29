import java.util.*;
class Solution {
    static int N, M, rx, ry, rex, rey, bx, by, bex, bey, answer = Integer.MAX_VALUE;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] red, blue;
    static int[][] map;
    public int solution(int[][] maze) {
        init(maze);
        
        move(rx, ry, bx, by, 0, false, false);
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    private void move(int rx, int ry, int bx, int by, int cnt, boolean rend, boolean bend) {
        if (cnt >= answer)  return;
        if (!rend && rx == rex && ry == rey) rend = true;
        if (!bend && bx == bex && by == bey) bend = true;
        
        if (rend && bend) {
            answer = cnt;
            return;
        }
        
        // 여기서 만약 새로 리스트 생성하는게 아니라 clear로 초기화 하는거면 
        // java.util.ConcurrentModificationException 예외 발생
        // 새로 리스트 생성하면 heap 메모리에 이전 리스트 남아있음
        // 재귀로 돌면서 현재는 새 리스트를 만들지만 해당 재귀 함수 호출한 이전 함수에서는 이전 리스트를 여전히 사용중 아래 이중 for문 부분
        // 따라서 이전 리스트를 아예 clear로 비우면 이중 for문으로 이용하다가 문제 발생
        // 따라서 그냥 새 리스트를 생성해서 이전 기록은 남겨놔야 한다. 
        List<int[]> nr = new ArrayList<>();
        List<int[]> nb = new ArrayList<>();
        
        // 빨간 수레가 이동 가능한 칸 목록
        if (!rend) {
            for (int i = 0; i < 4; i++) {
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                
                if (!isRange(nx, ny) || red[nx][ny] || map[nx][ny] == 5)   continue;
                
                nr.add(new int[]{nx, ny});
            }
        } else {    // 빨간 수레가 이미 도착한 경우는 해당 위치만 담으면 된다. 파란 수레와 비교하기 위해 필요
            nr.add(new int[]{rx, ry});
        }
        
        // 파란 수레가 이동 가능한 칸 목록
        if (!bend) {
            for (int i = 0; i < 4; i++) {
                int nx = bx + dx[i];
                int ny = by + dy[i];
                
                if (!isRange(nx, ny) || blue[nx][ny] || map[nx][ny] == 5)   continue;
                
                nb.add(new int[]{nx, ny});
            }
        } else {
            nb.add(new int[]{bx, by});
        }
        
        // 빨간, 파란 수레가 이동 가능한 칸 목록 비교하며 가능한 경우로 dfs
        for (int[] r : nr) {
            for (int[] b : nb) {
                // 같은 위치
                if (r[0] == b[0] && r[1] == b[1])   continue;
                
                // 서로 위치 바꾸기
                if (r[0] == bx && r[1] == by && b[0] == rx && b[1] == ry) {
                    continue;
                }
                
                red[r[0]][r[1]] = true;
                blue[b[0]][b[1]] = true;
                
                move(r[0], r[1], b[0], b[1], cnt + 1, rend, bend);
                    
                red[r[0]][r[1]] = false;
                blue[b[0]][b[1]] = false;
            }
        }
    }
    
    
    private void init(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        
        red = new boolean[N][M];       
        blue = new boolean[N][M];
        map = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(maze[i][j] == 0) continue;
                
                if(maze[i][j] == 1){     // 빨간 시작 
                    red[i][j] = true;
                    rx = i; ry = j;
                }
                else if(maze[i][j] == 2){ // 파란 시작 
                    blue[i][j] = true;
                    bx = i; by = j;
                }
                else if(maze[i][j] != 5){
                    if(maze[i][j] == 3){ // 빨간 도착 
                        rex = i; rey = j;
                    } 
                    else{ // 파란 도착 
                        bex = i; bey = j;
                    }
                }
                else{
                    map[i][j] = maze[i][j]; // 벽 데이터 추가 
                }
            }
        }
    }
    
    private boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}