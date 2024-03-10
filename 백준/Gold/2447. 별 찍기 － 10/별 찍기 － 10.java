import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        printStar(N, 0, 0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != '*') {
                    sb.append(' ');
                    continue;
                }
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    /**
     *
     * @param len 길이
     * @param x 시작점 x좌표
     * @param y 시작점 y좌표
     */
    private static void printStar(int len, int x, int y) {
        if (len == 1) {
            map[x][y] = '*';
            return;
        }

        int step = len / 3;
        for (int i = 0; i < len; i += step) {
            for (int j = 0; j < len; j += step) {
                if (i == step && j == step) {
                    continue;
                }

                printStar(step, x + i, y + j);
            }
        }
    }
}