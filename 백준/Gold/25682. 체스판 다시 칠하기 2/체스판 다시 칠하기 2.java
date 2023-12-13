import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static char[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new char[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = str.charAt(j);
			}
		}

		System.out.println(Math.min(getMinBoard('B'), getMinBoard('W')));

		// 풀이
		// (0, 0)이 B로 시작할 경우, W로 시작할 경우 분리
		// 해당 위치가 체스판이 되기까지 B or W 같은지 다른지로 분리
		// 0이면 색 칠할 필요 없는거, 1이면 색 다시 칠해야 하는거
		// 이중 for문으로 누적합 구하기
		// 누적합 구했으면 이중 for문으로 시작점만 정해주고
		// 구간합을 통해 최솟값 갱신
	}

	private static int getMinBoard(char color) {
		int min = K * K;
		int[][] prefixSum = new int[N + 1][M + 1];
		int value = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 행, 열의 합이 짝수인 경우는 시작점과 같은 색이어야 한다.
				// 1 : 색을 변경해야 하는 경우, 0 : 색을 변경할 필요가 없는 경우
				if ((i + j) % 2 == 0) {
					value = board[i][j] != color ? 1 : 0;
				} else { // 행, 열의 합이 홀수인 경우는 시작점과 다른 색이어야 한다.
					value = board[i][j] == color ? 1 : 0;
				}
				prefixSum[i + 1][j + 1] = prefixSum[i][j + 1] + prefixSum[i + 1][j] - prefixSum[i][j] + value;
			}
		}

		// 각 시작점 기준으로 구간합을 구하고 최솟값 갱신
		for (int i = 1; i <= N - K + 1; i++) {
			for (int j = 1; j <= M - K + 1; j++) {
				min = Math.min(min, prefixSum[i + K - 1][j + K - 1] - prefixSum[i + K - 1][j - 1] - prefixSum[i - 1][j + K - 1] + prefixSum[i - 1][j - 1]);
			}
		}

		return min;
	}
}