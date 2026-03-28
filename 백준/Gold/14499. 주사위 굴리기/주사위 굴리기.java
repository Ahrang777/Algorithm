import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X, Y, K;
    static int[][] map;
    static int[] dice = new int[6];
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int c = Integer.parseInt(st.nextToken()) - 1;

            int nx = X + dx[c], ny = Y + dy[c];

            if (!isRange(nx, ny)) {
                continue;
            }

            move(c);
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static void move(int c) {
        int nx = X + dx[c], ny = Y + dy[c];

        if (c == 0) {   // 동
            int tmp = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[2];
            dice[2] = tmp;
        } else if (c == 1) {    // 서
            int tmp = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[5];
            dice[5] = dice[3];
            dice[3] = tmp;
        } else if (c == 2) {    // 북
            int tmp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[5];
            dice[5] = dice[1];
            dice[1] = tmp;
        } else {    // 남
            int tmp = dice[0];
            dice[0] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[4];
            dice[4] = tmp;
        }

        if (map[nx][ny] == 0) {
            map[nx][ny] = dice[5];
        } else {
            dice[5] = map[nx][ny];
            map[nx][ny] = 0;
        }

        X = nx;
        Y = ny;

        System.out.println(dice[0]);
    }
}
