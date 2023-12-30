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
            long start = 1;
            long end = max;
            long result = 0;
            while (start <= end) {
                long mid = (start + end) / 2;
                long total = 0L;

                for (int i = 0; i < N; i++) {
                    total += (candy[i] / mid);
                }

                if (total >= M) {
                    start = mid + 1;
                    result = Math.max(result, mid);
                } else {
                    end = mid - 1;
                }
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }
}