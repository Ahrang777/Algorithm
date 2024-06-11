import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V, E, v[];
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(br.readLine());

        for (int t = 1; t <= K; t++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            graph = new List[V + 1];
            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            v = new int[V + 1];

            for (int e = 0; e < E; e++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[u].add(v);
                graph[v].add(u);
            }

            boolean flag = false;
            for (int i = 1; i <= V; i++) {
                if (v[i] == 0) {
                    flag = bfs(i);
                }

                if (!flag)  break;
            }

            if (flag) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);
    }

    private static boolean bfs(int s) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(s);
        v[s] = 1;

        while (!q.isEmpty()) {
            Integer now = q.poll();

            for (int next : graph[now]) {
                // 방문 한 경우, 같은 색
                if (v[next] == v[now]) {
                    return false;
                }

                // 방문 안한 경우
                if (v[next] == 0) {
                    v[next] = -v[now];
                    q.offer(next);
                }
            }
        }

        return true;
    }
}