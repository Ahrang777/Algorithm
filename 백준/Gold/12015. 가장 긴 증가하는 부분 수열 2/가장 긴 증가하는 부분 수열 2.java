import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dp = new int[N + 1];    // dp[i] : 길이 i인 가장 긴 증가하는 부분수열의 원소 중 최댓값

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int len = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] > dp[len]) {
                len++;
                dp[len] = arr[i];
                continue;
            }

            // lowerBound를 이용해서 index를 찾아야 한다. 
            // 1, 2, 6, 2, 6, 4, 5
            // upperBound로 index를 찾는 경우 
            // 1, 2, 6 -> 1, 2, 2 -> 1, 2, 2, 6 이 되며 이상해진다.
            int index = lowerBound(arr[i], 0, len);
            dp[index] = arr[i];
        }

        System.out.println(len);
    }

    private static int lowerBound(int key, int left, int right) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (key <= dp[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}