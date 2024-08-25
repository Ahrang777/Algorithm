import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int sqrt = (int) Math.sqrt(N);
        int[] dp = new int[N + 1];

        for (int i = 1; i <= sqrt; i++) {
            dp[i * i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            if (dp[i] != 0) {
                continue;
            }

            sqrt = (int) Math.sqrt(i);
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= sqrt; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        System.out.println(dp[N]);
    }
}