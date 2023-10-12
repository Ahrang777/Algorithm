import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, result, output[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		output = new int[N];

		dfs(0);
		System.out.println(result);
	}

	private static void dfs(int cnt) {
		if (cnt == N) {
			result++;
			return;
		}

		for (int i = 0; i < N; i++) {
			output[cnt] = i;
			if (!isValid(cnt))	continue;
			
			dfs(cnt + 1);
		}
	}

	// 가로, 세로, 대각선 체크
	private static boolean isValid(int cnt) {
		for (int i = 0; i < cnt; i++) {
			// 세로 체크
			if (output[i] == output[cnt])	return false;
			if (Math.abs(i - cnt) == Math.abs(output[i] - output[cnt]))	return false;
		}

		return true;
	}	
}