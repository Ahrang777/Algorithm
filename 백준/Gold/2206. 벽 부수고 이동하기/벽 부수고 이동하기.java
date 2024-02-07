import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Position {
        int x, y, brokenWall;

        public Position(int x, int y, int brokenWall) {
            this.x = x;
            this.y = y;
            this.brokenWall = brokenWall;
        }
    }
    static int N, M, min;
    static int[][] map;
    static final int BLANK = 0;
    static final int WALL = 1;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0, 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void bfs(int x, int y) {
        Queue<Position> q = new ArrayDeque<>();

        // [][][벽 부순 개수]
        int[][][] visited = new int[N][M][2];

        q.offer(new Position(x, y, 0));
        visited[x][y][0] = 1;

        while (!q.isEmpty()) {
            Position now = q.poll();

            if (now.x == N - 1 && now.y == M - 1) {
                min = visited[now.x][now.y][now.brokenWall];
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (!isRange(nx, ny)) {
                    continue;
                }

                // 벽 있는 경우 && 부순 벽 갯수 0인 경우
                if (map[nx][ny] == 1 && now.brokenWall == 0) {
                    visited[nx][ny][1] = visited[now.x][now.y][0] + 1;
                    q.offer(new Position(nx, ny, 1));
                }

                // 벽 없는 경우 && 현재 부순갯수 방문배열에서 방문 안한경우
                if (map[nx][ny] == 0 && visited[nx][ny][now.brokenWall] == 0) {
                    visited[nx][ny][now.brokenWall] = visited[now.x][now.y][now.brokenWall] + 1;
                    q.offer(new Position(nx, ny, now.brokenWall));
                }
            }

        }

    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}