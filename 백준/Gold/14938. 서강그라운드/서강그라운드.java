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

    static int N, M, R;
    static int[] items;
    static List<Node>[] graph;
    static int[] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        items = new int[N + 1];
        graph = new ArrayList[N + 1];
        d = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, l));
            graph[b].add(new Node(a, l));
        }

        int max = 0;

        for (int i = 1; i <= N; i++) {
            max = Math.max(max, dijkstra(i));
        }

        System.out.println(max);
    }

    private static int dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(d, Integer.MAX_VALUE);

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

        int total = 0;
        for (int i = 1; i <= N; i++) {
            if (d[i] <= M) {
                total += items[i];
            }
        }

        return total;
    }
}