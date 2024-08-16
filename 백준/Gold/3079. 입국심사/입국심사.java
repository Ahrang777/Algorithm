import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        long[] times = new long[N];
        long max = 0;
        long min = Long.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            times[i] = Long.parseLong(br.readLine());
            max = Math.max(max, times[i]);
            min = Math.min(min, times[i]);
        }

        long s = min;
        long e = M * max;
        long answer = e;

        while (s <= e) {
            // 심사시간
            long mid = (s + e) / 2;

            // 심사시간이 mid일 경우 심사 가능한 최대 인원 수
            long cnt = 0;
            for (long time : times) {
                cnt += (mid / time);

                if (cnt >= M) {
                    break;
                }
            }

            if (cnt >= M) { // 심사시간이 커서 M명만 검사하면 되는데 M명 이상 검사할 수 있는 경우 >> 심사시간을 줄여야 한다.
                e = mid - 1;
                answer = Math.min(answer, mid);
            } else {    // 심사시간이 작아서 M명을 다 검사하지 못하는 경우 >> 심사시간을 늘려야 한다.
                s = mid + 1;
            }
        }

        System.out.println(answer);
    }
}