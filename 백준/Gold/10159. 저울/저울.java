import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] counts;
    static boolean[] visited;
    static List<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        counts = new int[N + 1];
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int big = Integer.parseInt(st.nextToken());
            int small = Integer.parseInt(st.nextToken());

            graph[big].add(small);
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            visited[i] = true;
            dfs(i, i);
        }

        for (int i = 1; i <= N; i++) {
            sb.append(N - counts[i]).append("\n");
        }

        System.out.println(sb);
    }

    /**
     *
     * @param start : 시작점
     * @param now : 그래프를 따라가며 현재 index >> start ~ 다음 위치
     */
    private static void dfs(int start, int now) {
        counts[now]++;

        for (int next : graph[now]) {
            if (visited[next]) {
                continue;
            }

            visited[next] = true;
            counts[start]++;
            dfs(start, next);
        }
    }
}