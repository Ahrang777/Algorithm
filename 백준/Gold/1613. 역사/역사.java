import java.io.*;
import java.util.*;

public class Main {
    static int N, K, S;
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int a = 1; a <= N; a++) {
                for (int b = 1; b <= N; b++) {
                    if (graph[a][i] == 1 && graph[i][b] == 1) {
                        graph[a][b] = 1;
                    }
                }
            }
        }

        S = Integer.parseInt(br.readLine());
        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (graph[a][b] == 0 && graph[b][a] == 0) {
                sb.append(0).append("\n");
            } else if (graph[a][b] == 1) {
                sb.append(-1).append("\n");
            } else if (graph[b][a] == 1) {
                sb.append(1).append("\n");
            }
        }

        System.out.println(sb);
    }
}