import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int A, K;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        A = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        /*
        dp = new int[K + 1];

        for (int i = A + 1; i <= K; i++) {
            dp[i] = dp[i - 1] + 1;

            if (i % 2 == 0 && i / 2 >= A) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
        }

        System.out.println(dp[K]);

        */

        int cnt = 0;

        // K -> A로 만들면서 연산횟수를 계산한다.
        while (K != A) {
            if (K % 2 == 0 && K / 2 >= A) {
                K /= 2;
            } else {
                K--;
            }

            cnt++;
        }

        System.out.println(cnt);
    }
}
