import java.io.*;
import java.util.*;

public class Main {
    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N = 12, M = 6, min, result, bomb;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        solution();
        System.out.println(result);
    }

    private static void solution() {
        while (true) {
            int cnt = 0;

            min = N - 1;    // 중력으로 내려갔을때 뿌요가 있는 가장 높은 위치 >> 바닥에서 제일 높은건 위에서 봤을때 가장 작은값

            // 뿌요 내려오기
            fall();

            // 뿌요 터지기
            bomb = 0;
            visited = new boolean[N][M];

            for (int x = 0; x < min; x++) {
                for (int y = 0; y < M; y++) {
                    if (visited[x][y] || map[x][y] == '.') {
                        continue;
                    }
                    bfs(x, y);
                }
            }

            // 만약 터진게 하나도 없다면 종료
            if (bomb == 0) {
                return;
            }

            result++;
        }
    }

    private static void bfs(int x, int y) {
        List<Position> positions = new ArrayList<>();
        Queue<Position> q = new ArrayDeque<>();
        q.offer(new Position(x, y));
        visited[x][y] = true;
        positions.add(new Position(x, y));

        char color = map[x][y];

        while (!q.isEmpty()) {
            Position now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] != color) {
                    continue;
                }

                visited[nx][ny] = true;
                q.offer(new Position(nx, ny));
                positions.add(new Position(nx, ny));
            }
        }

        if (positions.size() >= 4) {
            bomb++;
            for (Position p : positions) {
                map[p.x][p.y] = '.';
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static void fall() {
        for (int y = 0; y < M; y++) {
            int cnt = 0;
            for (int x = N - 1; x >= 0; x--) {
                if (map[x][y] != '.') {
                    cnt++;
                    char color = map[x][y];
                    map[x][y] = '.';
                    map[N - cnt][y] = color;
                    Math.min(min, N - cnt);
                }
            }
        }
    }
}