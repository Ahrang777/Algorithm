import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] memories, costs, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memories = new int[N];
        costs = new int[N];

        // 앱 최대 100개 * 비용 최대 100 = 10000 까지는 입력이 가능해야 한다.
        // 해당 비용으로 얻을 수 있는 최대 메모리
        dp = new int[10001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp, -1);

        for (int i = 0; i < N; i++) {
            int cost = costs[i];

            for (int j = 10000; j >= cost; j--) {
                if (dp[j - cost] != -1) {
                    dp[j] = Math.max(dp[j], dp[j - cost] + memories[i]);
                }
            }

            dp[cost] = Math.max(dp[cost], memories[i]);
        }

        for (int i = 0; i < 10001; i++) {
            if (dp[i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}