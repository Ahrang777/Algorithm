import java.io.*;
import java.util.*;

public class Main {
    static int N, H, result = 1;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                H = Math.max(H, map[i][j]);
            }
        }

        // 장마철 높이 h 이하인 지역은 물에 잠김
        for (int h = 1; h <= H; h++) {
            check(h);
        }

        System.out.println(result);
    }

    private static void check(int h) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        int cnt = 0;

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                // 이미 방문했거나 물에 잠긴 경우
                if (visited[x][y] || map[x][y] <= h) {
                    continue;
                }

                cnt++;
                q.offer(new int[]{x, y});
                visited[x][y] = true;

                while (!q.isEmpty()) {
                    int[] now = q.poll();

                    for (int i = 0; i < 4; i++) {
                        int nx = now[0] + dx[i];
                        int ny = now[1] + dy[i];

                        if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] <= h) {
                            continue;
                        }

                        q.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        result = Math.max(result, cnt);
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}