import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];

        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        List<Position> positions = new ArrayList<>();
//        List<int[]> positions = new ArrayList<>();
        for (int y = 0; y < N; y++) {
            if (map[0][y] == 0) {
//                positions.add(new int[]{0, i});
                positions.add(new Position(0, y));
            }
        }

        System.out.println(bfs(positions) ? "YES" : "NO");
    }

    private static boolean bfs(List<Position> positions) {
        Queue<Position> q = new ArrayDeque<>();
        for (Position p : positions) {
            q.offer(new Position(p.x, p.y));
            map[p.x][p.y] = 2;
        }

        while (!q.isEmpty()) {
            Position now = q.poll();

            if (now.x == M - 1) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (!isRange(nx, ny) || map[nx][ny] >= 1) {
                    continue;
                }

                q.offer(new Position(nx, ny));
                map[nx][ny] = 2;
            }
        }

        return false;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }
}