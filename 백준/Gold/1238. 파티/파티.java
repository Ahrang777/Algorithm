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

    static int N, M, X;
    static List<Node>[] graph;
    static int[][] d;
    static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        d = new int[N + 1][N + 1];  // 시작점, 끝점

        for (int i = 1; i <= N; i++) {
            Arrays.fill(d[i], INF);
        }

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[s].add(new Node(e, dist));
        }

        for (int i = 1; i <= N; i++) {
            dijkstra(i);
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, d[X][i] + d[i][X]);
        }

        System.out.println(max);
    }

    private static void dijkstra(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));
        d[s][s] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.index;
            int dist = node.dist;

            if (d[s][now] < dist) {
                continue;
            }

            for (Node next : graph[now]) {
                int cost = d[s][now] + next.dist;

                if (cost < d[s][next.index]) {
                    pq.offer(new Node(next.index, cost));
                    d[s][next.index] = cost;
                }
            }
        }
    }
}