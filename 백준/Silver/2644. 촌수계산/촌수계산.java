import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, T1, T2, M, result;
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        graph = new List[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        result = -1;

        st = new StringTokenizer(br.readLine());

        T1 = Integer.parseInt(st.nextToken());
        T2 = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            graph[parent].add(child);
            graph[child].add(parent);
        }

        dfs(T1, 0);
        System.out.println(result);
    }

    private static void dfs(int index, int total) {
        if (index == T2) {
            result = total;
            return;
        }

        for (int next : graph[index]) {
            if (visited[next])  continue;

            visited[next] = true;
            dfs(next, total + 1);
        }
    }
}