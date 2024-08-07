import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, max;
    static int[][] map, dp;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        max = Integer.MIN_VALUE;

        map = new int[N][N];
        dp = new int[N][N]; // x, y 좌표에서 판다가 출발했을 때 이동할 수 있는 칸의 수 최대값

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }

        System.out.println(max);
    }

    private static int dfs(int x, int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        dp[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isRange(nx, ny)) {
                continue;
            }

            if (map[nx][ny] > map[x][y]) {
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);                
            }
        }

        return dp[x][y];
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}