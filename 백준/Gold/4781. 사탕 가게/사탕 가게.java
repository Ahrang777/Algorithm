import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dp;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            double m = Double.parseDouble(st.nextToken());

            if (N == 0 && m == 0.00) break;

            M = (int) (m * 100 + 0.05);

            // i번째 사탕 / 0: 칼로리, 1: 가격
            arr = new int[N + 1][2];
            dp = new int[M + 1];    // 금액 i 이하의 최대 칼로리합

            for (int i = 1; i <= N; i++) {
                /*
                87% 오답
                rounding error(부동 소수점 반올림 오차)
                Java에서 실수 데이터를 다루다 보면 계산에 오차가 발생하는 경우가 있다.
                int를 통한 자동 라운딩을 하면 0.11이 11이 나와야 하지만 10이 나오는 경우이다.
                실수를 다룰 때는 rounding error를 주의해야 하며 0.5를 더하는 것으로 해결할 수 있다.

                0,5를 도허눈 곳운 아래 값을 반올림하는 가장 간단한 기법이기 때문입니다.
                원래 수가 0.5 미만일 때 (예: 2.3) → 0.5를 더하면 2.8이 되고, 정수 변환 시 2로 반올림됩니다.
                원래 수가 0.5 이상일 때 (예: 2.7) → 0.5를 더하면 3.2가 되고, 정수 변환 시 3으로 반올림됩니다.
                더 정확한 방법으로는 BigDecimal을 사용하는 방법이 있습니다.
                 */
                st = new StringTokenizer(br.readLine());

                int C = Integer.parseInt(st.nextToken());
                int P = (int) (100 * Double.parseDouble(st.nextToken()) + 0.05);

                arr[i][0] = C;
                arr[i][1] = P;
            }

            for (int i = 1; i <= N; i++) {
                int cal = arr[i][0];
                int price = arr[i][1];

                for (int j = price; j <= M; j++) {
                    dp[j] = Math.max(dp[j], dp[j - price] + cal);
                }
            }

            sb.append(dp[M]).append("\n");
        }

        System.out.println(sb);
    }
}