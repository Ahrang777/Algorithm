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
			return Integer.compare(o.dist, this.dist);
		}
	}

	static int N, M, A, B;
	static int[] d;
	static List<Node>[] graph;
	static final int INF = (int)1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		d = new int[N + 1];
		Arrays.fill(d, -1);	

		graph = new List[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			graph[from].add(new Node(to, dist));
			graph[to].add(new Node(from, dist));
		}

		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		dijkstra();
		System.out.println(d[B]);
	}

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(A, INF));
		d[A] = INF;

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.index;
			int dist = node.dist;

			if (d[now] > dist)	continue;

			for (Node next : graph[now]) {
				int cost = Math.min(dist, next.dist);

				if (cost > d[next.index]) {
					d[next.index] = cost;
					pq.offer(new Node(next.index, cost));
				}
			}
		}
	}
}