import java.io.*;
import java.util.*;

public class Main {
    static int[][] board;
    static int N;
    static boolean isEnd;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = 9;
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 0; j < N; j++) {
                board[i][j] = str.charAt(j) - '0';
            }
        }

        dfs(0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]);
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

        if (board[x][y] != 0) {
            dfs(cnt + 1);
        } else {
            for (int i = 1; i <= N; i++) {
                if (!isValid(x, y, i)) {
                    continue;
                }

                board[x][y] = i;
                dfs(cnt + 1);

                if (isEnd) {
                    return;
                }
                board[x][y] = 0;
            }
        }
    }

    private static boolean isValid(int x, int y, int target) {
        // 가로, 세로
        for (int i = 0; i < N; i++) {
            if (board[x][i] == target || board[i][y] == target) {
                return false;
            }
        }

        // 3x3
        int sx = x / 3 * 3, sy = y / 3 * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[sx + i][sy + j] == target) {
                    return false;
                }
            }
        }

        return true;
    }
}
