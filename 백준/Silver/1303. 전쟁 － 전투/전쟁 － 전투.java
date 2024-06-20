import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, team, enemy, cnt;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j])  continue;

                cnt = 0;
                visited[i][j] = true;
                dfs(i, j);

                if (map[i][j] == 'W') {
                    team += cnt * cnt;
                } else {
                    enemy += cnt * cnt;
                }
            }
        }

        System.out.println(team + " " + enemy);
    }

    private static void dfs(int x, int y) {
        cnt++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] != map[x][y]) {
                continue;
            }

            visited[nx][ny] = true;
            dfs(nx, ny);
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}