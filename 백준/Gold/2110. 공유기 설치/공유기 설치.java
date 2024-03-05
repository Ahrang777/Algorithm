import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, C;
    static int[] homes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        homes = new int[N];

        for (int i = 0; i < N; i++) {
            homes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(homes);

        int result = Integer.MIN_VALUE;

        // 두 공유기 사이 가장 인접한 거리
        int start = 0, end = homes[N - 1] - homes[0];
        while (start <= end) {
            int mid = (start + end) / 2;
            int prev = 0;
            int cnt = 1;

            for (int i = 1; i < N; i++) {
                if (homes[i] - homes[prev] >= mid) {
                    prev = i;
                    cnt++;
                }
            }

            if (cnt >= C) { // 가장 인접한 거리 증가해도 된다.
                result = Math.max(mid, result);
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(result);
    }
}