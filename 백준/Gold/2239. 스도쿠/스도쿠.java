import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    final static int N = 9;
    static boolean isEnd;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        dfs(0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int cnt) {
        if (cnt == 81) {
            isEnd = true;
            return;
        }

        int x = cnt / N, y = cnt % N;

        if (map[x][y] == 0) {
            for (int i = 1; i <= N; i++) {
                if (!isValid(x, y, i)) {
                    continue;
                }

                map[x][y] = i;
                dfs(cnt + 1);
                if (isEnd) {
                    return;
                }
                map[x][y] = 0;
            }
        } else {
            dfs(cnt + 1);
        }
    }

    private static boolean isValid(int x, int y, int num) {
        for (int i = 0; i < N; i++) {
            if (map[x][i] == num || map[i][y] == num) {
                return false;
            }
        }

        x = x / 3 * 3;
        y = y / 3 * 3;

        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (map[i][j] == num)   return false;
            }
        }

        return true;
    }
}