import java.io.*;
import java.util.*;

public class Main {
    static int T, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            int[][] dp = new int[N + 1][2];

            if (N == 0) {
                sb.append("1 0").append("\n");
                continue;
            }

            if (N == 1) {
                sb.append("0 1").append("\n");
                continue;
            }

            dp[0][0] = 1;
            dp[0][1] = 0;
            dp[1][0] = 0;
            dp[1][1] = 1;

            for (int i = 2; i <= N; i++) {
                for (int j = 0; j <= 1; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 2][j];
                }
            }

            sb.append(dp[N][0]).append(" ").append(dp[N][1]).append("\n");
        }

        System.out.println(sb);
    }
}