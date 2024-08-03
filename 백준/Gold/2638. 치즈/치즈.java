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
    static int N, M, cnt;
    static int[][] map;
    static List<Position> cheeseList;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cheeseList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    cnt++;
                    cheeseList.add(new Position(i, j));
                }
            }
        }

        int time = 0;

        while (cnt != 0) {
            time++;
            visited = new boolean[N][M];
            init(0, 0);
            melt();
        }

        System.out.println(time);
    }

    private static void init(int x, int y) {
        visited[x][y] = true;
        map[x][y] = 2;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 기존에 이미 2로 처리된 경우가 있을 수 있으므로 visited없이 map[nx][ny] >= 1 로 해결 안됨
            if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == 1) {
                continue;
            }

            init(nx, ny);
        }
    }

    private static void melt() {
        for (int i = 0; i < cheeseList.size(); i++) {
            Position now = cheeseList.get(i);

            if (isMelted(now.x, now.y)) {
                cnt--;
                map[now.x][now.y] = 0;
                cheeseList.remove(i);
                i--;
            }
        }
    }

    private static boolean isMelted(int x, int y) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (map[nx][ny] == 2) {
                cnt++;
            }
        }

        return cnt >= 2;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}