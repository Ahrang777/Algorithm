import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, dp[];
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
    }
}