import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] map;
	static final int SIZE = 9;
	static boolean end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		map = new int[SIZE][SIZE];

		for (int i = 0; i < SIZE; i++) {
			String str = br.readLine();
			for (int j = 0; j < SIZE; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		dfs(0);

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static void dfs(int depth) {
		if (depth == 81) {
			end = true;

			return;
		}

		int x = depth / SIZE;
		int y = depth % SIZE;

		if (map[x][y] != 0) {
			dfs(depth + 1);
		} else {
			for (int i = 1; i <= 9; i++) {
				if (!isValid(x, y, i))
					continue;

				map[x][y] = i;
				dfs(depth + 1);
				if (end)	return;	// 한번 완성되면 그 이후는 처음 값보다 큰값이므로
				map[x][y] = 0;
			}
		}
	}

	private static boolean isValid(int x, int y, int num) {
		// 가로 , 세로
		for (int i = 0; i < SIZE; i++) {
			if (map[x][i] == num || map[i][y] == num)	return false;
		}

		// 3 x 3 체크
		x = x / 3 * 3;
		y = y / 3 * 3;
		for (int i = x; i < x + 3; i++) {
			for (int j = y; j < y + 3; j++) {
				if (map[i][j] == num)	return false;
			}
		}

		return true;
	}
}