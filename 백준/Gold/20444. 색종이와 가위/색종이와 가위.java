import java.io.*;
import java.util.*;

public class Main {
    static long N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());

        long s = 0;
        long e = N / 2;
        String answer = "NO";

        // (N + 1) * 1, (N + 1 - 1) * (1 + 1), (N + 1 -2) * (1 + 2)
        // ... (N + 1 - x) * (1 + x)
        // (N + 1 - x) >= 1 + x
        while (s <= e) {
            long mid = (s + e) / 2;
            long total = (N + 1 - mid) * (1 + mid);

            if (total == K) {
                answer = "YES";
                break;
            } else if (total < K) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        System.out.println(answer);
    }
}