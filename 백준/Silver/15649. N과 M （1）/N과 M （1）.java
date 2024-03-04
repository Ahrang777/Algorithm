import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static StringBuilder sb;
    static boolean[] visited;
    static int[] output;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        output = new int[M];

        dfs(0);

        System.out.println(sb.toString());
    }

    private static void dfs(int cnt) {
        if (cnt == M) {
            for (int out : output) {
                sb.append(out).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            output[cnt] = i + 1;
            dfs(cnt + 1);
            visited[i] = false;
        }
    }
}