import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int num;
		String str;

		public Node(int num, String str) {
			this.num = num;
			this.str = str;
		}
	}
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			bfs(A, B);
		}
		System.out.println(sb);
	}

//	하나의 값에 대해 D, S, L, R 모두 확인해야한다.
//	dfs의 경우 A == B가 될때까지 처음 연산만 판다.
//	최솟값 = bfs
	private static void bfs(int start, int end) {
		Queue<Node> q = new ArrayDeque<>();
//		4자리 수이므로 배열의 크기를 10000 으로 지정
//		한번 방문한거 다시 계산 안하도록 visited[num] : num에 대한 계산 경우 완료
		boolean[] visited = new boolean[10000];

		q.offer(new Node(start, ""));
		visited[start] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int num = node.num;
			String str = node.str;
			if (num == end) {
				sb.append(str).append("\n");
				return;
			}

			int D = 2 * num % 10000;
			int S = num == 0 ? 9999 : num - 1;
			int L = (num % 1000) * 10 + num / 1000;
			int R = (num % 10) * 1000 + num / 10;

//			DSLR 중 방문안한 것들 모두 처리
			if (!visited[D]) {
				q.offer(new Node(D, str + "D"));
				visited[D] = true;
			}

			if (!visited[S]) {
				q.offer(new Node(S, str + "S"));
				visited[S] = true;
			}

			if (!visited[L]) {
				q.offer(new Node(L, str + "L"));
				visited[L] = true;
			}

			if (!visited[R]) {
				q.offer(new Node(R, str + "R"));
				visited[R] = true;
			}
		}
	}
}