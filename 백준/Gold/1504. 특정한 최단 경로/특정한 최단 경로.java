import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int to, d;

        public Node(int to, int d) {
            this.to = to;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.d, o.d);
        }
    }
    static int N, E, min;
    static final int INF = (int) 1e7;
    static List<Node>[] graph;
    static boolean[] visited;
    static int[] d1, dN, dMid;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, d));
            graph[to].add(new Node(from, d));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        d1 = new int[N + 1];
        dMid = new int[N + 1];
        dN = new int[N + 1];

        Arrays.fill(d1, INF);
        Arrays.fill(dMid, INF);
        Arrays.fill(dN, INF);

        dijkstra(1, d1);
        dijkstra(v1, dMid);
        dijkstra(N, dN);

        int v1Dist = d1[v1] + dMid[v2] + dN[v2];
        int v2Dist = d1[v2] + dMid[v2] + dN[v1];

        if (v1Dist >= INF && v2Dist >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(v1Dist, v2Dist));
        }
    }

    private static void dijkstra(int start, int[] d) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.to;
            int dist = node.d;

            if (d[now] < dist)  continue;

            for (Node next : graph[now]) {
                int cost = d[now] + next.d;

                if (cost < d[next.to]) {
                    d[next.to] = cost;
                    pq.offer(new Node(next.to, cost));
                }
            }
        }
    }

    private static void bfs(int start, int end, int total) {
        if (start == end) {
            min = Math.min(min, total);
            return;
        }

        for (Node next : graph[start]) {
            if (visited[next.to])   continue;

            visited[next.to] = true;
            bfs(next.to, end, total + next.d);
            visited[next.to] = false;
        }
    }
}