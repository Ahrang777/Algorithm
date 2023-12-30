import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static long N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");

            N = Long.parseLong(br.readLine());
            long start = 1; // 정답이 존재하는 가능성이 있는 왼쪽 끝

            // K (K + 1) / 2 <= N을 생각했을 때 N은 최대 10 ^ 18
            // K (K + 1) >> K ^ 2 == 2 * N >> 최대 2 * 10 ^ 18
            // K = 2 * 10 ^ 9 까지 고려하는게 안전
            long end = 10000000000L;    // 정답이 존재하는 가능성이 있는 오른쪽 끝
            long result = 0;

            while (start <= end) {
                long mid = (start + end) / 2;

                if ((mid * (mid + 1) / 2) <= N) {
                    result = Math.max(result, mid);
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            sb.append((result * (result + 1) / 2) == N ? result : -1).append("\n");
        }

        System.out.print(sb);
    }
}