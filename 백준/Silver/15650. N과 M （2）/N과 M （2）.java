import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[] visited;
    static int[] output;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        output = new int[M];

        combination(0, 0);

        System.out.println(sb.toString());
    }

    static void combination(int cnt, int start) {
        if (cnt == M) {
            for (int o : output) {
                sb.append(o).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int i = start; i < N; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            output[cnt] = i + 1;
            combination(cnt + 1, i + 1);
            visited[i] = false;
        }
    }
}