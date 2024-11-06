import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H;
    static int[][] isSelected;
    static int result = -1;
    static final int RIGHT = 1;
    static final int LEFT = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        isSelected = new int[H][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            isSelected[a][b] = RIGHT;
            isSelected[a][b + 1] = LEFT;
        }

        for (int i = 0; i <= 3; i++) {
            if (dfs(0, 0, i)) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }

    private static boolean dfs(int cnt, int total, int len) {
        if (total == len) {
            return isValid();
        }

        if (cnt == H * N) {
            return false;
        }

        int x = cnt / N;
        int y = cnt % N;

        if (y != N - 1 && isSelected[x][y] == 0 && isSelected[x][y + 1] == 0) {
            isSelected[x][y] = RIGHT;
            isSelected[x][y + 1] = LEFT;

            if (dfs(cnt + 1, total + 1, len)) return true;

            isSelected[x][y] = 0;
            isSelected[x][y + 1] = 0;
        }

        return dfs(cnt + 1, total, len);
    }

    private static boolean isValid() {
        for (int y = 0; y < N; y++) {
            if (!isReached(y)) {
                return false;
            }
        }

        return true;
    }

    // index -> index 도달 가능?
    private static boolean isReached(int index) {
        int x = 0, y = index;

        while (x < H) {
            if (isSelected[x][y] == RIGHT) {
                y += 1;
            } else if (isSelected[x][y] == LEFT) {
                y -= 1;
            }
            x += 1;
        }

        return y == index;
    }
}