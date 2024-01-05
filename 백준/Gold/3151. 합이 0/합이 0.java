import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int total = arr[i] + arr[j];
                int target = -1 * total;

                int upper = upperBound(j + 1, N, target);
                int lower = lowerBound(j + 1, N, target);

                cnt += (upper - lower);
            }
        }

        System.out.println(cnt);
    }

    private static int upperBound(int low, int high, int target) {
        while (low < high) {
            int mid = (low + high) / 2;

            if (target < arr[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static int lowerBound(int low, int high, int target) {
        while (low < high) {
            int mid = (low + high) / 2;

            if (target <= arr[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}