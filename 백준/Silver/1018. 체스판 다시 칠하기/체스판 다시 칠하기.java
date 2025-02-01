import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result = Integer.MAX_VALUE;
    static boolean[][] map; // 검 : true, 흰 : false
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == 'B') {
                    map[i][j] = true;
                } else {
                    map[i][j] = false;
                }
            }
        }

        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                check(i, j);
            }
        }

        System.out.println(result);
    }

    private static void check(int x, int y) {
        int cnt = 0;
        int ex = x + 8;
        int ey = y + 8;

        boolean isTrueColor = map[x][y];

        for (int i = x; i < ex; i++) {
            for (int j = y; j < ey; j++) {
                if (map[i][j] != isTrueColor) {
                    cnt++;
                }

                isTrueColor = !isTrueColor;
            }
            isTrueColor = !isTrueColor;
        }

        // 시작점 색, 시작점과 반대되는 색으로 시작한 경우
        result = Math.min(result, Math.min(cnt, 64 - cnt));
    }
}