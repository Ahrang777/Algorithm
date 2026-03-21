import java.io.*;
import java.util.*;

public class Main {
    static int N, max;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                int key = map[i][j];

                max = Math.max(max, countMax(i, j));
            }
        }

        if (max != N) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    //열교환
                    int nx = i, ny = j + 1;
                    if (isRange(nx, ny)) {
                        swap(i, j, nx, ny);
                        max = Math.max(max, Math.max(countMax(i, j), countMax(nx, ny)));
                        swap(i, j, nx, ny);
                    }

                    //행교환
                    nx = i + 1; ny = j;
                    if (isRange(nx, ny)) {
                        swap(i, j, nx, ny);
                        max = Math.max(max, Math.max(countMax(i, j), countMax(nx, ny)));
                        swap(i, j, nx, ny);
                    }
                }
            }


        }

        System.out.println(max);
    }

    private static void swap(int x, int y, int nx, int ny) {
        char tmp = map[x][y];
        map[x][y] = map[nx][ny];
        map[nx][ny] = tmp;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static int countMax(int x, int y) {
        int rowCnt = 1, colCnt = 1, key = map[x][y];
        //행확인
        for (int k = y + 1; k < N; k++) {
            if (map[x][k] != key) {
                break;
            }

            rowCnt++;
        }

        for (int k = y - 1; k >= 0; k--) {
            if (map[x][k] != key) {
                break;
            }

            rowCnt++;
        }

        //열확인
        for (int k = x + 1; k < N; k++) {
            if (map[k][y] != key) {
                break;
            }

            colCnt++;
        }

        for (int k = x - 1; k >= 0; k--) {
            if (map[k][y] != key) {
                break;
            }

            colCnt++;
        }

        return Math.max(rowCnt, colCnt);
    }
}
