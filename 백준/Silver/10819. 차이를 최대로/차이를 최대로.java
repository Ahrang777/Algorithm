import java.io.*;
import java.util.*;

public class Main {
    static int N, result = Integer.MIN_VALUE;
    static int[] arr, output;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        output = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        perm(0);
        System.out.println(result);
    }

    private static void perm(int cnt) {
        if (cnt == N) {
            int total = 0;
            for (int i = 0; i < N - 1; i++) {
                total += Math.abs(output[i] - output[i + 1]);
            }

            result = Math.max(result, total);

            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            output[cnt] = arr[i];
            perm(cnt + 1);
            visited[i] = false;
        }
    }
}