import java.io.*;
import java.util.*;

public class Main {
    static int N, R, Q;
    static List<Integer>[] graph;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            graph[U].add(V);
            graph[V].add(U);
        }

        Arrays.fill(parents, 1);
        count(R, -1);


        for (int i = 0; i < Q; i++) {
            int U = Integer.parseInt(br.readLine());
            sb.append(parents[U]).append("\n");
        }

        System.out.println(sb);
    }

    private static void count(int now, int pa) {
        for (int next : graph[now]) {
            if (pa != next) {
                count(next, now);
            }
        }

        if (pa != -1) {
            parents[pa] += parents[now];
        }
    }
}
