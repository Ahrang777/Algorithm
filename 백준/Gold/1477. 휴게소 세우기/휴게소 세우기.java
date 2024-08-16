import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 2];
        arr[0] = 0;
        arr[1] = L;
        st = new StringTokenizer(br.readLine());
        for (int i = 2; i < N + 2; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        System.out.println(lowerBound(1, L, arr, M));
    }

    static int lowerBound(int left, int right, int[] arr, int M) {
        while (left < right) {
            int mid = left + (right - left) / 2;

            int count = getCount(mid, arr);
            // count 더 늘려야 -> 거리 mid 줄여 -> M과 더 근접
            // count 같아도  -> 거리 mid 줄여 (더 적은 mid 찾아야)
            if (count <= M) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    static int getCount(int val, int[] arr) {
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            int diff = arr[i] - arr[i - 1];
            count += diff / val;
            if (diff % val == 0) count--;
        }
        return count;
    }
    
    /*static int N, M, L;
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
            // 현재 구간에서 휴게소가 없는 구간의 값
            int mid = (s + e) / 2;

            // 휴게소 없는 구간의 값이 mid일 경우 추가 설치 가능한 휴게소의 수
            int cnt = 0;

            for (int i = 1; i < arr.length; i++) {
                cnt += (arr[i] - arr[i - 1] - 1) / mid;
            }

            if (cnt > M) {
                s = mid + 1;
            } else {
                // M개 휴게소 세울수 있는지 여부 아래 경우가 여기에 해당한다.
                // F, F, F, F, T

                // 현재 M값과 동일하게 휴게소를 세우거나 휴게소가 더 적게 세워지는 건 현재 mid가 휴게소 없는 구간의 최댓값이라는 의미
                // cnt < M인 경우도 괜찮은 이유는
                // 현재는 최댓값인 mid와 동일하게 나눈다고 가정하여 M보다 작은거지
                // 하나만 mid로 나누고 나머지는 더 작은 값으로 나우어 휴게소를 M개 세울 수 있다.
                // 구간 길이 : 1, 1, 1, mid 이런식으로 (mid > 1)
                e = mid - 1;
                answer = mid;
            }
        }

        System.out.println(answer);
    }*/
}