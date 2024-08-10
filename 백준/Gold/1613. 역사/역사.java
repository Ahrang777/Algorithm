import java.io.*;
import java.util.*;

public class Main {
    static int N, K, S;
    static boolean[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new boolean[N + 1][N + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = true;
        }

        for (int i = 1; i <= N; i++) {
            for (int a = 1; a <= N; a++) {
                if (!graph[a][i]) continue;
                for (int b = 1; b <= N; b++) {
                    if (graph[i][b]) {
                        graph[a][b] = true;
                    }
                }
            }
        }

        S = Integer.parseInt(br.readLine());
        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (graph[a][b]) {
                sb.append(-1).append("\n");
            } else if (graph[b][a]) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }

        System.out.println(sb);
    }
}