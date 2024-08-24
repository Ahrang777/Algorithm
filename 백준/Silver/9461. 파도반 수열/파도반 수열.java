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

            if (N <= 3) {
                sb.append(1).append("\n");
            } else if (N <= 5) {
                sb.append(2).append("\n");
            } else {
                long[] dp = new long[N + 1];
                dp[1] = dp[2] = dp[3] = 1;
                dp[4] = dp[5] = 2;

                for (int i = 6; i <= N; i++) {
                    dp[i] = dp[i - 1] + dp[i - 5];
                }

                sb.append(dp[N]).append("\n");
            }
        }

        System.out.println(sb);
    }
}