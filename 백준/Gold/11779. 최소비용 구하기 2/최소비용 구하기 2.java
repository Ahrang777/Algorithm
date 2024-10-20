import java.io.*;
import java.util.*;

public class Main {
    static class Bus {
        int index, dist;

        public Bus(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }
    }

    static class Node implements Comparable<Node>{
        int index, dist;
        List<Integer> path;

        public Node(int index, int dist, List<Integer> path) {
            this.index = index;
            this.dist = dist;
            this.path = path;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    static int N, M;
    static List<Bus>[] graph;
    static int[] d;
    static List<Integer> results;
    static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new List[N + 1];
        d = new int[N + 1];
        results = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Bus(to, dist));
        }

        Arrays.fill(d, INF);
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        dikjstra(s, e);

        sb.append(d[e]).append("\n");
        sb.append(results.size()).append("\n");
        for (int n : results) {
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }

    private static void dikjstra(int s, int e) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        List<Integer> path = new ArrayList<>();
        path.add(s);
        pq.offer(new Node(s, 0, path));
        d[s] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.index;
            int dist = node.dist;
            path = node.path;

            if (d[now] < dist) {
                continue;
            }

            if (now == e && d[now] == dist) {
                results = path;
            }

            for (Bus next : graph[now]) {
                int cost = d[now] + next.dist;

                if (cost < d[next.index]) {
                    d[next.index] = cost;
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(next.index);
                    pq.offer(new Node(next.index, cost, newPath));
                }
            }
        }
    }
}