import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, max;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        max = -1;

        // 시작점
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                checkSquareNumber(i, j);
            }
        }

        System.out.println(max);
    }

    private static void checkSquareNumber(int x, int y) {
        for (int i = -N; i < N; i++) {  // x의 증가 크기
            for (int j = -M; j < M; j++) {  // y의 증가 크기

                // 크기 변화가 없으므로 제외
                if (i == 0 && j == 0) {
                    continue;
                }

                // 0 ~ N , 0 ~ M 범위 내에 있는지
                // 해당 x, y의 크기변화로 끝까지 적용
                int nx = x;
                int ny = y;
                int result = 0;
                while (isRange(nx, ny)) {
                    result = result * 10 + map[nx][ny];
                    nx += i;
                    ny += j;

                    if (isSquare(result)) {
                        max = Math.max(max, result);
                    }
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static boolean isSquare(int num) {
        int sqrt = (int) Math.sqrt(num);

        return num == sqrt * sqrt;
    }
}