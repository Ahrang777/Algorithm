import java.io.*;
import java.util.*;

public class Main {
    static int T, N, M, K;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        List<int[]> positions = new ArrayList<>();

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            positions.clear();
            int cnt = 0;
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                positions.add(new int[]{x, y});
                map[x][y] = 1;
            }

            for (int[] p : positions) {
                int x = p[0], y = p[1];

                if (map[x][y] == 0) {
                    continue;
                }

                bfs(x, y);
                cnt++;
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        map[x][y] = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            x = now[0];
            y = now[1];

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
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
