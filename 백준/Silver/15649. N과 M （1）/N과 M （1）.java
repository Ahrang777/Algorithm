import java.io.*;
import java.util.*;

public class Main {
    static int N, M, arr[];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        visited = new boolean[N];

        permutation(0, sb);
        System.out.println(sb);
    }

    private static void permutation(int cnt, StringBuilder sb) {
        if (cnt == M) {
            for (int n : arr) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            arr[cnt] = i + 1;
            permutation(cnt + 1, sb);
            visited[i] = false;
        }
    }
}
