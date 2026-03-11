import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, cnt;
    static boolean[][] visited;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()) - 1;

        visited = new boolean[N][M];
        int result = 1;

        int sx = 0, sy = 0, ex = N - 1, ey = M - 1;

        if (K >= 0) {
            ex = K / M; ey = K % M;

            visited[sx][sy] = true;
            countRoot(sx, sy, ex, ey);
            sx = ex;
            sy = ey;
            ex = N - 1;
            ey = M - 1;
            result *= cnt;
            cnt = 0;
        }

        visited[sx][sy] = true;
        countRoot(sx, sy, ex, ey);
        result *= cnt;

        System.out.println(result);
    }

    private static void countRoot(int sx, int sy, int ex, int ey) {
        if (sx == ex && sy == ey) {
            cnt++;
            return;
        }

        for (int i = 0; i < 2; i++) {
            int nx = sx + dx[i];
            int ny = sy + dy[i];

            if (!isRange(nx, ny) || visited[nx][ny]) {
                continue;
            }

            visited[nx][ny] = true;
            countRoot(nx, ny, ex, ey);
            visited[nx][ny] = false;
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}
