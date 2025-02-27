import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static boolean[] visited;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        visited = new boolean[N + 1];

        dfs(0);
        System.out.println(sb);
    }

    private static void dfs(int cnt) {
        if (cnt == N) {
//            sb.setLength(0);

            for (int i : arr) {
                sb.append(i).append(" ");
            }
            sb.append("\n");

//            System.out.println(sb);

            return;
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            arr[cnt] = i;
            dfs(cnt + 1);
            visited[i] = false;
        }
    }
}