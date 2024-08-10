import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
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

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (graph[i][j] || graph[j][i]) {
                    cnt++;
                }
            }

            if (cnt == N - 1) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}