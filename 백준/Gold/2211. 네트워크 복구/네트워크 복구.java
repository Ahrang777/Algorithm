import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int index, dist;

        public Node(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    static int N, M;
    static List<Node>[] graph;
    static int[] d, links;
    static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        d = new int[N + 1];
        links = new int[N + 1];

        Arrays.fill(d, INF);
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph[A].add(new Node(B, C));
            graph[B].add(new Node(A, C));
        }

        dijkstra(1);

        sb.append(N - 1).append("\n");
        for (int i = 2; i <= N; i++) {
            sb.append(links[i]).append(" ").append(i).append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.index;
            int dist = node.dist;

            if (d[now] < dist) {
                continue;
            }

            for (Node next : graph[now]) {
                int cost = d[now] + next.dist;

                if (cost < d[next.index]) {
                    pq.offer(new Node(next.index, cost));
                    d[next.index] = cost;
                    links[next.index] = now;
                }
            }
        }
    }
}