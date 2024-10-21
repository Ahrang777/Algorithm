import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] memories, costs;
    static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memories = new int[N + 1];
        costs = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[M + 1];
        Arrays.fill(dp, INF);

        for (int i = 1; i <= N; i++) {
            for (int j = M; j > 0; j--) {
                if (j <= memories[i]) {
                    dp[j] = Math.min(dp[j], costs[i]);
                } else {
                    dp[j] = Math.min(dp[j], dp[j - memories[i]] + costs[i]);
                }
            }
        }

        System.out.println(dp[M]);
    }
}