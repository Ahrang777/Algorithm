import java.io.*;
import java.util.*;

public class Main {
    static int R, C, max = 0;
    static char[][] map;
    static boolean[] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[26];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        visited[map[0][0] - 'A'] = true;

        dfs(0, 0, 1);

        System.out.println(max);
    }

    private static void dfs(int x, int y, int total) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isRange(nx, ny) || visited[map[nx][ny] - 'A']) {
                max = Math.max(max, total);
                continue;
            }

            visited[map[nx][ny] - 'A'] = true;
            dfs(nx, ny, total + 1);
            visited[map[nx][ny] - 'A'] = false;
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}