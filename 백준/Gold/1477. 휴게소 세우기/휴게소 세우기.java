import java.io.*;
import java.util.*;

public class Main {
    static int N, M, L;
    static int[] arr, subArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N + 2];

        if (N > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
        }

        arr[0] = 0;
        arr[N + 1] = L;

        Arrays.sort(arr);

        int s = 1;
        int e = L - 1;
        int answer = L - 1;

        while (s <= e) {
            // 휴게소가 없는 구간의 최댓값
            int mid = (s + e) / 2;

            // 휴게소 없는 구간의 최댓값이 mid일 경우 추가 설치 가능한 휴게소의 수
            int cnt = 0;

            for (int i = 1; i < arr.length; i++) {
                cnt += (arr[i] - arr[i - 1] - 1) / mid;
            }

            if (cnt > M) {
                s = mid + 1;
            } else {
                e = mid - 1;
                answer = mid;
            }
        }

        System.out.println(answer);
    }


}