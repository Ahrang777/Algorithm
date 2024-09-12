import java.io.*;

public class Main {
    private static final int MOD = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int N = str.length();
        int[] dp = new int[N + 1];

        dp[0] = 1;
        dp[1] = 1;

        if (str.charAt(0) == '0') {
            System.out.println(0);
            return;
        }

        for (int i = 2; i <= N; i++) {
            int now = str.charAt(i - 1) - '0';
            int prev = str.charAt(i - 2) - '0';
            int value = prev * 10 + now;

            if (now >= 1 && now <= 9) {
                dp[i] += dp[i - 1] % MOD;
            }

            if (value >= 10 && value <= 26) {
                dp[i] += dp[i - 2] % MOD;
            }
        }

        System.out.println(dp[N] % MOD);
    }
}