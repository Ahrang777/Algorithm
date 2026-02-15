import java.io.*;
import java.util.*;

public class Main {
    static int N, result;
    static List<Integer>[] graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        graph = new List[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        result = 0;
        visited = new boolean[N + 1];
        dfs(1, 0);

        System.out.println(result % 2 == 0 ? "No" : "Yes");
    }

    private static void dfs(int now, int cnt) {
        visited[now] = true;

        int pass = 0;
        for (int next : graph[now]) {
            if (visited[next]) {
                pass++;
                continue;
            }

            dfs(next, cnt + 1);
        }

        if (pass == graph[now].size()) {
            result += cnt;
            return;
        }
    }
}
