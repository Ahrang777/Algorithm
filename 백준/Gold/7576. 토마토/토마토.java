import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/7576
 * @author SSAFY
 *
 */
public class Main {
	static class Position {
		int x, y, cnt;

		public Position(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	static int N, M, map[][], min;
	static List<Position> list;	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());		
		
		N = Integer.parseInt(st.nextToken());	// 가로
		M = Integer.parseInt(st.nextToken());	// 세로
		
		map = new int[M][N];
		min = Integer.MAX_VALUE;
		list = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 1) {
					list.add(new Position(i, j, 0));
				}
			}
		}
		
		bfs();
		
		if (!isRipeTomatoes()) {
			min = -1;
		}
		
		System.out.println(min);
	}
	
	// 모든 토마토가 익었는지 여부 
	private static boolean isRipeTomatoes() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static void bfs() {
		Queue<Position> q = new ArrayDeque<>();
		
		for (Position p : list) {
			q.offer(p);
		}
		
		while (!q.isEmpty()) {
			Position now = q.poll();
			min = now.cnt;
			
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if (!isRange(nx, ny))	continue;
				
				
				if (map[nx][ny] == 0) {
					q.offer(new Position(nx, ny, now.cnt + 1));
					map[nx][ny] = 1;	
				}				
			}
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < M && y >= 0 && y < N;
	}
}