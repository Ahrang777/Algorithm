import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static String[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());

		map = new String[N][N];

		star(0, 0, N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j] != null ? map[i][j] : " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);				
	}

	/**
	 * 
	 * @param x   시작점 x좌표
	 * @param y   시작점 y좌표
	 * @param len 한변 길이
	 */
	private static void star(int x, int y, int len) {
		if (len == 1) {
			map[x][y] = "*";
			return;
		}

		/*int size = len / 3;
		int cnt = 0;
		for (int i = x; i < x + len; i += size) {
			for (int j = y; j < y + len; j += size) {
				if (cnt != 4) {
					star(i, j, size);
				}		
				
				cnt++;
			}
		}*/
		
		len = len / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1)	continue;
				
				star(x + i * len, y + j * len, len);
			}
		}
	}
}