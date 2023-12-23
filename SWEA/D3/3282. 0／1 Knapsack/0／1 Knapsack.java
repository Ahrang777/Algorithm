import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static class Thing {
        int v, c;   // 부피, 가치

        public Thing(int v, int c) {
            this.v = v;
            this.c = c;
        }
    }

    static int N, K;
    static Thing[] things;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            things = new Thing[N + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                things[i] = new Thing(v, c);
            }

            int[] dp = new int[K + 1];

            // 물건 i까지 고려
            for (int i = 1; i <= N; i++) {
                // 각 잔여 부피별 고려
                for (int j = K; j > 0; j--) {
                    // 물건 담을 수 없는 경우
                    if (things[i].v > j) {
                        continue;
                    }

                    // 물건 담을 수 있는 경우 >> 담을지 말지 선택
                    if (things[i].v <= j) {
                        dp[j] = Math.max(dp[j], things[i].c + dp[j - things[i].v]);
                    }
                }
            }

            sb.append(dp[K]).append("\n");
        }

        System.out.println(sb);
    }
}