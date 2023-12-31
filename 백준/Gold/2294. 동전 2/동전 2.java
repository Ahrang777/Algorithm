import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static int[] coin, dp;
    static final int INF = 10000001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[K + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        coin = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            coin[i] = Integer.parseInt(br.readLine());
            if(coin[i] <= K) {
                dp[coin[i]] = 1;
            }
        }

        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (i - coin[j] > 0 && dp[i] > dp[i - coin[j]] + 1) {
                    dp[i] = dp[i - coin[j]] + 1;
                }
            }
        }
        System.out.println(dp[K] == INF ? -1 : dp[K]);
    }

    /*static int N, K, dp[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[K + 1];

        Set<Integer> positions = new HashSet<>();

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x <= K) {
                dp[x] = 1;
                positions.add(x);
            }
        }

        bfs(positions);
        System.out.println(dp[K] == 0 ? -1 : dp[K]);
    }

    private static void bfs(Set<Integer> positions) {
        Queue<Integer> q = new ArrayDeque<>();

        // dp 값이 0이 아니면 이미 방문한것
        for (int position : positions) {
            q.offer(position);
        }

        while (!q.isEmpty()) {
            Integer now = q.poll();

            if (now == K) {
                return;
            }

            for (int position : positions) {
                // 다음 dp 위치
                int next = now + position;

                // 범위 밖이거나 이미 방문한 경우
                if (next > K || dp[next] != 0) {
                    continue;
                }

                dp[next] = dp[now] + 1;
                q.offer(next);
            }
        }
    }*/
}