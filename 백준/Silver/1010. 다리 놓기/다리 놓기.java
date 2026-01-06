import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] dp = new int[M + 1][N + 1];

            for (int i = 0; i <= M; i++) {
                for (int j = 0, end = Math.min(i, N); j <= end; j++) {
                    if (j == 0 || i == j)	dp[i][j] = 1;
                    else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }

            sb.append(dp[M][N]).append("\n");
        }

        System.out.println(sb);
    }
}
