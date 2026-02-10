import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] map;
    static int N;

    static List<int[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean isValid = true;
        N = 6;
        map = new boolean[N][N];

        list = new ArrayList<>();

        for (int i = 0; i < 36; i++) {
            String str = br.readLine();
            int x = str.charAt(0) - 'A', y = str.charAt(1) - '1';
            list.add(new int[]{x, y});
        }

        for (int i = 0; i < list.size() - 1; i++) {
            int x = list.get(i)[0];
            int y = list.get(i)[1];
            int nx = list.get(i + 1)[0];
            int ny = list.get(i + 1)[1];

            if (map[x][y] || !isNext(x, y, nx, ny)) {
                isValid = false;
                break;
            }
            map[x][y] = true;
        }

        // 마지막점 이동
        if (isValid) {
            int ex = list.get(35)[0], ey = list.get(35)[1];

            if (map[ex][ey]) {
                isValid = false;
            }

            map[ex][ey] = true;
        }

        if (isValid) {
            // 시작점으로 이동 가능한지
            int sx = list.get(0)[0], sy = list.get(0)[1], ex = list.get(35)[0], ey = list.get(35)[1];

            isValid = isNext(sx, sy, ex, ey);
        }

        System.out.println(isValid ? "Valid" : "Invalid");
    }

    private static boolean isNext(int x, int y, int nx, int ny) {
        int dx = Math.abs(nx - x);
        int dy = Math.abs(ny - y);

        return dx * dy == 2;
    }
}
