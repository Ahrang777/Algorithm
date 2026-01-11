import java.io.*;
import java.util.*;

public class Main {
    static int N, M, max = Integer.MIN_VALUE;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int cnt = 0;

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    cnt++;
                    bfs(i, j);
                }
            }
        }

        System.out.println(cnt);
        System.out.println(max == Integer.MIN_VALUE ? 0 : max);
    }

    private static void bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        int area = 0;

        q.offer(new int[]{x, y});
        map[x][y] = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            x = now[0];
            y = now[1];
            area++;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isRange(nx, ny) || map[nx][ny] == 0) {
                    continue;
                }

                map[nx][ny] = 0;
                q.offer(new int[]{nx, ny});
            }
        }

        max = Math.max(max, area);
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
