import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][2];    // i번 물건의 무게 W, 가치 V
        int[] dp = new int[K + 1];  // 무게 i까지 최대 가치

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            int w = arr[i][0];
            int v = arr[i][1];

            for (int j = K; j >= w; j--) {
                // 배낭에 i번째 물건을 담을 수 있는 경우는 이전 최대 가치를 그대로 갖고있으면 된다.
                // 2차원 배열로 생각했을 때 dp[i][j] = dp[i-1][j]
                // 배낭에 i번째 물건을 담을 수 있는 경우
                dp[j] = Math.max(dp[j], dp[j - w] + v);
            }
        }

        System.out.println(dp[K]);
    }
}