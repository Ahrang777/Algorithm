import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] dp;
    static final int DIV = 10007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i][0] = dp[i][i] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                dp[i][j] = (dp[i - 1][j - 1] % DIV + dp[i - 1][j] % DIV) % DIV;
            }
        }

        System.out.println(dp[N][K]);
    }
}
