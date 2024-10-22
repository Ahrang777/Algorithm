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
    static int T, N, D, C;
    static int[] d;
    static List<Node>[] graph;
    static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            d = new int[N + 1];
            graph = new List[N + 1];

            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());

                // b -> a s초
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph[b].add(new Node(a, s));
            }

            Arrays.fill(d, INF);

            dijkstra(C);

            int cnt = 0, max = 0;
            for (int i = 1; i <= N; i++) {
                if (d[i] != INF) {
                    cnt++;
                    max = Math.max(max, d[i]);
                }
            }

            sb.append(cnt).append(" ").append(max).append("\n");
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
                    d[next.index] = cost;
                    pq.offer(new Node(next.index, cost));
                }
            }
        }
    }
}