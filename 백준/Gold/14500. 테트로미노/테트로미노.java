import java.io.*;
import java.util.*;

public class Main{
    public static int n, m;
    public static int[][] map = new int[500][500];

    public static boolean[][] visited = new boolean[500][500];
    public static int maxValue;

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static void dfs(int cnt, int sum, int prevX, int prevY) {
        if (cnt == 4) {
            maxValue = Math.max(maxValue, sum);

            return;
        }

        for (int i = 0; i < 4; i++) {
            int x = prevX + dx[i];
            int y = prevY + dy[i];

            if (x >= 0 && x < n && y >= 0 && y < m) {
                if (!visited[x][y]) {
                    visited[x][y] = true;
                    dfs(cnt + 1, sum + map[x][y], x, y);
                    visited[x][y] = false;
                }
            }
        }
    }

    // ㅗ 모양을 찾는다. 가운데 있는 좌표를 기준으로 세 방향을 탐색한다.
    public static void another(int x, int y) {
        // 1. 맵의 꼭지점일때는 ㅗ 모양 불가능
        if ((x == 0 || x == n - 1) && (y == 0 || y == m - 1)) return;

        int sum = map[x][y];

        // 2. 맵의 테두리일때는 모양이 하나
        if (x == 0)
            sum += map[x][y - 1] + map[x][y + 1] + map[x + 1][y];
        else if (x == n - 1)
            sum += map[x][y - 1] + map[x][y + 1] + map[x - 1][y];
        else if (y == 0)
            sum += map[x - 1][y] + map[x + 1][y] + map[x][y + 1];
        else if (y == m - 1)
            sum += map[x - 1][y] + map[x + 1][y] + map[x][y - 1];
        else {
            // 3. 맵의 테두리가 아닐 때는 4 개의 모양을 비교
            sum = Math.max(sum, map[x][y] + map[x + 1][y] + map[x][y - 1] + map[x][y + 1]);
            sum = Math.max(sum, map[x][y] + map[x - 1][y] + map[x][y - 1] + map[x][y + 1]);
            sum = Math.max(sum, map[x][y] + map[x][y + 1] + map[x - 1][y] + map[x + 1][y]);
            sum = Math.max(sum, map[x][y] + map[x][y - 1] + map[x - 1][y] + map[x + 1][y]);
        }

        maxValue = Math.max(maxValue, sum);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // 처음 시작
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(1, map[i][j], i, j);
                visited[i][j] = false;
                another(i, j);
            }
        }

        System.out.println(maxValue);
    }
}