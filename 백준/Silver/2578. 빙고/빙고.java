import java.io.*;
import java.util.*;

public class Main {
    static int N = 5;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int cnt = 0;
        boolean isBingo = false;
        boolean[][] board = new boolean[N][N];
        Map<Integer, int[]> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());

                map.put(num, new int[]{i, j});
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int key = Integer.parseInt(st.nextToken());

                int[] now = map.get(key);
                int x = now[0], y = now[1];

                board[x][y] = true;
                cnt++;

                if (isBingo(board)) {
                    isBingo = true;
                    break;
                }
            }

            if (isBingo) {
                break;
            }
        }

        System.out.println(cnt);
    }

    private static boolean isBingo(boolean[][] board) {
        int cnt = 0;
        boolean isBingo = true;

        for (int i = 0; i < N; i++) {

            isBingo = true;

            //가로
            for (int j = 0; j < N; j++) {
                if (!board[i][j]) {
                    isBingo = false;
                    break;
                }
            }

            if (isBingo) {
                cnt++;
            }

            isBingo = true;

            //세로
            for (int j = 0; j < N; j++) {
                if (!board[j][i]) {
                    isBingo = false;
                    break;
                }
            }

            if (isBingo) {
                cnt++;
            }
        }

        // /대각선
        isBingo = true;
        for (int i = 0; i < N; i++) {
            if (!board[i][i]) {
                isBingo = false;
                break;
            }
        }

        if (isBingo) {
            cnt++;
        }

        // \대각선
        isBingo = true;
        for (int i = 0; i < N; i++) {
            if (!board[N - 1 - i][i]) {
                isBingo = false;
                break;
            }
        }

        if (isBingo) {
            cnt++;
        }

        return cnt >= 3;
    }
}
