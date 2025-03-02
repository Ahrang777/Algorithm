import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        arr = new long[N];
        int r1 = 0, r2 = 0, r3 = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        boolean isEnd = false;
        long min = Long.MAX_VALUE;

        // 용액 1개 지정, 나머지 2개 용액에 대해 투포인터 사용
        for (int i = 0; i < N - 2; i++) {
            // 나머지 용액 2개
            int left = i + 1;
            int right = N - 1;

            if (isEnd) {
                break;
            }

            while (left < right) {
                long total = arr[i] + arr[left] + arr[right];

                if (min > Math.abs(total)) {
                    min = Math.abs(total);

                    r1 = i;
                    r2 = left;
                    r3 = right;
                }

                if (total == 0) {
                    r1 = i;
                    r2 = left;
                    r3 = right;
                    isEnd = true;
                    break;
                } else if (total > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        sb.append(arr[r1]).append(" ").append(arr[r2]).append(" ").append(arr[r3]);
        System.out.println(sb);
    }
}