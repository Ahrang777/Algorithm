import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1];  // i번째 포도주까지 고려했을 때 최대로 마실 수 있는 포도주 양

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = arr[1];

        if (N >= 2) {
            dp[2] = arr[1] + arr[2];

            // 1. 현재 잔 마시는 경우
            // 1-1. 하나 건너뛰고 마시기 o, x, o
            // 1-2. 한 번 쉬고 두잔 연속 마시기 o, x, o, o
            // 2. 현재 잔 안 마시는 경우
            for (int i = 3; i <= N; i++) {
                dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]));
            }
        }

        System.out.println(dp[N]);
    }
}