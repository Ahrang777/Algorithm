import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1260
 * 
 * @author SSAFY
 *
 */
public class Main {
	static int N, M, V;
	static List<Integer>[] graph;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		graph = new List[N + 1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}

		for (int i = 1; i <= N; i++) {
			Collections.sort(graph[i]);
		}
		
		dfs(V, sb);
		sb.append("\n");
		
		Arrays.fill(visited, false);
		bfs(V, sb);
		
		System.out.println(sb);
	}
	
	private static void dfs(int start, StringBuilder sb) {
		visited[start] = true;
		sb.append(start).append(" ");
		
		for (int next : graph[start]) {
			if (!visited[next]) {
				dfs(next, sb);
			}
		}
	}
	
	private static void bfs(int start, StringBuilder sb) {
		Queue<Integer> q = new ArrayDeque<>();		
		q.offer(start);
		visited[start] = true;
		
		while (!q.isEmpty()) {
			int now = q.poll();
			sb.append(now).append(" ");
			
			for (int next : graph[now]) {
				if (!visited[next]) {
					q.offer(next);
					visited[next] = true;
				}
			}
		}
	}
}