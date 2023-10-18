import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/4485
 * @author SSAFY
 *
 */
public class Main {
	static class Node implements Comparable<Node> {
		int x, y, dist;

		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}

	static int N, map[][], d[][], result;
	static final int INF = Integer.MAX_VALUE;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int idx = 1;

		while ((N = Integer.parseInt(br.readLine())) != 0) {
			map = new int[N][N];
			d = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					d[i][j] = INF;
				}
			}

			dijkstra(0, 0);

			sb.append("Problem ").append(idx++).append(": ").append(d[N - 1][N - 1]).append("\n");
		}

		System.out.println(sb);
	}

	private static void dijkstra(int x, int y) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(x, y, map[x][y]));
		d[x][y] = map[x][y];

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int curX = node.x;
			int curY = node.y;
			int dist = node.dist;

			if (d[curX][curY] < dist) continue;

			for (int i = 0; i < 4; i++) {
				int nx = curX + dx[i];
				int ny = curY + dy[i];

				if (!isRange(nx, ny))	continue;

				int cost = d[curX][curY] + map[nx][ny];

				if (cost < d[nx][ny]) {
					d[nx][ny] = cost;
					pq.offer(new Node(nx, ny, cost));
				}
			}
		}
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}