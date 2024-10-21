import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
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
    static int[] d, preCity;
    static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new List[N + 1];
        d = new int[N + 1];
        preCity = new int[N + 1];

        Arrays.fill(d, INF);

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, dist));
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        dikjstra(s, e);

        sb.append(d[e]).append("\n");

        Stack<Integer> stack = new Stack<>();
        stack.push(e);
        while (preCity[e] != 0) {
            stack.push(preCity[e]);
            e = preCity[e];
        }

        sb.append(stack.size()).append("\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }

    private static void dikjstra(int s, int e) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        d[s] = 0;

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
                    preCity[next.index] = now;
                }
            }
        }
    }
}