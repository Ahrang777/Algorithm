import java.io.*;
import java.util.*;

public class Main {
    static int M;
    static long N;
    static int[] times;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        times = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        if (N <= M) {
            System.out.println(N);
            return;
        }

        // N이 최대 20억으로 시간초과
        // 순서대로 자리 비워지면 바로 탑승하니까 N명이 모두 탑승 가능한 최소시간 찾기
        // 이분탑색으로 시간복잡도 줄이기 O(logN)

        // N명의 아이들이 모두 탑승 가능한 최소시간
        long minTime = binarySearch(0, N * 30);

        // minTime - 1분까지 탑승한 아이들의 수를 구한 후 minTime분에 추가로 탑승한 아이들의 기구 번호를 확인하며 N명이 되는 순간 종료

        // minTime - 1분까지 탑승한 아이들의 수 구하기
        long childCnt = getChildInTime(minTime - 1);

        for (int i = 0; i < M; i++) {
            // minTime분에 탑승한 아이
            if (minTime % times[i] == 0) {
                childCnt++;
            }

            // 마지막 순서의 아이까지 모두 탑승한 경우 종료
            if (childCnt == N) {
                System.out.println(i + 1);
                break;
            }
        }
    }

    private static long binarySearch(long left, long right) {
        long result = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            // 주어진 시간동안 탑승 가능한 아이들의 수
            long childCnt = getChildInTime(mid);

            if (childCnt >= N) {
                result = Math.min(mid, result);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    // 주어진 시간 내에 탑승 가능한 아이들의 수
    private static long getChildInTime(long time) {
        // 0분에 M개의 놀이기구마다 1명씩 탑승해서 총 M명 탑승
        long total = M;

        // 각 놀이기구는 1분부터 time분까지
        // 총 시간 / 탑승시간 만큼의 아이들이 탑승한다.
        for (int i = 0; i < M; i++) {
            total += time / times[i];
        }

        return total;
    }
}