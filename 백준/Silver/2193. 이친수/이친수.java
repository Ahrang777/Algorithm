import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 0: 짝, 1: 홀
        // dp[i][j] : i자리 이천수들의 짝수, 홀수 개수
        long[][] dp = new long[N + 1][2];

        dp[1][0] = 0;
        dp[1][1] = 1;

        // 0으로 끝나는 이진수는 짝수, 1로 끝나는 이진수는 홀수
        // 0으로 끝나면 다음은 0, 1이 붙을 수 있다. 
        // 1로 끝나면 다음은 0만 뭍을 수 있다. 
        // 짝수 -> 짝수, 홀수 / 홀수 -> 짝수
        // 즉 i자리수의 이친수 중 짝수의 개수는 i -1의 홀수 + 짝수 개수
        // i자리수의 이친수 중 홀수의 개수는 i - 1의 짝수 개수
        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            dp[i][1] = dp[i - 1][0];
        }

        System.out.println(dp[N][0] + dp[N][1]);
    }
}