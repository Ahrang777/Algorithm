import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<Integer>[] graph; // 같은 파티에 나왔는지 연결 상태
	static boolean[] visited;	// 방문처리, 방문했으면 진실 전파된것

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int result = 0;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());

		int truthNum = Integer.parseInt(st.nextToken());
		int[] truthArr = new int[truthNum];
		for (int i = 0; i < truthNum; i++) {
			truthArr[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer>[] party = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			party[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int participantNum = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			party[i].add(start);
			for (int j = 1; j < participantNum; j++) {
				int x = Integer.parseInt(st.nextToken());
				graph[start].add(x);
				graph[x].add(start);
				party[i].add(x);
			}
		}

		// 진실 전파
		for (int t : truthArr) {
			bfs(t);
		}		
		
		for (int i = 0; i < M; i++) {
			boolean isExaggeration = true; 
			for (int n : party[i]) {
				if (visited[n]) {
					isExaggeration = false;
				}
			}
			
			if (isExaggeration) {
				result++;
			}
		}
		System.out.println(result);
	}

	private static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		if (!visited[start]) {
			visited[start] = true;
			q.offer(start);
		}

		while (!q.isEmpty()) {
			int now = q.poll();
			
			for (int next : graph[now]) {
				if (visited[next])	continue;
				
				q.offer(next);
				visited[next] = true;
			}
		}

	}
}