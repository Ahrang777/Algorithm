import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, times[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int result = 0;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        times = new int[M + 1];

        long max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            times[i] = Integer.parseInt(st.nextToken());
            max = Math.max(times[i], max);
        }

        if (N <= M) {
            result = N;
        } else {
            long minTime = binarySearch(0, N / M * max);
            long cnt = M;   // 0초일때 초기 M명 채워지므로

            // 마지막 다 채워지는 시간 이전까지 반영
            for (int i = 1; i <= M; i++) {
                cnt += (minTime - 1) / times[i];
            }

            for (int i = 1; i <= M; i++) {
                if (minTime % times[i] == 0) {
                    cnt++;
                }

                if (cnt == N) {
                    result = i;
                    break;
                }
            }
        }

        System.out.println(result);
    }

    private static long binarySearch(long left, long right) {
        while (left <= right) {
            long mid = (left + right) / 2;

            // 기본적으로 0분에 M명이 채워지므로
            long sum = M;   // 기구를 탄 사람수

            for (int i = 1; i <= M; i++) {
                sum += (mid / times[i]);
            }

            if (sum < N) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}