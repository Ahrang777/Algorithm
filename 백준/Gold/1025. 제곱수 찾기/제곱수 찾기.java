import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = -1;

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                checkSqure(i, j);
            }
        }

        System.out.println(result);
    }

    private static void checkSqure(int x, int y) {
        // x, y에서 왼쪽, 오른쪽으로 가능한 끝까지 변경해가며 완전제곱수인지 확인
        for (int i = -N; i < N; i++) {
            for (int j = -M; j < M; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int nx = x;
                int ny = y;
                int total = 0;

                while (isRange(nx, ny)) {
                    total = total * 10 + arr[nx][ny];
                    nx += i;
                    ny += j;

                    if (isSqure(total)) {
                        result = Math.max(result, total);
                    }
                }
            }
        }

    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static boolean isSqure(int num) {
        int sqrt = (int) Math.sqrt(num);

        return sqrt * sqrt == num;
    }
}