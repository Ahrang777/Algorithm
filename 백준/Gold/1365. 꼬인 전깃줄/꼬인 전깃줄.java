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
        dp = new int[N + 1];    // i 길이를 갖는 증가하는 부분수열의 최대값

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int len = 0;
        int index = 0;

        for (int i = 0; i < N; i++) {
            if (arr[i] > dp[len]) {
                len++;
                dp[len] = arr[i];
                continue;
            }

            index = binarySearch(0, len, arr[i]);
            dp[index] = arr[i];
        }

        System.out.println(N - len);
    }

    private static int binarySearch(int left, int right, int key) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (dp[mid] > key) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}