import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] indegrees;
	static List<Integer>[] list;
	static List<Integer> result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new ArrayList[N + 1];
		indegrees = new int[N + 1];
		result = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}				

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			indegrees[to]++;
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (indegrees[i] == 0) {
				sb.append(i).append(" ");
				q.offer(i);
			}
		}
		
		while (!q.isEmpty()) {
			int now = q.poll();
			
			for (int next : list[now]) {
				indegrees[next]--;
				
				if (indegrees[next] == 0) {
					q.offer(next);
					sb.append(next).append(" ");
				}
			}
		}
		
		System.out.println(sb);
	}
}