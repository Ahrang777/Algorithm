import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        int s = 0, e = 0;


        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            e = Math.max(e, arr[i]);
        }

        M = Integer.parseInt(br.readLine());
        int answer = 0;

        while (s <= e) {
            // 상한액
            int mid = (s + e) / 2;
            int total = 0;
            int max = 0;

            for (int i = 0; i < N; i++) {
                if (arr[i] <= mid) {
                    total += arr[i];
                    max = Math.max(max, arr[i]);
                } else {
                    total += mid;
                    max = Math.max(max, mid);
                }
            }

            if (total <= M) {
                s = mid + 1;
                answer = Math.max(answer, max);
            } else {
                e = mid - 1;
            }
        }

        System.out.println(answer);
    }
}