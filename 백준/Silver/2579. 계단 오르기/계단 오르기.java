import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        if (N == 1) {
            dp[1] = arr[1];
        } else if (N == 2) {
            dp[2] = arr[2] + arr[1];
        } else {
            dp[1] = arr[1];
            dp[2] = arr[2] + arr[1];
            dp[3] = Math.max(arr[1] + arr[3], arr[2] + arr[3]);

            for (int i = 4; i <= N; i++) {
                // 1. 두 계단 + 한 계단 오른 경우 
                //  >> 최근에 한 계단으로 오른 경우는 연속해서 3계단 오를 수 없으니 두 계단 오르고 한 계단 오르는 경우밖에 없다. 
                // 2. 한 번에 두 계단 오른 경우 
                //  >> (한 계단 + 두 계단) or (두 계단 + 두 계단)의 경우 2번인 한 번에 두 계단 오른 경우에 속한다.
                dp[i] = Math.max(dp[i - 3] + arr[i - 1] + arr[i], dp[i - 2] + arr[i]);
            }
        }

        System.out.println(dp[N]);
    }
}