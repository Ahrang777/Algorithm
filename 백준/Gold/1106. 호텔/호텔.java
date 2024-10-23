import java.io.*;
import java.util.*;

public class Main {
    static int C, N;
    static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        // i : 도시 수 / j >> 0 : 비용, 1 : 고객 수
        int[][] cities = new int[N][2];

        // 최대 도시 수 20, 최대 비용 100 >> 2000
        // 비용이 i일 때 최대 고객 수
//        int[] dp = new int[100001];

        int[] dp = new int[C + 101];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            cities[i][0] = Integer.parseInt(st.nextToken());
            cities[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int cost = cities[i][0];
            int customer = cities[i][1];

            for (int j = customer; j < C + 101; j++) {
                dp[j] = Math.min(dp[j], dp[j - customer] + cost);
            }
        }

        int result = INF;
        for (int i = C; i < C + 101; i++) {
            result = Math.min(result, dp[i]);
        }

        System.out.println(result);
    }
}