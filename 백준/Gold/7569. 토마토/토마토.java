import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/7569
 * @author SSAFY
 *
 */
public class Main {
	static class Position {
		int h, x, y, cnt;

		public Position(int h, int x, int y, int cnt) {
			super();
			this.h = h;
			this.x = x;
			this.y = y;			
			this.cnt = cnt;
		}
	}
	
	static int M, N, H, map[][][], unripeCnt, min;	// 가로, 세로, 높이, 상자 상태, 안익은 토마토 개수
	static List<Position> list;
	// h, x, y
	static int[][] d = {
			{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 0, -1}, {0, 1, 0}, {0, 0, 1}
	};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][N][M];
		list = new ArrayList<>();
		min = Integer.MAX_VALUE;
		
		for (int h = 0; h < H; h++) {
			for (int x = 0; x < N; x++) {
				st = new StringTokenizer(br.readLine());
				for (int y = 0; y < M; y++) {
					map[h][x][y] = Integer.parseInt(st.nextToken());
					
					if (map[h][x][y] == 0) {	// 익지 않은 토마토
						unripeCnt++;
					}
					
					if (map[h][x][y] == 1) {	// 익은 토마토
						list.add(new Position(h, x, y, 0));						
					}
				}
			}
		}
		
		bfs();				
		
		System.out.println(unripeCnt == 0 ? min : -1);
	}
	
	private static void bfs() {
		Queue<Position> q = new ArrayDeque<>();
		
		for (Position p : list) {
			q.offer(p);
		}
		
		while (!q.isEmpty()) {
			Position now = q.poll();
			
			for (int i = 0; i < 6; i++) {
				int nh = now.h + d[i][0];
				int nx = now.x + d[i][1];
				int ny = now.y + d[i][2];
				min = now.cnt;
				
				if (!isRange(nh, nx, ny))	continue;
				
				if (map[nh][nx][ny] == 0) {
					unripeCnt--;
					map[nh][nx][ny] = 1;
					q.offer(new Position(nh, nx, ny, now.cnt + 1));
				}
			}
		}
	}
	
	private static boolean isRange(int h, int x, int y) {
		return h >= 0 && h < H && x >= 0 && x < N && y >= 0 && y < M;
	}
}