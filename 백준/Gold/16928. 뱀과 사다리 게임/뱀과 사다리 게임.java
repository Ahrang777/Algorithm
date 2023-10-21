import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Position {
		int num, cnt;

		public Position(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}

	static int N, M, map[][], min;
	static final int SIZE = 10;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[SIZE][SIZE];
		min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken()) - 1;
			int n2 = Integer.parseInt(st.nextToken()) - 1;

			int x = n1 / SIZE;
			int y = n1 % SIZE;
			map[x][y] = n2;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken()) - 1;
			int n2 = Integer.parseInt(st.nextToken()) - 1;

			int x = n1 / SIZE;
			int y = n1 % SIZE;
			map[x][y] = n2;
		}

		bfs(0);
		System.out.println(min);
	}

	private static void bfs(int num) {
		Queue<Position> q = new ArrayDeque<>();
		q.offer(new Position(num, 0));

		int x = num / SIZE;
		int y = num % SIZE;
		map[x][y] = -1;

		while (!q.isEmpty()) {
			Position now = q.poll();

			if (now.num == 99) {
				min = now.cnt;
				break;
			}

			// 주사위 눈
			for (int i = 1; i <= 6; i++) {
				int next = now.num + i;

				if (!isRange(next))	continue;

				x = next / SIZE;
				y = next % SIZE;

				if (map[x][y] == -1)	continue;

				// 사다리 또는 뱀
				if (map[x][y] > 0) {
					next = map[x][y];
					map[x][y] = -1;
					x = next / SIZE;
					y = next % SIZE;
				}

				q.offer(new Position(next, now.cnt + 1));
				map[x][y] = - 1;
			}
		}
	}

	private static boolean isRange(int num) {
		return num >= 0 && num < 100;
	}
}