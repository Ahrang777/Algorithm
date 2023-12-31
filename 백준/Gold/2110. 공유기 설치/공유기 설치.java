import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, C, max;
    static int[] homes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        max = Integer.MIN_VALUE;

        homes = new int[N];

        for (int i = 0; i < N; i++) {
            homes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(homes);

        int start = 1;
        int end = homes[N - 1] - homes[0];
        int result = 0;

        while (start <= end) {
            // 두 공유기 사이 거리의 최솟값
            int mid = (start + end) / 2;

            int cnt = 1;
            int prev = 0;

            for (int i = 1; i < N; i++) {
                if (homes[i] - homes[prev] >= mid) {
                    cnt++;
                    prev = i;
                }
            }

            if (cnt >= C) {
                start = mid + 1;
                result = Math.max(result, mid);
            } else {
                end = mid - 1;
            }
        }

        System.out.println(result);
    }
}