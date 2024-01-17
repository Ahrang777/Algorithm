import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int M, N, min;
    static int[][] map;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static final int BLANK = 0;
    static final int WALL = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        min = Integer.MAX_VALUE;

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        bfs(0, 0);

        System.out.println(min);
    }

    private static void bfs(int x, int y) {
        Deque<Position> q = new ArrayDeque<>();
        int[][] visited = new int[N][M];

        q.offer(new Position(x, y));
        visited[x][y] = 1;

        while (!q.isEmpty()) {
            Position now = q.poll();
            int curX = now.x;
            int curY = now.y;

            if (curX == N - 1 && curY == M - 1) {
                min = Math.min(min, visited[curX][curY] - 1);
            }

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (!isRange(nx, ny)) {
                    continue;
                }

                if (visited[nx][ny] != 0) {
                    continue;
                }

                if (map[nx][ny] == WALL) {
                    visited[nx][ny] = visited[curX][curY] + 1;
                    q.offer(new Position(nx, ny));
                } else {
                    visited[nx][ny] = visited[curX][curY];
                    q.offerFirst(new Position(nx, ny));
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}