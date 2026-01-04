import java.io.*;
import java.util.*;

public class Main {
    static int N, M, V;
    static List<Integer>[] graph;
    static StringBuilder sb;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        graph = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        dfs(V);

        sb.append("\n");
        Arrays.fill(visited, false);

        bfs(V);

        System.out.println(sb);
    }

    private static void dfs(int now) {
        visited[now] = true;
        sb.append(now).append(" ");

        for (int next : graph[now]) {
            if (visited[next]) {
                continue;
            }

            dfs(next);
        }
    }

    private static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(start);
        visited[start] = true;
        sb.append(start).append(" ");

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph[now]) {
                if (visited[next]) {
                    continue;
                }

                q.offer(next);
                visited[next] = true;
                sb.append(next).append(" ");
            }
        }
    }
}
