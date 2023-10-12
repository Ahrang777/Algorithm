import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, dir, cnt, map[][];
	static boolean[][] visited;
	
	// 우, 우하, 하 방향의 파이프에서 다음으로 연결 가능한 방향 
	// 현재 nextDir 의 index 방향일때 다음으로 올 수 있는 방향
	static int[][] nextDir = {
			{0, 1}, {0, 1, 2}, {1, 2}
	};
	
	static int[] dx = {0, 1, 1};
	static int[] dy = {1, 1, 0};

	static final int BLANK = 0;
	static final int WALL = 1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited[0][0] = true;
		visited[0][1] = true;
		
		int dir = 0;
		dfs(0, 1, dir);
		System.out.println(cnt);
	}
	
	private static void dfs(int x, int y, int dir) {
		if (x == N - 1 && y == N - 1) {
			cnt++;						
			
			return;
		}
		
		// 현재 방향에서 다음으로 선택할 수 있는 방향
		for (int next : nextDir[dir]) {
			int nx = x + dx[next];
			int ny = y + dy[next];
			
			if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == WALL)	continue;
			
			if (next == 1) {
				if (map[nx - 1][ny] == WALL || map[nx][ny - 1] == WALL)	continue;
			}
			
			visited[nx][ny] = true;
			dfs(nx, ny, next);
			visited[nx][ny] = false;
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}