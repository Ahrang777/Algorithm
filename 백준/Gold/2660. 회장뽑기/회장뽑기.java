import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] graph;
    static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1) {
                break;
            }

            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            for (int a = 1; a <= N; a++) {
                for (int b = 1; b <= N; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][i] + graph[i][b]);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        List<Integer> results = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            int total = Integer.MIN_VALUE;
            for (int j = 1; j <= N; j++) {
                total = Math.max(total, graph[i][j]);
            }

            if (total > min) {
                continue;
            }

            if (total < min) {
                min = total;
                results.clear();
            }

            results.add(i);
        }

        sb.append(min).append(" ").append(results.size()).append("\n");

        for (int result : results) {
            sb.append(result).append(" ");
        }

        System.out.println(sb);
    }
}