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

            int[] dp = new int[N + 1];

            if (N == 1) {
                sb.append(1).append("\n");
                continue;
            }
            dp[1] = 1;

            if (N == 2) {
                sb.append(2).append("\n");
                continue;
            }

            if (N == 3) {
                sb.append(4).append("\n");
                continue;
            }
            dp[2] = 2;
            dp[3] = 4;

            // 1 >> + 1, + 2, + 3
            for (int i = 4; i <= N; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }

            sb.append(dp[N]).append("\n");
        }

        System.out.println(sb);
    }
}