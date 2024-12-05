import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());

            if (find(target)) {
                sb.append(1);
            } else {
                sb.append(0);
            }

            sb.append(" ");
        }

        System.out.println(sb);
    }

    private static boolean find(int target) {
        int s = 0, e = arr.length - 1;

        while (s <= e) {
            int mid = (s + e) / 2;

            if (target == arr[mid]) {
                return true;
            } else if (target < arr[mid]) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return false;
    }
}