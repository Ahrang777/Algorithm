import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] values;
    static List<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        values = new int[N + 1];
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent != -1) {
                graph[parent].add(i);
            }
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            values[index] += value;
        }

        dfs(1);

        for (int i = 1; i <= N; i++) {
            sb.append(values[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static void dfs(int now) {
        for (int next : graph[now]) {
            values[next] += values[now];
            dfs(next);
        }
    }
}