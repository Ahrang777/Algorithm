import java.util.*;
import java.io.*;

class Solution
{
	static class Position {
		int x, y, cnt;

		public Position(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
	}
	static final int BLANK = 0;
	static int N, W, H, map[][], copy[][], output[], min;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			output = new int[N];
			min = Integer.MAX_VALUE;
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0);
			
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		
		System.out.println(sb);
	}
	
	// 중복순열
	private static void dfs(int depth) {
		if (depth == N) {
			copy = copy();
			
			// 공 쏘기 
			// 벽돌 깨기
			// 벽돌 떨어짐
			for (int w : output) {
				// 공 위치 찾기 
				int h = findX(w);
				
				if (!isRange(h, w))	continue;
				
				// 벽돌 깨기
				bfs(h, w);
				
				// 벽돌 내리기
				down();				
			}
			
			
			min = Math.min(min, countBlocks());
			
			return;
		}
		
		for (int i = 0; i < W; i++) {
			output[depth] = i;
			dfs(depth + 1);
		}
	}
	
	private static int countBlocks() {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (copy[i][j] != BLANK) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}
		
	private static int findX(int w) {
		for (int i = 0; i < H; i++) {
			if (copy[i][w] != BLANK) {
				return i;
			}
		}
		
		return H;
	}
	
	private static void down() {
		List<Integer> list = new ArrayList<>();		
//		int cnt = 0;
		for (int i = 0; i < W; i++) {
			list.clear();
			for (int j = H - 1; j >= 0; j--) {
				if (copy[j][i] != BLANK) {
					list.add(copy[j][i]);		
//					cnt++;
					copy[j][i] = BLANK;
				}
			}
			int idx = 0;
			for (int j = H - 1; j >= 0; j--) {
				if (idx >= list.size()) {
					break;			
				}
				copy[j][i] = list.get(idx++);
			}
		}
	
	}
	
	private static void bfs(int x, int y) {
		Queue<Position> q = new ArrayDeque<>();
		q.offer(new Position(x, y, copy[x][y]));
		copy[x][y] = BLANK;
		
		while (!q.isEmpty()) {
			Position now = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = now.x, ny = now.y;
				for (int j = 0; j < now.cnt - 1; j++) {
					nx += dx[i];	ny += dy[i];
					
					if (!isRange(nx, ny))	break;
					
					if (copy[nx][ny] == BLANK)	continue;
					
					q.offer(new Position(nx, ny, copy[nx][ny]));
					copy[nx][ny] = BLANK;
				}
			}
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < H && y >= 0 && y < W;
	}
	
	private static int[][] copy() {
		int[][] tmp = new int[H][W];
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		
		return tmp;
	}
}