import java.io.*;
import java.util.*;

public class Main {
    static class Position {
        int x, y, time;

        public Position(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    static int N, M, result;
    static int[][] map;
    static List<Position> list;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        list = new ArrayList<>();

        // 토마토 개수
        int total = N * M;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    list.add(new Position(i, j, 0));
                }

                if (map[i][j] == -1) {
                    total--;
                }
            }
        }

        bfs(total);

        System.out.println(result);
    }

    private static void bfs(int total) {
        Queue<Position> q = new ArrayDeque<>();

        for (Position p : list) {
            q.offer(p);
        }

        while (!q.isEmpty()) {
            Position now = q.poll();
            total--;

            result = Math.max(result, now.time);

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (!isRange(nx, ny) || map[nx][ny] != 0) {
                    continue;
                }

                q.offer(new Position(nx, ny, now.time + 1));
                map[nx][ny] = 1;
            }
        }

        if (total != 0) {
            result = -1;
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
