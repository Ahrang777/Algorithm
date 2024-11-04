import java.io.*;
import java.util.*;

public class Main {
    static int answer = Integer.MAX_VALUE;
    static int[][] map;
    static int[] paper = {0, 5, 5, 5, 5, 5};

    static final int N = 10;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int size = 0;
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    size++;
                }
            }
        }

        if (size == 0) {
            System.out.println(0);
            return;
        } else if (size == N * N) {
            System.out.println(4);
            return;
        }

        dfs(0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }


    private static void dfs(int cnt, int index) {
        if (index >= 100) {
            answer = Math.min(answer, cnt);
            return;
        }

        if (cnt >= answer) {
            return;
        }

        int x = index / N, y = index % N;

        if (map[x][y] == 1) {
            // 색종이 길이
            for (int len = 5; len >= 1; len--) {
                // 색종이 붙일 수 있는 경우
                if (paper[len] > 0 && isAttach(x, y, len)) {
                    // 색종이 붙이기
                    attach(x, y, len, 0);
                    paper[len]--;

                    dfs(cnt + 1, index + 1);

                    // 색종이 제거
                    paper[len]++;
                    attach(x, y, len, 1);
                }
            }
        } else {
            dfs(cnt, index + 1);
        }
    }

    private static void attach(int sx, int sy, int len, int state) {
        for (int x = sx; x < sx + len; x++) {
            for (int y = sy; y < sy + len; y++) {
                map[x][y] = state;
            }
        }
    }

    // 색종이 붙일 수 있는지 확인
    private static boolean isAttach(int sx, int sy, int len) {
        // 색종이가 범위 초과
        if (!isRange(sx + len - 1, sy + len - 1)) {
            return false;
        }

        for (int x = sx; x < sx + len; x++) {
            for (int y = sy; y < sy + len; y++) {
                if (map[x][y] != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}