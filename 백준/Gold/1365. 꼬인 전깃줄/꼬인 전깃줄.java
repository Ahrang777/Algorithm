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

        for (int i = 0; i < N; i++) {
            // 현재 값이 LIS 최대값 보다 큰 경우 갱신
            // arr[i]가 LIS를 연장가능
            if (arr[i] > dp[len]) {
                len++;
                dp[len] = arr[i];
                continue;
            }

            // arr[i]는 dp[0] < < dp[len] 사이에 들어가는 값이다.
            // arr[i]가 들어가야 할 위치를 찾고 갱신
            // lowerBound로 찾은 값에서 arr과 같은 dp가 없어 arr보다 큰 첫 번째 값의 index가 나온 경우
            // dp[index] = arr[i]하는 이유는
            // dp[i]에서 길이 i인 LIS 입장에서 dp[i]를 기존 dp[i]값으로 하나 arr[i]로 하나 길이는 동일하다.
            // 또한 arr[i] 는 dp[index - 1]보다 크기에 dp[index]에 넣어도 문제되지 않는다.
            // 오히려 dp에서 길이는 유지하면서 최대값이 더 작을수록 그 이후에 값들을 이어 붙일 수 있기에 
            // LIS를 구하기 위해서는 lowerBound 구한 arr[i]보다 크거나 같은 첫 번째 값의 index를 얻어 dp[index]를 갱신하는 것이 맞다.
            // 이 문제에서는 중복되는 arr값이 존재하지 않기 때문에 lowerBound, upperBound 모두 상관이 없다.
            // 하지만 이분탐색을 이용한 LIS 길이를 구할 때 중복되는 arr이 있다면 lowerBound로 해결해야 한다. 
            int index = lowerBound(0, len, arr[i]);          
            dp[index] = arr[i];
        }

        System.out.println(N - len);
    }

    private static int lowerBound(int left, int right, int key) {
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