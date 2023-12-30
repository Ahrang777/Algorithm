import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static long M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Long.parseLong(st.nextToken());

            long[] candy = new long[N];
            long max = Long.MIN_VALUE;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                candy[i] = Long.parseLong(st.nextToken());
                max = Math.max(max, candy[i]);
            }

            // 이런 start, end의 초기값을 주의하자
            // 가방의 개수 시작, 종료 범위
            long start = 1;
            long end = max; // 범위를 10 ^ 18으로 해도되는데 최적화를 하는것. 제일 많은 사탕의 개수보다는 가방을 더 많이 만들 수 없다.
            long result = 0;
            while (start <= end) {
                long mid = (start + end) / 2;
                long total = 0L;

                // total = 모든 사탕을 가방 개수로 나눈(모든 가방의 구성이 같아야 하므로) 합
                // total은 각 사탕의 경우 모든 가방에 사탕을 분배한 경우의 가방에 들어갈 사탕 개수의 합
                // total = 각 가방에 들어갈 수 있는 사탕의 개수이다. 따라서 total >= M이면 조건을 만족해서 분배할 수 있는 가방의 개수이다.
                for (int i = 0; i < N; i++) {
                    total += (candy[i] / mid);
                }

                if (total >= M) {
                    start = mid + 1;
//                    result = Math.max(result, mid);
                    result = mid;
                } else {
                    end = mid - 1;
                }
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }
}