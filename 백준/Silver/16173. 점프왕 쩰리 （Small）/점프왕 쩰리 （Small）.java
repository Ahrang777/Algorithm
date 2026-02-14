import java.io.*;
import java.util.*;

public class Main {
    static int N, map[][];
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    static boolean isSuccess;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(isSuccess ? "HaruHaru" : "Hing");
    }

    // 현재x, 현재y, 이동해야하는 칸수
    private static void dfs(int x, int y) {
        if (map[x][y] == -1) {
            isSuccess = true;
            return;
        }

        if (isSuccess) {
            return;
        }

        int cnt = map[x][y];

        if (cnt == 0) {
            return;
        }

        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i] * cnt;
            int ny = y + dy[i] * cnt;

            if (!isRange(nx, ny)) {
                continue;
            }

            dfs(nx, ny);
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
