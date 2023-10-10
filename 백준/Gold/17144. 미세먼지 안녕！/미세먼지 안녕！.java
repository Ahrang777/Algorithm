import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/17144
 * 
 * @author SSAFY
 *
 */
public class Main {
	static int R, C, T, map[][];
	static List<Integer> airCleaner;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		airCleaner = new ArrayList<>();
		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == -1) {
					airCleaner.add(i);
				}
			}
		}

		for (int i = 0; i < T; i++) {
			// 미세먼지 확산
			map = diffusion();

			// 공기 순환
			cycle();
		}
		
		int total = 0;
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					total += map[i][j];
				}
			}
		}
		
		System.out.println(total);
	}

	private static int[][] diffusion() {
		int[][] tmp = new int[R][C];
		
		for (int x = 0; x < R; x++) {
			for (int y = 0; y < C; y++) {
				if (map[x][y] == -1) {
					tmp[x][y] = -1;
					continue;
				}
				
				tmp[x][y] += map[x][y];
				int dust = map[x][y] / 5;
				
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if (!isRange(nx, ny))	continue;
					if (map[nx][ny] == -1)	continue;
					
					tmp[x][y] -= dust;
					tmp[nx][ny] += dust;
				}		
			}
		}
		
		return tmp;
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}

	private static void cycle() {
		// 윗 공기청정기: 반시계 방향
        int top = airCleaner.get(0);

        // 왼쪽면
        for (int x = top - 1; x > 0; x--) {
            map[x][0] = map[x - 1][0];
        }

        // 윗면
        for (int y = 0; y < C - 1; y++) {
            map[0][y] = map[0][y + 1];
        }

        // 오른쪽 면
        for (int x = 0; x < top; x++) {
            map[x][C - 1] = map[x + 1][C - 1];
        }

        // 아랫면
        for (int y = C - 1; y > 1; y--) {
            map[top][y] = map[top][y - 1];
        }

        map[top][1] = 0;

        // 아래 공기청정기: 시계방향
        int bottom = airCleaner.get(1);

        // 왼쪽 면
        for (int x = bottom + 1; x < R - 1; x++) {
            map[x][0] = map[x + 1][0];
        }

        // 아랫면
        for (int y = 0; y < C - 1; y++) {
            map[R - 1][y] = map[R - 1][y + 1];
        }

        // 오른쪽 면
        for (int x = R - 1; x > bottom; x--) {
            map[x][C - 1] = map[x - 1][C - 1];
        }

        // 윗 면
        for (int y = C - 1; y > 1; y--) {
            map[bottom][y] = map[bottom][y - 1];
        }

        map[bottom][1] = 0;
	}
}