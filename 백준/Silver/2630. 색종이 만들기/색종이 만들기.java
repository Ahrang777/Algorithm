import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, whiteCnt, blueCnt, map[][];
    public static void main(String[] args) throws Exception {
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

    private static void cut(int x, int y, int len) {
        int standard = map[x][y];
        boolean flag = false;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (map[x + i][y + j] != standard) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }

        if (flag) {
            cut(x, y, len / 2);
            cut(x + len / 2, y, len / 2);
            cut(x, y + len / 2, len / 2);
            cut(x + len / 2, y + len / 2, len / 2);
        } else {
            if (standard == 0) {
                whiteCnt++;
            } else {
                blueCnt++;
            }
        }

//        int white = 0, blue = 0;
//        for (int i = 0; i < len; i++) {
//            for (int j = 0; j < len; j++) {
//                if (map[x + i][y + j] == 0) {
//                    white++;
//                } else {
//                    blue++;
//                }
//            }
//        }
//
//        if (white == len * len) {
//            whiteCnt++;
//            return;
//        }
//
//        if (blue == len * len) {
//            blueCnt++;
//            return;
//        }
//
//        cut(x, y, len / 2);
//        cut(x + len / 2, y, len / 2);
//        cut(x, y + len / 2, len / 2);
//        cut(x + len / 2, y + len / 2, len / 2);
    }

}