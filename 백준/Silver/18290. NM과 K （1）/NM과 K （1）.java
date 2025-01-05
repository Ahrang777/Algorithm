import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, max = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0, 0);
        System.out.println(max);
    }

    private static void dfs(int x, int y, int cnt, int total) {
        if (cnt == K) {
            max = Math.max(max, total);

            return;
        }

        for (int i = x; i < N; i++) {
            for (int j = y; j < M; j++) {
                if (!visited[i][j] && isValid(i, j)) {
                    visited[i][j] = true;
                    dfs(x, y, cnt + 1, total + map[i][j]);
                    visited[i][j] = false;
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static boolean isValid(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isRange(nx, ny) && visited[nx][ny]) {
                return false;
            }
        }

        return true;
    }
}