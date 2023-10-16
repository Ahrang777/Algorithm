import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map = new int[9][9];
	static boolean isFinished;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static void dfs(int cnt) {
		if (cnt == 81) {
			isFinished = true;
			return;
		}

		int x = cnt / 9;
		int y = cnt % 9;

		if (map[x][y] != 0) {
			dfs(cnt + 1);
		} else {
			for (int i = 1; i <= 9; i++) {
				if (!isValid(i, x, y))
					continue;

				map[x][y] = i;
				dfs(cnt + 1);
				if (isFinished)	return;
				map[x][y] = 0;
			}
		}
	}

	private static boolean isValid(int num, int x, int y) {
		// 가로, 세로
		for (int i = 0; i < 9; i++) {
			if (map[x][i] == num || map[i][y] == num) {
				return false;
			}
		}
		
		// 3 x 3
		// x / 3 * 3
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[i + x / 3 * 3][j + y / 3 * 3] == num) {
					return false;
				}
			}
		}
		
		return true;
	}
}