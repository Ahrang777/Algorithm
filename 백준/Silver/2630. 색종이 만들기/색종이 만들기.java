import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, whiteCnt, blueCnt;
    static int[][] map;
    static final int WHITE = 0;
    static final int BLUE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cut(0, 0, N);

        sb.append(whiteCnt).append("\n").append(blueCnt);

        System.out.println(sb);
    }

    /**
     *
     * @param x 시작 x좌표
     * @param y 시작 y좌표
     * @param len 범위내 길이
     */
    private static void cut(int x, int y, int len) {
        if (len == 1) {
            if (map[x][y] == WHITE) {
                whiteCnt++;
            } else {
                blueCnt++;
            }
            return;
        }

        // 범위내 모든 색이 같은지 체크
        int start = map[x][y];
        boolean isDiff = false;   // 첫번째 색과 다른지 여부

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (map[x + i][y + j] != start) {
                    isDiff = true;
                    break;
                }
            }

            if (isDiff) {
                break;
            }
        }

        if (!isDiff) {
            if (start == WHITE) {
                whiteCnt++;
            } else {
                blueCnt++;
            }

            return;
        }

        cut(x, y, len / 2);
        cut(x + len / 2, y, len / 2);
        cut(x, y + len / 2, len / 2);
        cut(x + len / 2, y + len / 2, len / 2);
    }
}