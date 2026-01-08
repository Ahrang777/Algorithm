import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0, 0);
    }

    private static void bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y, 1});
        map[x][y] = 0;
        int answer = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            x = now[0];
            y = now[1];
            int cnt = now[2];

            if (x == N - 1 && y == M - 1) {
                answer = now[2];
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isRange(nx, ny) || map[nx][ny] == 0) {
                    continue;
                }

                q.offer(new int[]{nx, ny, cnt + 1});
                map[nx][ny] = 0;
            }
        }

        System.out.println(answer);
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
