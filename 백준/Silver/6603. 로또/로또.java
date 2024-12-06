import java.io.*;
import java.util.*;

public class Main {
    static int K;
    static int[] arr, output;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());

            K = Integer.parseInt(st.nextToken());

            if (K == 0) break;

            arr = new int[K];
            output = new int[6];

            for (int i = 0; i < K; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int cnt, int start) {
        if (cnt == 6) {
            for (int value : output) {
                sb.append(value).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int i = start; i < K; i++) {
            output[cnt] = arr[i];
            dfs(cnt + 1, i + 1);
        }
    }
}