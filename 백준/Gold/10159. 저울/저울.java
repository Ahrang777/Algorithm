import java.io.*;
import java.util.*;

public class Main {
    static int N, M, cnt;
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i][i] = 1;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int big = Integer.parseInt(st.nextToken());
            int small = Integer.parseInt(st.nextToken());

            graph[big][small] = 1;
            graph[small][big] = -1;
        }

        for (int i = 1; i <= N; i++) {
            for (int a = 1; a <= N; a++) {
                for (int b = 1; b <= N; b++) {
                    if (graph[a][i] == 1 && graph[i][b] == 1) {
                        graph[a][b] = 1;
                    }
                    if (graph[a][i] == -1 && graph[i][b] == -1) {
                        graph[a][b] = -1;
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                if (graph[i][j] != 0) {
                    cnt++;
                }
            }
            sb.append(N - cnt).append("\n");
        }

        System.out.println(sb);
    }

}