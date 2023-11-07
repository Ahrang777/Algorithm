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
	static boolean[] visited;	// bfs 방문처리, 방문했으면 진실 전파된것

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

		int truthNum = Integer.parseInt(st.nextToken());	// 진실을 아는 사람의 수 
		int[] truthArr = new int[truthNum];	// 진실을 아는 사람의 번호
		
		for (int i = 0; i < truthNum; i++) {
			truthArr[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer>[] party = new ArrayList[M];	// 각 파티마다 참여한 사람의 번호 저장
		for (int i = 0; i < M; i++) {
			party[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 현재 파티에 참여한 사람의 수
			int participantCnt = Integer.parseInt(st.nextToken());
			
			// 각 파티에는 무조건 1명이상 참여하기에 처음 나온 사람의 번호를 시작점으로 한다
			int start = Integer.parseInt(st.nextToken());
			
			party[i].add(start);
			for (int j = 1; j < participantCnt; j++) {
				int x = Integer.parseInt(st.nextToken());
				
				// 처음 나온 사람의 번호를 기준으로 그래프 연결
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
			// 해당 파티에서 과장된 이야기를 하였는가
			boolean isExaggeration = true; 
			for (int n : party[i]) {
				// 한번이라도 진실을 아는 사람이 등장하면 진실만 말해야 함
				if (visited[n]) {
					isExaggeration = false;
				}
			}
			
			// 과장된 이야기를 한 파티 수 증가
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