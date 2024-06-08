import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, parents[];
    static List<Integer>[] graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        parents = new int[N + 1];
        graph = new List[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        bfs(1);

        for (int i = 2; i <= N; i++) {
            sb.append(parents[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            Integer now = q.poll();

            for (int next : graph[now]) {
                if (visited[next])  continue;

                visited[next] = true;
                q.offer(next);
                parents[next] = now;
            }
        }
    }
}