import java.io.*;
import java.util.*;

public class Main {
    static int N, white, blue, map[][];
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

        cut(0, 0, N);

        System.out.println(white);
        System.out.println(blue);
    }

    private static void cut(int x, int y, int len) {
        //더이상 자를 수 없거나 색이 하나인 경우
        if (len == 1 || isOneColor(x, y, len)) {
            if (map[x][y] == 1) {
                blue++;
            } else {
                white++;
            }
            return;
        }

        //4등분
        cut(x, y, len / 2);
        cut(x + len / 2, y, len / 2);
        cut(x, y + len / 2, len / 2);
        cut(x + len / 2, y + len / 2, len / 2);
    }

    private static boolean isOneColor(int x, int y, int len) {
        for (int i = x; i < x + len; i++) {
            for (int j = y; j < y + len; j++) {
                if (map[i][j] != map[x][y])
                    return false;
            }
        }

        return true;
    }
}
