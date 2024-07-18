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
            dp[1] = 1;

            // 1 >> + 1, + 2, + 3
            for (int i = 2; i <= N; i++) {

                // + 1
                dp[i] += dp[i - 1];

                // + 2
                if (i > 2) {
                    dp[i] += dp[i - 2];
                }

                // + 3
                if (i > 3) {
                    dp[i] += dp[i - 3];
                }

                // 2, 3인 경우
                if (i == 2 || i == 3) {
                    dp[i]++;
                }
            }

            sb.append(dp[N]).append("\n");
        }

        System.out.println(sb);
    }
}