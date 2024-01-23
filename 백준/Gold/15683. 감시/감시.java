import java.io.*;
import java.util.*;

public class Main{
    static class CCTV {
        int index, x, y;

        public CCTV(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }

    public static int n, m;

    // U, R, D, L >> 시계 방향 90도
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static int[][][] ccDir = {
            {{0}},
            {{1}, {2}, {3}, {0}},   // 1번
            {{1, 3}, {0, 2}},   // 2번
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}},   // 3번
            {{0, 1, 3}, {0, 1, 2}, {1, 2, 3}, {2, 3, 0}},   // 4번
            {{0, 1, 2, 3}}  // 5번 CCTV
    };

    public static int minValue = Integer.MAX_VALUE;

    public static ArrayList<CCTV> cctvs = new ArrayList<>();

    public static final int BLANK = 0;
    public static final int WALL = 6;
    public static final int CHECK = -1; // 감시된 영역

    public static void solution(int cnt, int[][] map) {
        if (cnt == cctvs.size()) {
            minValue = Math.min(minValue, getArea(map));
            return;
        }

        int[][] copiedMap = new int[n][m];
        // 각 cctv 확인
        CCTV now = cctvs.get(cnt);

        // 해당 cctv를 90도씩 회전하며 감시
        for (int i = 0; i < ccDir[now.index].length; i++) {
            copyMap(copiedMap, map);

            // 현재 상태에서 감시할 수 있는 방향
            for (int j = 0; j < ccDir[now.index][i].length; j++) {
                int dir = ccDir[now.index][i][j];
                observation(now.x, now.y, dir, copiedMap);
            }

            solution(cnt + 1, copiedMap);
        }
    }

    public static void observation(int x, int y, int dir, int[][] map) {
        while (true) {
            x += dx[dir];
            y += dy[dir];

            if (x >= 0 && x < n && y >= 0 && y < m && map[x][y] != WALL) {
                if (map[x][y] == BLANK) {
                    map[x][y] = CHECK;
                } else {    // 이미 체크했거나 CCTV인 경우
                    continue;
                }
            } else {
                return;
            }
        }
    }

    public static void copyMap(int[][] copiedMap, int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copiedMap[i][j] = map[i][j];
            }
        }
    }

    public static int getArea(int[][] map){

        int total = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == BLANK) {
                    total += 1;
                }
            }
        }

        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < m; j++) {
                int a = Integer.parseInt(stk.nextToken());
                map[i][j] = a;

                if (a >= 1 && a <= 5) {
                    cctvs.add(new CCTV(a, i, j));
                }
            }
        }

        solution(0, map);
        System.out.println(minValue);
    }
}