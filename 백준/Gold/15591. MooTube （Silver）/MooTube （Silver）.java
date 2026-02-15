import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int index, dist;

		public Node(int index, int dist) {
			this.index = index;
			this.dist = dist;
		}
	}

	static List<Node>[] graph;
	static int N, Q;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];
		for (int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			graph[p].add(new Node(q, r));
			graph[q].add(new Node(p, r));
		}

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			int cnt = 0;

			boolean[] visited = new boolean[N + 1];
			visited[v] = true;

			Queue<Integer> q = new LinkedList<>();
			q.offer(v);

			while (!q.isEmpty()) {
				Integer now = q.poll();

				for (Node next : graph[now]) {
					if (!visited[next.index] && next.dist >= k) {
						q.offer(next.index);
						visited[next.index] = true;
						cnt++;
					}
				}
			}

			sb.append(cnt).append("\n");
		}

		System.out.println(sb);
	}
}