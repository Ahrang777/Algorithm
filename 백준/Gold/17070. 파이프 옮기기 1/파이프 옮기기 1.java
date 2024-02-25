import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, result;
    static int[][] map;

    // 0 : 가로 / 1 : 대각선 / 2 : 세로
    static int[][][] dir = {
            {{0, 1, 0}, {1, 1, 1}},
            {{0, 1, 0}, {1, 1, 1}, {1, 0, 2}},
            {{1, 1, 1}, {1, 0, 2}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 1, 0);
        System.out.println(result);
    }

    private static void dfs(int x, int y, int d) {
        if (x == N - 1 && y == N - 1) {
            result++;
            return;
        }

        for (int i = 0; i < dir[d].length; i++) {
            int nx = x + dir[d][i][0];
            int ny = y + dir[d][i][1];
            int nd = dir[d][i][2];

            if (!isRange(nx, ny) || map[nx][ny] == 1) {
                continue;
            }
            if (nd == 1 && (map[x + 1][y] == 1 || map[x][y + 1] == 1)) {
                continue;
            }

            dfs(nx, ny, nd);
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}